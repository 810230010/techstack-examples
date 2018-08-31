package com.usher.elasticsearch.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestResult {
    private int code;
    private String msg;
    private Object data;

    public RestResult() {
    }
}
