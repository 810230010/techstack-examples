package design.abstract_factory;

public class HumanKindom implements Kindom {
    @Override
    public Castle createCastle() {
        return new HumanCastle();
    }

    @Override
    public Army createArmy() {
        return new HumanArmy();
    }
}
