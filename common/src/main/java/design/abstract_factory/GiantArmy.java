package design.abstract_factory;

/**
 * 巨人军队
 */
public class GiantArmy implements Army{
    @Override
    public void describe() {
        System.out.println("a giant army");
    }
}
