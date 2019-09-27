package com.usher.web.groovy;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huajia 2019-08-19
 **/
public class GroovyDemo {
    public static void main(String[] args) throws Exception{
       List<Long> list = new ArrayList<>();
       list.add(1L);
       list.add(2L);

       List<Long> temp = list;

       List<Long> list2 = new ArrayList<>();
       list2.add(1L);
       list2.add(2L);

       temp.removeAll(list2);
        System.out.println(list);
    }
}
