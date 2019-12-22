package main.java.model.strategy;

import main.java.model.Position;
import main.java.model.Tir;
import main.java.model.plateau.bateau.Arme;

import java.util.Random;

public class Croix implements IA {

    private Position pos;
    private int cpt = 0;
    private Position[] pool;

    @Override
    public Position comportement() {
        System.out.println("strat croix");
        Random rand = new Random();
        int x_rand = rand.nextInt(7)+1;
        int y_rand = rand.nextInt(7)+1;
        if (pos==null || cpt > 3){
            System.out.println("HERE CROIX");
            cpt = 0;
            pos = new Position(x_rand, y_rand);
            pool = new Position[4];
            pool[0] = new Position(pos.getX()+1, pos.getY()+1);
            pool[1] = new Position(pos.getX()-1, pos.getY()+1);
            pool[2] = new Position(pos.getX()+1, pos.getY()-1);
            pool[3] = new Position(pos.getX()-1, pos.getY()-1);
            return pos;
        } else {

            return pool[cpt++];

        }


    }
}
