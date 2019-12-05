package main.java.model;

import main.java.model.strategy.Aleatoire;
import main.java.model.strategy.IA;

public class Machine extends Joueur {

    private IA ia;

    public Machine(){
        super();
        ia = new Aleatoire();
    }
}
