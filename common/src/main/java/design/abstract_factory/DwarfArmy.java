package design.abstract_factory;

/**
 * 侏儒人军队
 */
public class DwarfArmy implements Army {
    @Override
    public void describe() {
        System.out.println("a land army");
    }
}
