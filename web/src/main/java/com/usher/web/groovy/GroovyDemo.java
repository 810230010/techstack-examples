package com.usher.web.groovy;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Date;

/**
 * @author huajia 2019-08-19
 **/
public class GroovyDemo {
    public static void main(String[] args) throws Exception{
        MenSayStrategy menSayStrategy = new MenSayStrategy();
        System.out.println(menSayStrategy.say("hello"));
    }
}
