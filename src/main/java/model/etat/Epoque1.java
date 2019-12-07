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

    /**
     * ne regarder pas le code
     * ça remplit la description des bateaux en fonction des tailles et des versions
     * utile pour la vu
     * posez des questions si vous avez un doute
     */
    @Override
    void setBateau() {

        //taille 1 :
        List<List<Object>> res = new ArrayList<>(); //les differentes versions
        List<Object> tmp = new ArrayList<>(); //PV | description des compartiments avec arme
        tmp.add(5);
        tmp.add(Arme.LEGER);
        res.add(tmp);
        caracteristiquePV.put(1, res);

        //reset
        res = new ArrayList<>();
        tmp = new ArrayList<>();

        //taille 2:
        //version 1:
        tmp.add(10);
        tmp.add(Arme.LEGER);
        tmp.add(Arme.LEGER);

        res.add(tmp);


        //reset
        tmp = new ArrayList<>();

        //version 2:
        tmp.add(10);
        tmp.add(Arme.MOYENNE);
        tmp.add(null);

        res.add(tmp);

        caracteristiquePV.put(2, res);

        //reset
        res = new ArrayList<>();
        tmp = new ArrayList<>();

        //taille 3:
        //version 1
        tmp.add(15);
        tmp.add(Arme.LEGER);
        tmp.add(Arme.MOYENNE);
        tmp.add(Arme.LEGER);

        res.add(tmp);

        tmp = new ArrayList<>();
        //version 2
        tmp.add(15);
        tmp.add(Arme.MOYENNE);
        tmp.add(Arme.MOYENNE);
        tmp.add(null);

        res.add(tmp);
        tmp = new ArrayList<>();

        //version 3
        tmp.add(15);
        tmp.add(null);
        tmp.add(Arme.LOURDE);
        tmp.add(null);

        res.add(tmp);
        tmp = new ArrayList<>();

        caracteristiquePV.put(3, res);

        res = new ArrayList<>();

        //taille 4
        //version 1
        tmp.add(20);
        tmp.add(Arme.ATOMIQUE);
        tmp.add(null);
        tmp.add(null);
        tmp.add(null);


        res.add(tmp);

        tmp = new ArrayList<>();
        //version 2
        tmp.add(20);
        tmp.add(Arme.LOURDE);
        tmp.add(Arme.MOYENNE);
        tmp.add(null);
        tmp.add(null);

        res.add(tmp);
        tmp = new ArrayList<>();

        //version 3
        tmp.add(20);
        tmp.add(Arme.LEGER);
        tmp.add(Arme.MOYENNE);
        tmp.add(Arme.MOYENNE);
        tmp.add(Arme.LEGER);

        res.add(tmp);
        caracteristiquePV.put(4, res);

    }


    @Override
    public String toString() {
        return "World War 2";
    }
}
