package cz.martlin.douckonline.web.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("timesConverter")
public class TimesConverter implements Converter {

    public static final String DATE_FORMAT = "HH:mm";
    private static final DateFormat FORMAT = new SimpleDateFormat(DATE_FORMAT);

    public TimesConverter() {
    }
    
    public String getFormat() {
	return DATE_FORMAT;
   }
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	Calendar calendar = stringToCalendar(value);
	return calendar;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	Calendar calendar = (Calendar) value;
	String string = calendarToString(calendar);
	return string;
    }

    private String calendarToString(Calendar calendar) {
	return FORMAT.format(calendar.getTime());
    }

    private Calendar stringToCalendar(String value) {
	Calendar cal = Calendar.getInstance();
	try {
	    cal.setTime(FORMAT.parse(value));
	} catch (ParseException ex) {
	    throw new ConverterException("Cannot convert value to time calendar", ex);
	}
	return cal;
    }
    
}
