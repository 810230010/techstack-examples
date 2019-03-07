package design.abstract_factory;

/**
 * 人类城堡
 */
public class HumanCastle implements Castle {
    @Override
    public void describe() {
        System.out.println("human's castle");
    }
}
