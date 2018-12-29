package com.usher.rpc.common;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
//注册中心地址:端口号/接口全限定名?key=value
@Data
public class ServiceURL {
    private final String url;
    private final String host;
    private final int port;
    private final String path;
    private final Map<String, String> params;

    public ServiceURL() {
        this(null, null, 0, null, null);
    }

    public ServiceURL(String url) {
        this(url, null, 0, null, null);
    }

    public ServiceURL(String url, String host, int port, String path, Map<String, String> params) {
        this.url = url;
        this.host = host;
        this.port = port;
        this.path = path;
        this.params = params;
    }

    public ServiceURL(String host, int port, String path, Map<String, String> params) {
        this.host = host;
        this.port = port;
        this.path = path;
        this.params = params;
        StringBuilder paramStr = new StringBuilder();
        String finalUrl = "";
        if(!CollectionUtils.isEmpty(params)){
            for(Map.Entry<String, String> entry : params.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                paramStr.append(String.format("%s=%s&", key, value));
            }
            finalUrl = String.format("%s:%d/%s?%s", host, port, path, paramStr.toString());
        }
        finalUrl = com.usher.rpc.util.StringUtils.trimEdgeStr(finalUrl, "&");
        this.url = finalUrl;
    }

    public ServiceURL toServiceURL(String url){
        int hostAndIpSeparatorIndex = url.indexOf(":");
        if(hostAndIpSeparatorIndex < 0){
            throw new IllegalArgumentException("illegal service url!");
        }
        String host = url.substring(0, hostAndIpSeparatorIndex);

        int firstSlashIndex = url.indexOf("/");
        if(firstSlashIndex < 0){
            throw new IllegalArgumentException("illegal service url");
        }
        int port = Integer.parseInt(url.substring(hostAndIpSeparatorIndex+1, firstSlashIndex).trim());
        String pathIncludeParamStr = url.substring(firstSlashIndex+1);

        int i = pathIncludeParamStr.indexOf("?");
        String path = pathIncludeParamStr.substring(0, i);
        String queryStr = pathIncludeParamStr.substring(i + 1);
        Map<String, String> params = null;
        if(!StringUtils.isEmpty(queryStr)){
            params = new HashMap<>();
            String[] paramArr = queryStr.split("&");
            for(String param : paramArr){
                String[] paramKeyAndValue = param.split("=");
                String key = paramKeyAndValue[0];
                String value = paramKeyAndValue[1];
                params.put(key, value);
            }
        }
        return new ServiceURL(url, host, port, path, params);
    }

    @Override
    public String toString() {
        return url;
    }

    public String getParamValueFor(String paramKey){
        if(CollectionUtils.isEmpty(params)){
            return null;
        }
        return this.params.get(paramKey);
    }
}
