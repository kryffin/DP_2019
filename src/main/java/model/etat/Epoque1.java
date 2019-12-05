package main.java.model.etat;

import main.java.model.plateau.bateau.Arme;
import main.java.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Epoque1 extends Epoque {
    /**
     * constructeur
     */
    public Epoque1(){
        super();
        setLeger();
        setMedium();
        setLourde();
        setAtomique();
    }

    /**
     * factorise le code, étant donné que la hashmap contient <Arme, List<Object>>
     * ici on "creer" les attributs des armes
     * @param degat : dégats de l'arme
     * @param munition : munitions de l'arme
     * @param pattern : patterne de l'arme
     * @return : une liste <degat,munition,pattern> représentant l'arme
     */
    @Override
     List<Object> setArme(int degat, int munition, Position[] pattern) {
        List<Object> carac = new ArrayList<>();
        carac.add(degat);
        carac.add(munition);
        carac.add(pattern);

        return carac;
    }

    @Override
    void setLeger() {
        Position[] pattern = new Position[1];
        pattern[0] = new Position(0,0);
        List<Object> tmp = setArme(1, 20, pattern);

        super.put(Arme.LEGER, tmp);
    }

    @Override
    void setMedium() {
        List<Object> medium = new ArrayList<>();
        medium.add(3);
        medium.add(10);
        Position[] tmp = new Position[1];
        tmp[0] = new Position(0,0);
        medium.add(tmp);

        super.put(Arme.MOYENNE, medium);
    }

    @Override
    void setLourde() {
        List<Object> lourde = new ArrayList<>();
        lourde.add(5);
        lourde.add(7);
        Position[] tmp = new Position[1];
        tmp[0] = new Position(0,0);
        lourde.add(tmp);

        super.put(Arme.LOURDE, lourde);
    }

    @Override
    void setAtomique() {
        List<Object> atomique = new ArrayList<>();
        atomique.add(1);
        atomique.add(20);
        atomique.add(new Position(0,0));

        super.put(Arme.ATOMIQUE, atomique);
    }

    @Override
    public String toString() {
        return "Nazi";
    }
}
