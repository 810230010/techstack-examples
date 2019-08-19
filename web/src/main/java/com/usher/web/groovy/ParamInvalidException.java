package com.usher.web.groovy;

/**
 * @author huajia 2019-08-19
 **/
public class ParamInvalidException extends RuntimeException {
    public ParamInvalidException(String message){
        super(message);
    }
}
