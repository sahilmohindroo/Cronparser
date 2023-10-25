package src.main.java.cronParser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CronField {

    private CronExpression.FieldType type;

    private List<Integer> values;

    public CronField(CronExpression.FieldType type, String valueStr) {
        this.type = type;

        if (valueStr == null || valueStr.isBlank()){
            throw new IllegalArgumentException();
        }
        values = new ArrayList<>();
        if (valueStr.equals("*")){
            for (int i = type.min; i <=type.max ; i++){
                values.add(i);
            }
        } else if (valueStr.contains(",")){
            values = Arrays.stream(valueStr.split(","))
                    .map(Integer::parseInt).collect(Collectors.toList());
        } else if (valueStr.contains("-")){
            List<Integer> range = Arrays.stream(valueStr.split("-"))
                    .map(Integer::parseInt).collect(Collectors.toList());
            if (range.size() != 2|| range.get(0).compareTo(range.get(1)) > 0
                    || range.get(1).compareTo(type.max) > 0
                    || range.get(0).compareTo(type.min) < 0){
                throw new IllegalArgumentException("invalid values");
            }
            for (int i = range.get(0); i <= range.get(1); i++){
                values.add(i);
            }
        } else if (valueStr.contains("/")){
            List<String> rangeStr = Arrays.stream(valueStr.split("/")).collect(Collectors.toList());
            if (rangeStr.size() != 2){
                throw new IllegalArgumentException("invalid expression");
            }
            Integer start = 0;
            Integer skip = Integer.parseInt(rangeStr.get(1));
            if(!rangeStr.get(0).equals("*") ){
                start = Integer.parseInt(rangeStr.get(0));
            }

            while(start <= type.max){
                values.add(start);
                start =start+ skip;
            }
        }else if (valueStr.chars().allMatch( Character::isDigit )){
            Integer val = Integer.parseInt(valueStr);
            if (val.compareTo(type.max) > 0 || val.compareTo(type.min) < 0){
                throw new IllegalArgumentException("invalid values");
            }
            values.add(val);
        }else {
            throw new IllegalArgumentException();
        }
    }

    public CronExpression.FieldType getType() {
        return type;
    }

    public List<Integer> getValues() {
        return values;
    }
}
