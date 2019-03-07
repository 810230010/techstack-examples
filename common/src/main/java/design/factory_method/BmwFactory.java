package design.factory_method;

public class BmwFactory implements CarFactory{
    @Override
    public Car createCar() {
        return new BmwCar();
    }
}
