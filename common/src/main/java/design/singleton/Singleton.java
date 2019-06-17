package design.singleton;

/**
 * 单例模式
 * 饿汉、懒汉、枚举、DCL(双重检查锁)、静态内部类
 */
public class Singleton {
    private Singleton(){}
    //饿汉
    private static final Singleton INSTANCE = new Singleton();
    public static Singleton getInstance(){
        return INSTANCE;
    }

    //懒汉
    private static volatile Singleton INSTANCE2;
    public static Singleton getInstance2(){
        synchronized (Singleton.class){
            if(INSTANCE2 == null){
                INSTANCE2 = new Singleton();
            }
        }
        return INSTANCE;
    }

    //DCL
    private static volatile Singleton INSTANCE3;
    public static Singleton getInstance3(){
        if(INSTANCE3 == null){
            synchronized (Singleton.class){
                if(INSTANCE3 == null){
                    INSTANCE3 = new Singleton();
                }
            }
        }
        return INSTANCE3;
    }

    //静态内部类
    private static class InstanceHolder{
        public static Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance4(){
        return InstanceHolder.INSTANCE;
    }


    //枚举类
}
