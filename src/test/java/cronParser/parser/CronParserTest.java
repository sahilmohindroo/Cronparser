package src.test.java.cronParser.parser;

import org.junit.Test;
import src.main.java.cronParser.parser.CronExpression;
import src.main.java.cronParser.parser.Parser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CronParserTest {




    @Test
    public void correct_range() {
        CronExpression exp = new CronExpression("0/15 1-3 1,15 * 1-5");

        assertEquals(new ArrayList<>(List.of(0,15,30,45)), exp.getMinute());
        assertEquals(new ArrayList<>(List.of(1,2,3)), exp.getHour());
        assertEquals(new ArrayList<>(List.of(1,15)), exp.getDayOfMonth());
        assertEquals(new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9,10,11,12)), exp.getMonth());
        assertEquals(new ArrayList<>(List.of(1,2,3,4,5)), exp.getDayOfWeek());

    }

    @Test(expected = NumberFormatException.class)
    public void failedNAN() {
        CronExpression exp = new CronExpression("0/d 1-3 3-5 5-7 1-5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void failedInvalidNumberOfArgs() {
        CronExpression exp = new CronExpression("*/15 0 1,15 * 1-5 1-3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void failedInvalidExp() {
        CronExpression exp = new CronExpression("*/ 0 1,15 * 1-5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void failedOutOfBoundValues() {
        CronExpression exp = new CronExpression("*/15 30 1,15 * 1-5");
    }

}
