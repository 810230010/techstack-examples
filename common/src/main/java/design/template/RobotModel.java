package design.template;

public abstract class RobotModel {
    String name;

    public RobotModel(String name) {
        this.name = name;
    }

    protected abstract void assembleHands();
    protected abstract void assembleFeet();
    protected abstract void assembleHead();
    protected abstract void assembleBody();
    public void assemble(){
        assembleBody();
        assembleHead();
        assembleHands();
        assembleFeet();
        System.out.println(name + "组装完成..");
    }
}
