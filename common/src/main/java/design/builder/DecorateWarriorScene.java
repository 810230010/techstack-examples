package design.builder;

import design.Scene;

public class DecorateWarriorScene extends Scene {
    @Override
    public void show() throws InterruptedException {
        Warrior warrior = new Warrior.WarriorBuilder().weapon(new MetalSword())
                .armor(new BulletProofArmor())
                .name("亚历山大").build();
        warrior.describe();
    }

    public static void main(String[] args) throws InterruptedException {
        new DecorateWarriorScene().show();
    }
}
