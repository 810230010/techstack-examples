package com.usher.web.groovy;

import org.json.JSONObject;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huajia 2019-08-19
 **/
public class MenuStrategy extends AbstractRuleStrategy {
    /**
     *
     * @param requestBody  {"user_id":"xxx","phone":"xx"}
     * @return
     * @throws Exception
     */
    @Override
    public String execute(String requestBody) throws Exception{
        ScriptEngineManager factory = new ScriptEngineManager();
        final ScriptEngine groovyEngine = factory.getEngineByName("groovy");
        Map<String, Object> menuStrategy = new HashMap<>();
        menuStrategy.put("dimension", "user_id");
        //检查request body是否有dimension
        groovyEngine.eval("def execute(requestBody){return 'man say:' + words;}\n" +
                "def say(requestBody){return 'man say:' + words;}");
        String message = (String)((Invocable)groovyEngine).invokeFunction("execute", requestBody);
        return message;
    }
}
