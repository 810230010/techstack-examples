package design.proxy.static_proxy;

import design.proxy.Assassin;
import design.proxy.Species;
import design.proxy.static_proxy.King;

public class HumanKing extends King {


    public HumanKing(Assassin assassin) {
        super(assassin);
    }

    @Override
    public void assassinate(Species species) {
        System.out.println(String.format("%s骑士，希望你能帮我干掉%s", assassin.name, species.getDescription()));
        this.assassin.assassinate(species);
    }

    @Override
    public String getDescription() {
        return "人类国王";
    }
}
