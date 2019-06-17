package basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
public class StringTest {
    @Test
    public void demo1(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        List<String> list1 = new ArrayList<>();
        List<String> a = list.stream().filter(item->!list1.contains(item)).collect(Collectors.toList());
        System.out.println(a);
    }
}
