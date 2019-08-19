package basic;

public abstract class Father {
    protected String name;

    public Father(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Father father = new Son("a", 1);
    }
}
