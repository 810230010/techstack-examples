package design.proxy;

public class HumanAssassin extends Assassin{
    public HumanAssassin(String name) {
        super(name);
    }

    @Override
    public void assassinate(Species species) {
        System.out.println("已经解决"+ species.getDescription());
    }

    @Override
    public String getDescription() {
        return "人类王国杀手";
    }
}
