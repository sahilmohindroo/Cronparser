package src.main.java.cronParser.parser;
import src.main.java.cronParser.parser.CronExpression;

import java.util.stream.Collectors;

public class Parser {

public static void main(String[] args) {

    // validate for space
    String cronExp = args[0].substring(0, args[0].lastIndexOf(" "));
    String cmd = args[0].substring(args[0].lastIndexOf(" "));
    CronExpression cronExpression = new CronExpression(cronExp);

    System.out.println("minute "+cronExpression.getMinute().stream().map(String::valueOf).collect(Collectors.joining(",")));
    System.out.println("hours "+cronExpression.getHour().stream().map(String::valueOf).collect(Collectors.joining(",")));
    System.out.println("dayOfMonth "+cronExpression.getDayOfMonth().stream().map(String::valueOf).collect(Collectors.joining(",")));
    System.out.println("month "+cronExpression.getMonth().stream().map(String::valueOf).collect(Collectors.joining(",")));
    System.out.println("dayofWeek "+cronExpression.getDayOfWeek().stream().map(String::valueOf).collect(Collectors.joining(",")));

    System.out.println("command "+cmd);

}    
}    