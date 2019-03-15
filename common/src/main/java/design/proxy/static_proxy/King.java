package design.proxy.static_proxy;

import design.proxy.Assassin;
import design.proxy.Species;

public abstract class King {
    public Assassin assassin;

    public King(Assassin assassin) {
        this.assassin = assassin;
    }

    /**
     * 杀人
     */
    public abstract void assassinate(Species species);

    public abstract String getDescription();
}
