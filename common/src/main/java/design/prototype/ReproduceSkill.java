package design.prototype;

import design.proxy.Species;

public class ReproduceSkill implements Skill {
    private Human human;

    public ReproduceSkill(Human human){
        this.human = human;
        System.out.println("分身术使用人: " + human);
    }
    @Override
    public void show() {
        System.out.println("使用分身术");
        try {
            Human copyHuman = (Human) this.human.clone();
            System.out.println("分身技能释放成功，" + copyHuman);
        } catch (CloneNotSupportedException e) {
            System.out.println("分身技能释放失败");
            e.printStackTrace();
        }
    }
}
