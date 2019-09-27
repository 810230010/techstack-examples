package com.usher.web.groovy;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author huajia 2019-08-19
 **/
public class MenSayStrategy extends AbstractSayStrategy{
    @Override
    public String say(String words) throws Exception{
        ScriptEngineManager factory = new ScriptEngineManager();
        final ScriptEngine groovyEngine = factory.getEngineByName("groovy");
        groovyEngine.eval("def say(words){return 'man say:' + words;}\n" +
                "def say2(words){return 'man say2:' + words;}");
        String message = (String)((Invocable)groovyEngine).invokeFunction("say2", words);

        String valFunc = "def val(requestBody){" +
                            "def jsonSlurper = new JsonSlurper();" +
                            "def obj = jsonSlurper.parseText(requestBody);" +
                          "}";
        return message;
    }
}
