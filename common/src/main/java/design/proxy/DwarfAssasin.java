package design.proxy;

public class DwarfAssasin extends Assassin {

    public DwarfAssasin(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return null;
    }
    @Override
    public void assassinate(Species species) {
        System.out.println("已经解决"+ species.getDescription());
    }
}
