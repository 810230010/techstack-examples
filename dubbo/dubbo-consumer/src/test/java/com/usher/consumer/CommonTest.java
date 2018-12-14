package com.usher.consumer;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class CommonTest {
    @Test
    public void test1(){
        Class<HashMap> clazz = HashMap.class;
        Type superClass = clazz.getGenericSuperclass();
        System.out.println(((ParameterizedType)superClass).getActualTypeArguments()[0].getTypeName());;
    }
}
