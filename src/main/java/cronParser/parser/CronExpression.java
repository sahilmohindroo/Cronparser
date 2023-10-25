package src.main.java.cronParser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CronExpression {
    private String expression;
    private CronField minute;
    private CronField hour;
    private CronField dayOfMonth;
    private CronField month;
    private CronField dayOfWeek;


    public CronExpression(String expresssion) {
        this.expression = expresssion;
        parse();
    }

    private void parse() {
        if (expression == null || expression.isBlank()){
            throw new IllegalArgumentException();
        }
        String[] values = expression.split(" ");
        if (values.length != 5) {
            throw new IllegalArgumentException();
        }

        this.minute = new CronField(FieldType.minute, values[0]);
        this.hour = new CronField(FieldType.hour, values[1]);
        this.dayOfMonth = new CronField(FieldType.dayOfMonth, values[2]);
        this.month = new CronField(FieldType.month, values[3]);
        this.dayOfWeek = new CronField(FieldType.dayOfWeek, values[4]);
    }



    public String getExpression() {
        return expression;
    }

    public CronField getMinute() {
        return minute;
    }

    public CronField getHour() {
        return hour;
    }

    public CronField getDayOfMonth() {
        return dayOfMonth;
    }

    public CronField getMonth() {
        return month;
    }

    public CronField getDayOfWeek() {
        return dayOfWeek;
    }

    protected enum FieldType{

        minute(0,59),
        hour(0,23),
        dayOfMonth(1,31),
        month(1,12),
        dayOfWeek(1,7);

        protected final int min;
        protected final int max;

        FieldType(int min, int max) {
            this.min = min;
            this.max = max;
        }

    }
}
