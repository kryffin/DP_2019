package main.java.model.strategy;

import main.java.model.Position;

import java.util.Random;

public class Aleatoire implements IA {
    @Override
    public Position comportement() {
        Random rand = new Random();
        int x_rand = rand.nextInt(10);
        int y_rand = rand.nextInt(10);
        return new Position(x_rand, y_rand);

    }
}
