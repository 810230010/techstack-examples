package design.prototype;

import design.Scene;

public class ReproduceScene extends Scene {
    @Override
    public void show() throws InterruptedException {
        ReproduceSkill reproduceSkill = new ReproduceSkill(new Human("忍者1"));
        reproduceSkill.show();
    }

    public static void main(String[] args) throws InterruptedException {
        new ReproduceScene().show();
    }
}
