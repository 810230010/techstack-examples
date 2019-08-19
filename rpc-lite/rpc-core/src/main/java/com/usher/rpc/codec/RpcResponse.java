package com.usher.rpc.codec;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResponse implements Serializable {
    private String requestId;
    private Object result;
    private Object error;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
