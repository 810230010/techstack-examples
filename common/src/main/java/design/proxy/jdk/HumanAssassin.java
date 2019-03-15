package design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HumanAssassin implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("人类雇佣杀手出现..");
        System.out.println("任务是刺杀" + args[0]);
        method.invoke(proxy, args);
        return null;
    }
}
