package design.command;

public abstract class Command {
    private Target target;
    public abstract void execute();
    public abstract void undo();
}
