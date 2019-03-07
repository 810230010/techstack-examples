package design.abstract_factory;

public class HumanArmy implements Army{
    @Override
    public void describe() {
        System.out.println("human's army");
    }
}
