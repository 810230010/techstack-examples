package basic;

public class BasicTest {
    public static void main(String[] args) {
        test();
    }
    BasicTest basicTest = new BasicTest();

    static {
        System.out.println("静态代码块...");
    }

    {
        System.out.println("构造代码块...");
    }

    BasicTest(){
        System.out.println("构造方法...");
        System.out.println("a = "+ a + ",b = " + b);
    }
    public static void test(){
        System.out.println("test方法...");
    }
    int a = 110;
    static int b = 200;
}
