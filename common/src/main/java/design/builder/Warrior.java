package design.builder;

public class Warrior {
    private Weapon weapon;
    private Armor armor;
    private String name;
    public Warrior(Weapon weapon, Armor armor, String name) {
        this.weapon = weapon;
        this.armor = armor;
        this.name = name;
    }
    public void describe(){
        System.out.println(String.format("%s is wearing %s, equipped with %s", name, armor.describe(), weapon.describe()));
    }
    public static class WarriorBuilder{
        private Weapon weapon;
        private Armor armor;
        private String name;
        public WarriorBuilder weapon(Weapon weapon){
            this.weapon = weapon;
            return this;
        }

        public WarriorBuilder armor(Armor armor){
            this.armor = armor;
            return this;
        }
        public WarriorBuilder name(String name){
            this.name = name;
            return this;
        }
        public Warrior build(){
            return new Warrior(weapon, armor, name);
        }


        public Weapon getWeapon() {
            return weapon;
        }

        public void setWeapon(Weapon weapon) {
            this.weapon = weapon;
        }

        public Armor getArmor() {
            return armor;
        }

        public void setArmor(Armor armor) {
            this.armor = armor;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
