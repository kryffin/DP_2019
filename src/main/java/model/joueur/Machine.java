package main.java.model.joueur;

import main.java.model.Position;
import main.java.model.Tir;
import main.java.model.strategy.Aleatoire;
import main.java.model.strategy.IA;

public class Machine extends Joueur {

    private IA ia;

    public Machine(){
        super();
        ia = new Aleatoire();
    }

    public void setComportement(IA ia){
        System.out.println("changement de strategie en " + ia.getClass().getName());
        this.ia = ia;
    }
    public Position getTarget(){
        return ia.comportement();
    }
}
