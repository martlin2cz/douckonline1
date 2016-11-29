
package cz.martlin.douckonline.business.utils;

import cz.martlin.douckonline.business.test.TestDataCreator;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class ToolsTest {
    
    private static final double EPSILON = 0.00001;
    
    public ToolsTest() {
    }

    @Test
    public void testTime() {
	Calendar expected1 = new GregorianCalendar(0, 0, 0, 1, 30);
	Calendar actual1 = Tools.time(1, 30);
	assertEquals(expected1.getTime().toString(), actual1.getTime().toString());
	assertEquals(1, actual1.get(Calendar.HOUR_OF_DAY));
	assertEquals(30, actual1.get(Calendar.MINUTE));
    }
    
    @Test
    public void testDurationToHours() {
	Calendar duration1 = Tools.time(0, 0);
	BigDecimal expected1 = new BigDecimal(0);
	BigDecimal actual1 = Tools.durationToHours(duration1);
	assertEquals(expected1.doubleValue(), actual1.doubleValue(), EPSILON);
	
	Calendar duration2 = Tools.time(1, 0);
	BigDecimal expected2 = new BigDecimal(1);
	BigDecimal actual2 = Tools.durationToHours(duration2);
	assertEquals(expected2.doubleValue(), actual2.doubleValue(), EPSILON);
	
	Calendar duration3 = Tools.time(0, 45);
	BigDecimal expected3 = new BigDecimal(0.75);
	BigDecimal actual3 = Tools.durationToHours(duration3);
	assertEquals(expected3.doubleValue(), actual3.doubleValue(), EPSILON);
	
	Calendar duration4 = Tools.time(1, 30);
	BigDecimal expected4 = new BigDecimal(1.5);
	BigDecimal actual4 = Tools.durationToHours(duration4);
	assertEquals(expected4.doubleValue(), actual4.doubleValue(), EPSILON);
    }
    
    @Test
    public void testToPercentMultiplier() {
	BigDecimal input1 = new BigDecimal(950);
	BigDecimal expected1 = new BigDecimal(1000);
	BigDecimal mult1 = Tools.toPercentMultiplier(5);
	BigDecimal actual1 = input1.multiply(mult1);
	
	assertEquals(expected1.doubleValue(), actual1.doubleValue(), EPSILON);
	
	BigDecimal input2 = new BigDecimal(42);
	BigDecimal expected2 = new BigDecimal(42);
	BigDecimal mult2 = Tools.toPercentMultiplier(0);
	BigDecimal actual2 = input2.multiply(mult2);
	
	assertEquals(expected2.doubleValue(), actual2.doubleValue(), EPSILON);
	
	BigDecimal input3 = new BigDecimal(20);
	BigDecimal expected3 = new BigDecimal(40);
	BigDecimal mult3 = Tools.toPercentMultiplier(50);
	BigDecimal actual3 = input3.multiply(mult3);
	
	assertEquals(expected3.doubleValue(), actual3.doubleValue(), EPSILON);
	
	
    }
    
}
