package cz.martlin.douckonline.business.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents set of relations between entities. I.e. (Pet.owner - Customer),
 * (Customer.employer - Company).
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Relations {

    private final Map<Class<?>, Map<Class<?>, String>> relations;

    public Relations() {
	this.relations = new HashMap<>();
    }

    /**
     * Adds specified relation: <code>(from.attribute - to)</code>. If needed,
     * change order.
     *
     * @param from
     * @param to
     * @param attribute
     */
    public void addRelation(Class<?> from, Class<?> to, String attribute) {
	Map<Class<?>, String> map = relations.get(from);

	if (map == null) {
	    map = new HashMap<>();
	    relations.put(from, map);
	}

	map.put(to, attribute);
    }

    /**
     * Tests if exists specified relation. If needed, change order.
     *
     * @param from
     * @param to
     * @param attribute
     * @return
     */
    public boolean hasRelation(Class<?> from, Class<?> to, String attribute) {
	String realAttr = get(from, to);

	return attribute.equals(realAttr);
    }

    /**
     * Gets attribute holding the relation between from and to. If needed,
     * change order.
     *
     * @param from
     * @param to
     * @return
     */
    public String get(Class<?> from, Class<?> to) {
	Map<Class<?>, String> map = relations.get(from);

	if (map == null) {
	    return null;
	} else {
	    return map.get(to);
	}
    }

    @Override
    public String toString() {
	return "Relations{" + relations + "}";
    }
}
