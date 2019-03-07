package basic;

import design.abstract_factory.Army;
import design.abstract_factory.GiantKindom;
import design.abstract_factory.Castle;
import design.abstract_factory.Kindom;
import design.factory_method.Car;
import design.factory_method.CarFactory;
import design.singleton.Singleton;
import org.junit.Assert;
import org.junit.Test;

public class DesignPatternTest {
    @Test
    public void singletonTest(){
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        Assert.assertSame(singleton1, singleton2);
    }
    @Test
    public void factoryMethodTest(){

        try {
            //制造benz汽车
            CarFactory benzFactory = (CarFactory) Class.forName("design.factory_method.BenzFactory").newInstance();
            Car benz = benzFactory.createCar();
            benz.describe();
            //制造bmw
            CarFactory bmwFactory = (CarFactory) Class.forName("design.factory_method.BmwFactory").newInstance();
            Car bmw = bmwFactory.createCar();
            bmw.describe();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void abstractFactoryTest(){
        Kindom bigKindom = new GiantKindom();
        Army army = bigKindom.createArmy();
        Castle castle = bigKindom.createCastle();


    }
}
