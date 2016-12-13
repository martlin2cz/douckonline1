package cz.martlin.douckonline.web.converters;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 * @param <T>
 */
public abstract class WithLongIdConverter<T extends EntityWithLongID> implements  Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String idStr = value;
	long idLong = Long.parseLong(idStr);
	T item = findById(idLong);
    	return item;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	T item = (T) value;
	long idLong = item.getId();
	String idString = Long.toString(idLong);
	return idString;
    }

    protected abstract T findById(long id);
    
}
