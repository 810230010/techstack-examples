package com.usher.web.groovy;

/**
 * @author huajia 2019-08-19
 **/
public abstract class AbstractRuleStrategy {
    public abstract String execute(String requestBody) throws Exception;
}
