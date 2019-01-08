package com.util;


public class GetValueUtil {

    public static Integer GetIntegerValue(Object data) {
        if (data == null) {
            return null;
        }
        return Integer.valueOf(data.toString());
    }

    public static Double GetDoubleValue(Object data) {
        if (data == null) {
            return null;
        }
        return Double.valueOf(data.toString());
    }

    public static String GetStringValue(Object data) {
        if (data == null) {
            return null;
        }
        return data.toString();
    }

    public static Object GetDefaultValue(Object data, Object defaultValues) {
        return data == null ? defaultValues : data;
    }
}
