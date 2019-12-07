package main.java.model.etat;

import main.java.model.plateau.bateau.Arme;
import main.java.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Epoque2 extends Epoque {

    public Epoque2(){
        super();

    }

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
        List<Object> tmp = setArme(5, 25, pattern);

        super.put(Arme.LEGER, tmp);
    }

    @Override
    void setMedium() {
        Position[] pattern = new Position[1];
        pattern[0] = new Position(0,0);
        List<Object> tmp = setArme(10, 7, pattern);

        super.put(Arme.MOYENNE, tmp);

    }

    @Override
    void setLourde() {
        Position[] pattern = new Position[4];
        pattern[0] = new Position(0,0);
        pattern[1] = new Position(0,1);
        pattern[2] = new Position(1, 0);
        pattern[3] = new Position(1,1);

        /*
            [ ][ ]
            [ ][ ]
         */
        List<Object> tmp = setArme(15, 5, pattern);

        super.put(Arme.LOURDE, tmp);
    }

    @Override
    void setAtomique() {
        Position[] pattern = new Position[9];
        pattern[0] = new Position(0,0);
        pattern[1] = new Position(0,1);
        pattern[2] = new Position(0, -1);
        pattern[3] = new Position(1, 0);
        pattern[4] = new Position(1,1);
        pattern[5] = new Position(1, -1);
        pattern[6] = new Position(-1, 0);
        pattern[7] = new Position(-1, 1);
        pattern[8] = new Position(-1, -1);

        /*
            [ ][ ][ ]
            [ ][ ][ ]
            [ ][ ][ ]
         */
        List<Object> tmp = setArme(40, 1, pattern);

        super.put(Arme.ATOMIQUE, tmp);
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
        tmp.add(15);
        tmp.add(Arme.LEGER);
        res.add(tmp);
        caracteristiquePV.put(1, res);

        //reset
        res = new ArrayList<>();
        tmp = new ArrayList<>();

        //taille 2:
        //version 1:
        tmp.add(30);
        tmp.add(Arme.LEGER);
        tmp.add(Arme.LEGER);

        res.add(tmp);


        //reset
        tmp = new ArrayList<>();

        //version 2:
        tmp.add(30);
        tmp.add(Arme.MOYENNE);
        tmp.add(null);

        res.add(tmp);

        caracteristiquePV.put(2, res);

        //reset
        res = new ArrayList<>();
        tmp = new ArrayList<>();

        //taille 3:
        //version 1
        tmp.add(45);
        tmp.add(Arme.LEGER);
        tmp.add(Arme.MOYENNE);
        tmp.add(Arme.LEGER);

        res.add(tmp);

        tmp = new ArrayList<>();
        //version 2
        tmp.add(45);
        tmp.add(Arme.MOYENNE);
        tmp.add(Arme.MOYENNE);
        tmp.add(null);

        res.add(tmp);
        tmp = new ArrayList<>();

        //version 3
        tmp.add(45);
        tmp.add(null);
        tmp.add(Arme.LOURDE);
        tmp.add(null);

        res.add(tmp);
        tmp = new ArrayList<>();

        caracteristiquePV.put(3, res);

        res = new ArrayList<>();

        //taille 4
        //version 1
        tmp.add(60);
        tmp.add(Arme.ATOMIQUE);
        tmp.add(null);
        tmp.add(null);
        tmp.add(null);


        res.add(tmp);

        tmp = new ArrayList<>();
        //version 2
        tmp.add(60);
        tmp.add(Arme.LOURDE);
        tmp.add(Arme.MOYENNE);
        tmp.add(null);
        tmp.add(null);

        res.add(tmp);
        tmp = new ArrayList<>();

        //version 3
        tmp.add(60);
        tmp.add(Arme.LEGER);
        tmp.add(Arme.MOYENNE);
        tmp.add(Arme.MOYENNE);
        tmp.add(Arme.LEGER);

        res.add(tmp);
        caracteristiquePV.put(4, res);

    }

    @Override
    public String toString() {
        return "1600 AC";
    }
}
