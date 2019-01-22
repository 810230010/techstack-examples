package basic;

public class StringDemo {
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
    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }
}
