package cz.martlin.douckonline.business.tools;

import java.util.Objects;

/**
 * Class representing simple JPA query. Query allows to load over one or more
 * tables, filter using WHERE or sort by ORDER BY.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class SimpleQuery<T> {

    private final Class<T> clazz;
    private final StringBuilder from;
    private StringBuilder where;
    private StringBuilder order;

//<editor-fold defaultstate="collapsed" desc="constructing">
    /**
     * Creates query over one entity table (given by its class).
     *
     * @param clazz
     */
    public SimpleQuery(Class<T> clazz) {
	this.clazz = clazz;
	this.from = createSimpleFrom(clazz);
    }

    /**
     * Creates query with result of type clazz over other tables given by
     * entities froms. The relations between entities is specified by relations
     * object. Param isStar specifies whether given froms should be joined like
     * star (<code>x JOIN b ON x=b JOIN c ON x=c JOIN d ON x=d</code>) or like
     * chain (<code>a JOIN b ON a=b JOIN c ON b=c JOIN d ON c=d</code>).
     *
     * @param relations
     * @param isStar
     * @param clazz
     * @param froms
     */
    public SimpleQuery(Relations relations, boolean isStar, Class<T> clazz, Class<?>... froms) {
	this.clazz = clazz;
	if (isStar) {
	    this.from = createJoinedStarFrom(clazz, froms, relations);
	} else {
	    this.from = createJoinedChainedFrom(clazz, froms, relations);
	}
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="adding WHERE clausules">
    /**
     * Adds simple where condition, like:
     * <code>q.addWhereCondition("entity.description != null")</code>.
     *
     * @param condition
     */
    public void addWhereCondition(String condition) {
	this.where = prepare(this.where, " AND ");
	this.where.append(condition);
    }

    /**
     * Adds test for attribute value, so <code>q.addWhereAttribute("foo")</code>
     * will output to <code>"entity.foo = :foo"</code>.
     *
     * @param attrAndVar
     */
    public void addWhereAttribute(String attrAndVar) {
	this.where = prepare(this.where, " AND ");
	this.where.append(createAttribute(clazz, attrAndVar, true));
	this.where.append(" = ");
	this.where.append(createVariable(attrAndVar));

    }

    /**
     * Adds test for attribute value (with explicit variable), so
     * <code>q.addWhereVariable("foo", "bar")</code> will output to
     * <code>"entity.foo = :bar"</code>.
     *
     * @param attribute
     * @param attrOfEntity
     * @param variable
     */
    public void addWhereVariable(String attribute, boolean attrOfEntity, String variable) {
	this.where = prepare(this.where, " AND ");
	this.where.append(createAttribute(clazz, attribute, attrOfEntity));
	this.where.append(" = ");
	this.where.append(createVariable(variable));
    }

    /**
     * Adds test for attribute value (with explicit value to equal to), so
     * <code>q.addWhereValue("foo", true, 42)</code> will output to
     * <code>"entity.foo = 42"</code>.
     *
     * @param attribute
     * @param attrOfEntity
     * @param value
     */
    public void addWhereValue(String attribute, boolean attrOfEntity, Object value) {
	this.where = prepare(this.where, " AND ");
	this.where.append(createAttribute(clazz, attribute, attrOfEntity));
	this.where.append(" = ");
	this.where.append(value);
    }

    /**
     * Adds test for attribute value (with whatever done with it), so
     * <code>q.addWhereWhatever("foo", true, " != null")</code> will output to
     * <code>"entity.foo != null"</code>.
     *
     * @param attribute
     * @param attrOfEntity
     * @param whatever
     */
    public void addWhereWhatever(String attribute, boolean attrOfEntity, String whatever) {
	this.where = prepare(this.where, " AND ");
	this.where.append(createAttribute(clazz, attribute, attrOfEntity));
	this.where.append(' ');
	this.where.append(whatever);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="adding ORDER clausule">
    /**
     * Adds order specification.
     *
     * @param attribute
     * @param onlyOnEntity
     */
    public void addOrder(String attribute, boolean onlyOnEntity) {
	this.order = prepare(this.order, ", ");
	this.order.append(createAttribute(clazz, attribute, onlyOnEntity));
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="creating FROM clausule">
    /**
     * Creates simple from clausule (only for one entity, class), returns:
     * <code>FooBar fooBar</code>
     *
     * @param clazz
     * @return
     */
    private StringBuilder createSimpleFrom(Class<?> clazz) {
	StringBuilder stb = new StringBuilder();

	stb.append(toTable(clazz));
	stb.append(' ');
	stb.append(toObject(clazz));

	return stb;
    }

    /**
     * Creates from clausule using chaining join (i.e. like
     * <code>"FROM Foo foo INNER JOIN Bar bar ON foo.attr = bar INNER JOIN Baz baz ON bar.attr = baz"</code>).
     *
     * @param clazz
     * @param froms
     * @param relations
     * @return
     */
    private StringBuilder createJoinedChainedFrom(Class<T> clazz, Class<?> froms[], Relations relations) {
	StringBuilder stb = createSimpleFrom(clazz);

	for (int i = -1; i < froms.length - 1; i++) {
	    Class<?> from = i != -1 ? froms[i] : clazz;
	    Class<?> to = froms[i + 1];

	    String attr;
	    Class<?> joinFrom, joinTo;

	    attr = relations.get(from, to);
	    joinFrom = from;
	    joinTo = to;

	    if (attr == null) {
		attr = relations.get(to, from);
		joinFrom = to;
		joinTo = from;
	    }

	    stb.append(createJoinCriteria(to, joinFrom, joinTo, attr));
	}

	return stb;
    }

    /**
     * Creates from clausule using the star topology. i.e. like:
     * <code>FROM Foo foo INNER JOIN Bar bar ON foo.x = bar INNER JOIN Baz baz ON foo.y = baz</code>
     *
     * @param clazz
     * @param froms
     * @param relations
     * @return
     */
    private StringBuilder createJoinedStarFrom(Class<T> clazz, Class<?> froms[], Relations relations) {
	StringBuilder stb = createSimpleFrom(clazz);

	for (int i = 0; i < froms.length; i++) {
	    Class<?> to = froms[i];
	    String attr;
	    Class<?> joinFrom, joinTo;

	    attr = relations.get(clazz, to);
	    joinFrom = clazz;
	    joinTo = to;

	    if (attr == null) {
		attr = relations.get(to, clazz);
		joinFrom = to;
		joinTo = clazz;
	    }

	    stb.append(createJoinCriteria(to, joinFrom, joinTo, attr));
	}

	return stb;
    }

    /**
     * Just simply creates StringBuilder with join criteria, i. e.:
     * <code>INNER JOIN Bar bar ON foo.x = bar</code>.
     *
     * @param destClass
     * @param attrClass
     * @param objClass
     * @param attr
     * @return
     */
    private StringBuilder createJoinCriteria(Class<?> destClass, Class<?> attrClass, Class<?> objClass, String attr) {
	StringBuilder stb = new StringBuilder();

	stb.append(" INNER JOIN ");
	stb.append(createSimpleFrom(destClass));
	stb.append(" ON ");

	stb.append(createAttribute(attrClass, attr, true));
	stb.append(" = ");
	stb.append(toObject(objClass));

	return stb;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="converting to JPQL">
    /**
     * Renders query to JPQL (or how it's called (: ).
     *
     * @return
     */
    public String toJPQL() {
	StringBuilder stb = new StringBuilder();

	stb.append("SELECT DISTINCT");
	stb.append(' ');
	stb.append(toObject(clazz));
	stb.append(' ');
	stb.append("FROM");
	stb.append(' ');
	stb.append(from);

	if (where != null) {
	    stb.append(' ');
	    stb.append("WHERE");
	    stb.append(' ');
	    stb.append(where);
	}

	if (order != null) {
	    stb.append(' ');
	    stb.append("ORDER BY");
	    stb.append(' ');
	    stb.append(order);
	}

	return stb.toString();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="utilities methods">
    /**
     * If given string builder is not initialized (is null), creates one, else
     * just appends glue.
     *
     * @param stb
     * @param glue
     * @return
     */
    private StringBuilder prepare(StringBuilder stb, String glue) {
	if (stb == null) {
	    stb = new StringBuilder();
	} else {
	    stb.append(glue);
	}

	return stb;
    }

    /**
     * Creates attribute specifier. In format <code>"entity.attr"</code> if
     * onlyOnEntity is true, <code>"attr"</code> otherwise.
     *
     * @param clazz
     * @param attr
     * @param onlyOnEntity
     * @return
     */
    private CharSequence createAttribute(Class<?> clazz, String attr, boolean onlyOnEntity) {
	StringBuilder stb = new StringBuilder();

	if (onlyOnEntity) {
	    String object = toObject(clazz);
	    stb.append(object);
	    stb.append('.');
	}
	stb.append(attr);

	return stb;
    }

    /**
     * Creates variable. It is just simply string <code>":variable"</code>.
     *
     * @param variable
     * @return
     */
    private StringBuilder createVariable(String variable) {
	StringBuilder stb = new StringBuilder();

	stb.append(':');
	stb.append(variable);

	return stb;
    }

    /**
     * For given entity class creates item name. For class <code>FooBar</code>
     * returns <code>fooBar</code> (only lowercases first char).
     *
     * @param clazz
     * @return
     */
    private String toObject(Class<?> clazz) {
	String name = clazz.getSimpleName();
	return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }

    /**
     * For given entity class creates "table" specifier. In fact, only returns
     * entity class name.
     *
     * @param clazz
     * @return
     */
    private String toTable(Class<?> clazz) {
	return clazz.getSimpleName();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="hashCode, equals, toString">
    @Override
    public int hashCode() {
	int hash = 7;
	hash = 73 * hash + Objects.hashCode(this.clazz);
	hash = 73 * hash + Objects.hashCode(this.from);
	hash = 73 * hash + Objects.hashCode(this.where);
	hash = 73 * hash + Objects.hashCode(this.order);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final SimpleQuery<?> other = (SimpleQuery<?>) obj;
	if (!Objects.equals(this.clazz, other.clazz)) {
	    return false;
	}
	if (!Objects.equals(this.from, other.from)) {
	    return false;
	}
	if (!Objects.equals(this.where, other.where)) {
	    return false;
	}
	if (!Objects.equals(this.order, other.order)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "SimpleQuery{" + "clazz=" + clazz + ", from=" + from + ", where=" + where + ", order=" + order + '}';
    }
//</editor-fold>

}
