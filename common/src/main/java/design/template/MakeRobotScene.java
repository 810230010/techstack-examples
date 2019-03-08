package design.template;

import design.Scene;

public class MakeRobotScene extends Scene {
    @Override
    public void show() throws InterruptedException {
        RobotModel humanRobot = new HumanStyleRobotModel("人形机器人");
        humanRobot.assemble();
    }

    public static void main(String[] args) throws InterruptedException {
        new MakeRobotScene().show();
    }
}
