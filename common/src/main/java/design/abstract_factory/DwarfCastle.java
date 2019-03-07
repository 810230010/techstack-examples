package design.abstract_factory;

/**
 * 侏儒人城堡
 */
public class DwarfCastle implements Castle {
    @Override
    public void describe() {
        System.out.println("a small castle");
    }
}
