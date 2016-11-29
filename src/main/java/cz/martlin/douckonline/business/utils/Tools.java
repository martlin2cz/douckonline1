package cz.martlin.douckonline.business.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;


/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Tools {
    private static final BigDecimal MINUTES_IN_HOUR = new BigDecimal(60);
    private static final BigDecimal PERCENT = new BigDecimal(100);
    private static final int PRECISION = 10;

    private Tools() {
    }
   
    
     public static Calendar time(int hours, int minutes) {
	Calendar calendar = Calendar.getInstance();
	
	calendar.set(Calendar.HOUR_OF_DAY, hours);
	calendar.set(Calendar.MINUTE, minutes);
	calendar.set(Calendar.SECOND, 0);
	
	calendar.set(Calendar.DAY_OF_YEAR, 0);
	calendar.set(Calendar.YEAR, 0);
	
	return calendar;
    }
    
    public static BigDecimal durationToHours(Calendar duration){
	int hours = duration.get(Calendar.HOUR);
	int minutes = duration.get(Calendar.MINUTE);
	
	BigDecimal result = new BigDecimal(minutes);
	
	result = result.divide(MINUTES_IN_HOUR, PRECISION, RoundingMode.HALF_UP);
	result = result.add(new BigDecimal(hours));
	
	return result;
    }
    
    public static BigDecimal toPercentMultiplier(int discount){
	
	BigDecimal disc = new BigDecimal(discount);
	BigDecimal sum = PERCENT.subtract(disc);
	
	BigDecimal result = PERCENT.divide(sum, PRECISION, RoundingMode.HALF_UP);
	return result;
    }
}
