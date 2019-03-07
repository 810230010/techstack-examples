package design.abstract_factory;

import design.Scene;

import java.util.concurrent.TimeUnit;

public class BuildKindomScene extends Scene {
    @Override
    public void show() throws InterruptedException {
        //侏儒王国
        Kindom dwarfKindom = new DwarfKindom();
        System.out.println("侏儒王国正在建立！");
        Castle dwarfCastle = dwarfKindom.createCastle();
        System.out.println("侏儒城堡已经建成！");
        Army dwarfArmy = dwarfKindom.createArmy();
        System.out.println("侏儒军队编制完成!");
        System.out.println("侏儒王国建成!");
        Thread.sleep(2000);

        //巨人王国
        Kindom giantKindom = new GiantKindom();
        System.out.println("巨人王国正在建立！");
        Castle giantCastle = giantKindom.createCastle();
        System.out.println("巨人城堡已经建成！");
        Army giantArmy = giantKindom.createArmy();
        System.out.println("巨人军队编制完成！");
        System.out.println("巨人王国建成!");
        Thread.sleep(2000);

        //人类王国
        Kindom humanKindom = new HumanKindom();
        System.out.println("人类王国正在建立！");
        Castle humanCastle = humanKindom.createCastle();
        System.out.println("人类城堡已经建成！");
        Army humanArmy = humanKindom.createArmy();
        System.out.println("人类军队编制完成!");
        System.out.println("人类王国建成!");

        Thread.sleep(1000);
        System.out.println("三大阵营已经建立完毕!");
    }

    public static void main(String[] args) throws InterruptedException {
        new BuildKindomScene().show();
    }
}
