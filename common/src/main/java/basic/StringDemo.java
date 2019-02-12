package basic;

public class StringDemo {
    private static String test = "adsda";
    public static void demo1(){
        String a = "a";
        String b = "a";
        System.out.println(a == b.intern());
    }


    public static void demo2(){
        String a = "a";
        String b = "a" + "b";
        System.out.println("ab" == b);
    }
    public static void demo3(){
        String a = "a";
        String b = "b";
        String c = a + b;
        System.out.println("ab" == c);
    }
    public static void demo4(){
        String a = new String("a").intern();
        String b = new String("a").intern();
        System.out.println(a == b);
    }
    public static void demo5(String s){
        String a = new String("a").intern();
        String b = new String("a").intern();
        System.out.println(a == b);
    }
    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
    }
}
