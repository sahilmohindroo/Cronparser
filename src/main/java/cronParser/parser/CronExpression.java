package src.main.java.cronParser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CronExpression {
    String expresssion;
    List<Integer> minute;
    List<Integer> hour;
    List<Integer> dayOfMonth;
    List<Integer> month;
    List<Integer> dayOfWeek;


    public CronExpression(String expresssion) {
        this.expresssion = expresssion;
        parse();
    }

    private void parse() {
        if (expresssion == null || expresssion.isBlank()){
            throw new IllegalArgumentException();
        }
        String[] values = expresssion.split(" ");
        if (values.length != 5) {
            throw new IllegalArgumentException();
        }

        this.minute = parseField(values[0], FieldType.minute);
        this.hour = parseField(values[1], FieldType.hour);
        this.dayOfMonth = parseField(values[2], FieldType.dayOfMonth);
        this.month = parseField(values[3], FieldType.month);
        this.dayOfWeek = parseField(values[4], FieldType.dayOfWeek);
    }

    private List<Integer> parseField(String value, FieldType type) {
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException();
        }
        List<Integer> field = new ArrayList<>();
        if (value.equals("*")){
            for (int i = type.min; i <=type.max ; i++){
                field.add(i);
            }
        } else if (value.contains(",")){
            field = Arrays.stream(value.split(","))
                    .map(Integer::parseInt).collect(Collectors.toList());
        } else if (value.contains("-")){
            List<Integer> range = Arrays.stream(value.split("-"))
                    .map(Integer::parseInt).collect(Collectors.toList());
            if (range.size() != 2|| range.get(0).compareTo(range.get(1)) > 0
                || range.get(1).compareTo(type.max) > 0
                || range.get(0).compareTo(type.min) < 0){
                throw new IllegalArgumentException("invalid values");
            }
            for (int i = range.get(0); i <= range.get(1); i++){
                field.add(i);
            }
        } else if (value.contains("/")){
            List<String> rangeStr = Arrays.stream(value.split("/")).collect(Collectors.toList());
            if (rangeStr.size() != 2){
                throw new IllegalArgumentException("invalid expression");
            }
            Integer start = 0;
            Integer skip = Integer.parseInt(rangeStr.get(1));
            if(!rangeStr.get(0).equals("*") ){
                start = Integer.parseInt(rangeStr.get(0));
            }

            while(start <= type.max){
                field.add(start);
                start =start+ skip;
            }
        }else if (value.chars().allMatch( Character::isDigit )){
            field.add(Integer.parseInt(value));
        }else {
            throw new IllegalArgumentException();
        }
        return field;
    }

    public String getExpresssion() {
        return expresssion;
    }

    public List<Integer> getMinute() {
        return minute;
    }

    public List<Integer> getHour() {
        return hour;
    }

    public List<Integer> getDayOfMonth() {
        return dayOfMonth;
    }

    public List<Integer> getMonth() {
        return month;
    }

    public List<Integer> getDayOfWeek() {
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
