package cz.martlin.douckonline.web.converters;

import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("teachingsConverter")
public class TeachingsConverter extends WithLongIdConverter<Teaching> {

    private final Teachings TEACHINGS = new Teachings();
    
    public TeachingsConverter() {
    }

    @Override
    protected Teaching findById(long id) {
	return TEACHINGS.getTeachingById(id);
    }
    
}
