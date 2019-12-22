package main.java.model.strategy;

import main.java.model.Position;
import main.java.model.Tir;
import main.java.model.plateau.bateau.Arme;

import java.util.Random;

public class Croix implements IA {
    @Override
    public Position comportement() {
        //todo
        Random rand = new Random();
        int x_rand = rand.nextInt(10);
        int y_rand = rand.nextInt(10);

        Position p1 = new Position(0,0);
        //todo choisir l'arme
        return new Position(x_rand, y_rand);

    }
}
