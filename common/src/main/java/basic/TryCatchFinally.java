package basic;

import java.util.HashMap;
import java.util.Map;

public class TryCatchFinally {
    /**
     *
     * @return world
     */
    public static String tryCatchDemo1(){
        String s = "";
        try {
            s = "hello";
        }catch (Exception e){

        }finally {
        }
        s = "world";
        return s;
    }

    /**
     *
     * @return hello
     */
    public static String tryCatchDemo2(){
        String s = "";
        try {
            s = "hello";
            return s;
        }catch (Exception e){

        }finally {
            s = "world";
        }
        return s;
    }

    /**
     *
     * String temp = sb.toString()
     * sb.append(" world")
     * return temp
     * @return hello
     */
    public static String tryCatchDemo3(){
        StringBuilder sb = new StringBuilder("hello");
        try {
            return sb.toString();
        }catch (Exception e){
            return sb.toString();
        }finally {
            sb.append(" world");
        }
    }


    /**
     *
     * @return world
     */
    public static String tryCatchDemo4(){
        String s = "";
        try {
            s = "hello";
            return s;
        }catch (Exception e){

        }finally {
            s = "world";
            return s;
        }
    }

    /**
     *
     * @return world
     * no exception
     * 如果在finally中返回值，不会抛异常
     */
    public static String tryCatchDemo5(){
        Map map = new HashMap();
        map.getOrDefault("a", "no a");
        String s = "";
        try {
            throw new RuntimeException();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            s = "world";
            return s;
        }
    }

}
