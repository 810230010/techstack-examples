package design.abstract_factory;

public class GiantKindom implements Kindom {
    @Override
    public Castle createCastle() {
        return new GiantCastle();
    }

    @Override
    public Army createArmy() {
        return new DwarfArmy();
    }
}
