package main.java.model.etat;

import main.java.model.Arme;
import main.java.model.Position;

import java.util.List;

public class Epoque2 extends Epoque {

    public Epoque2(){
        super();
        setLeger();
        setMedium();
        setLourde();
        setAtomique();
    }

    @Override
    List<Object> setArme(int degat, int munition, Position[] pattern) {
        return null;
    }

    @Override
    void setLeger() {

    }

    @Override
    void setMedium() {

    }

    @Override
    void setLourde() {

    }

    @Override
    void setAtomique() {

    }
}
