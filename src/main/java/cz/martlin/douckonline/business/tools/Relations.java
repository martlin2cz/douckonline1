package cz.martlin.douckonline.business.tools;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Relations {
    
    private final Map<Class<?>, Map<Class<?>, String>> relations;

    public Relations() {
	this.relations = new HashMap<>();
    }
    
    public void addRelation(Class<?> from, Class<?> to, String attribute) {
	Map<Class<?>, String> map = relations.get(from);
	
	if (map == null) {
	    map = new HashMap<>();
	    relations.put(from, map);
	}
	
	map.put(to, attribute);
    }

    public boolean hasRelation(Class<?> from, Class<?> to, String attribute) {
	String realAttr = get(from, to);
	
	return attribute.equals(realAttr);
    }    

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
