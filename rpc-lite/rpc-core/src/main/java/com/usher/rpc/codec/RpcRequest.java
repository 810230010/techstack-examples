package com.usher.rpc.codec;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String className;
    private Class[] parameterTypes;
    private Object[] params;
    private String methodName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
