package practic.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huajia 2019-08-20
 **/
public class PatternRegexDemo {
    public static final Pattern GET_NAME_PATTERN = Pattern.compile("(\\w+),hello");

    public static void main(String[] args) {
        Matcher matcher = GET_NAME_PATTERN.matcher("huajia,hello");
        if(!matcher.find()){
            System.out.println("未匹配...");
        }else {
            String name = matcher.group(1);
            System.out.println(name);
        }
    }
}
