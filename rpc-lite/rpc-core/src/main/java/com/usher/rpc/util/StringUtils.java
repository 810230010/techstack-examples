package com.usher.rpc.util;

import java.util.UUID;

public class StringUtils {
    public static String trimEdgeStr(String sourceStr, String needTrimChar){
        String trimmedStr = "";
        StringBuilder sb = new StringBuilder(sourceStr);
        if(sb.toString().startsWith(needTrimChar)){
            int trimStartIndex = sourceStr.indexOf(needTrimChar);
            sb.deleteCharAt(trimStartIndex);
        }
        if(sb.toString().endsWith(needTrimChar)){
            int trimEndIndex = sb.toString().lastIndexOf(needTrimChar);
            sb.deleteCharAt(trimEndIndex);
        }
        return sb.toString();
    }

    /**
     * 生成uuid
     * @return
     */
    public static String generateUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
