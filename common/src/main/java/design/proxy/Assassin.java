package design.proxy;

/**
 * 刺客
 */
public abstract class Assassin{
    public String name;
    public Assassin(String name) {
        this.name = name;
    }
    public abstract void assassinate(Species species);
    public abstract String getDescription();
}