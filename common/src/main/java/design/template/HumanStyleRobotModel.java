package design.template;

public class HumanStyleRobotModel extends RobotModel {
    public HumanStyleRobotModel(String name) {
        super(name);
    }

    @Override
    protected void assembleHands() {
        System.out.println("组装二只手..");
    }

    @Override
    protected void assembleFeet() {
        System.out.println("组装二只脚..");
    }

    @Override
    protected void assembleHead() {
        System.out.println("组装仿人头..");
    }

    @Override
    protected void assembleBody() {
        System.out.println("组装仿人身体..");
    }
}
