package main.java.model.fabriqueEpoque.fabriqueEpoque2;

import main.java.model.etat.Epoque;
import main.java.model.plateau.bateau.Arme;
import main.java.model.plateau.bateau.Bateau;
import main.java.model.fabriqueEpoque.FabriqueEpoque;

import java.util.ArrayList;
import java.util.List;

public class FabriqueEpoque2 implements FabriqueEpoque {

    private final Epoque e;

    public FabriqueEpoque2(Epoque e){
        this.e = e;
    }
    public List<Bateau> creerFlotte () {
        List<Bateau> bateaux = new ArrayList<>();

        bateaux.add(creerBateau1());
        bateaux.add(creerBateau2V2());
        bateaux.add(creerBateau4V2());
        bateaux.add(creerBateau4V3());

        return bateaux;
    }

    public Bateau creerBateau1 () {
        return new Bateau(1, 15, e, Arme.LEGER);
    }

    public Bateau creerBateau2 () {
        return new Bateau(2, 30, e, Arme.LEGER, Arme.LEGER);
    }

    public Bateau creerBateau2V2 () {
        return new Bateau(2, 30, e, Arme.MOYENNE, null);
    }

    public Bateau creerBateau3 () {
        return new Bateau(3, 45, e, Arme.LEGER, Arme.MOYENNE, Arme.LEGER);
    }

    public Bateau creerBateau3V2 () {
        return new Bateau(3, 45, e, Arme.MOYENNE, Arme.MOYENNE, null);
    }

    public Bateau creerBateau3V3 () {
        return new Bateau(3, 45, e,  null, Arme.LOURDE, null);
    }

    public Bateau creerBateau4 () {
        return new Bateau(4, 60, e,  Arme.ATOMIQUE, null, null, null);
    }

    public Bateau creerBateau4V2 () {
        return new Bateau(4, 60, e, Arme.LOURDE, Arme.MOYENNE, null, null);
    }

    public Bateau creerBateau4V3 () {
        return new Bateau(4, 60, e, Arme.LEGER, Arme.MOYENNE, Arme.MOYENNE, Arme.LEGER);
    }

    //todo
    @Override
    public Bateau creerBateau(int taille, int version) {
        switch (taille){
            case 1:
                return creerBateau1();
            case 2:
                if(version==0){
                    return creerBateau2();
                } else if (version==1){
                    return creerBateau2V2();
                }
                break;
            case 3:
                if(version==0){
                    return creerBateau3();
                } else if (version==1){
                    return creerBateau3V2();
                } else if (version==2){
                    return creerBateau3V3();
                }
                break;
            case 4:
                if(version==0){
                    return creerBateau4();
                } else if (version==1){
                    return creerBateau4V2();
                } else if (version==2){
                    return creerBateau4V3();
                }
                break;

            default:
                return null;
        }
        return null;
    }


}
