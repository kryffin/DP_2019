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
}
