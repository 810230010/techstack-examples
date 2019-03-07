package design.abstract_factory;

/**
 * 侏儒人王国
 */
public class DwarfKindom implements Kindom {
    @Override
    public Castle createCastle() {
        return new DwarfCastle();
    }

    @Override
    public Army createArmy() {
        return new DwarfArmy();
    }
}
