package design.proxy.static_proxy;

import design.proxy.Assassin;
import design.proxy.Species;

public class DwarfKing extends King {
    public DwarfKing(Assassin assassin) {
        super(assassin);
    }

    @Override
    public void assassinate(Species species) {
        System.out.println(String.format("%s侏儒杀手，希望你能帮我干掉%s", assassin.name, species.getDescription()));
        this.assassin.assassinate(species);
    }

    @Override
    public String getDescription() {
        return "侏儒国国王";
    }
}
