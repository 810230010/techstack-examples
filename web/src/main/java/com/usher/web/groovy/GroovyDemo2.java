package com.usher.web.groovy;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huajia 2019/09/05
 **/
public class GroovyDemo2 {
    public static void main(String[] args) throws Exception{
        String valFunc = "import com.alibaba.fastjson.JSON;" +
                    "def invoke(){" +
                        "def method = \"test\";\n" +
                        "return \"${method}\"(1);" +
                    "}\n" +
                    "def val(requestBody){" +
                        "def obj = JSON.parseObject(requestBody, Map.class);" +
                        "def method = \"test\"; " +
                        "return true;" +
                    "}\n" +

                    "def test(a){" +
                        "return true;" +
                    "}";
        ScriptEngineManager factory = new ScriptEngineManager();
        final ScriptEngine groovyEngine = factory.getEngineByName("groovy");
        try {
            groovyEngine.eval(valFunc);
            Map map = new HashMap<>();
            map.put("couponAmount", 1000);
            boolean b = (boolean) ((Invocable)groovyEngine).invokeFunction("invoke");
            System.out.println(b);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

}
