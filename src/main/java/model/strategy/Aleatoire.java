package main.java.model.strategy;

import main.java.model.Position;
import main.java.model.Tir;
import main.java.model.plateau.bateau.Arme;

import java.util.Random;

public class Aleatoire implements IA {
    @Override
    public Tir comportement() {
        Random rand = new Random();
        int x_rand = rand.nextInt(10);
        int y_rand = rand.nextInt(10);
        //todo choisir l'arme
        return new Tir(Arme.LEGER, new Position(x_rand, y_rand));

    }
}
