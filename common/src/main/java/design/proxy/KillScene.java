package design.proxy;

import design.Scene;
import design.proxy.static_proxy.DwarfKing;
import design.proxy.static_proxy.HumanKing;
import design.proxy.static_proxy.King;

import java.lang.reflect.Proxy;

public class KillScene extends Scene {
    @Override
    public void show() throws InterruptedException {
        System.out.println("");
        King humanKing = new HumanKing(new HumanAssassin("Glant"));
        humanKing.assassinate(new Dwarf());

        King dwarfKing = new DwarfKing(new HumanAssassin("Massacre"));
        dwarfKing.assassinate(new Human());

        System.out.println("使用jdk...");
        King humanKing2 = (King) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{King.class}, new design.proxy.jdk.HumanAssassin());
        humanKing2.assassinate(new Dwarf());
    }

    public static void main(String[] args) throws InterruptedException {
        new KillScene().show();
    }
}
