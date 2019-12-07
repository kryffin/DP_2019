package main.java.model.etat;

import main.java.model.plateau.bateau.Arme;
import main.java.model.Position;

import java.util.HashMap;
import java.util.List;

public abstract class Epoque {
    private HashMap<Arme, List<Object>> caracteristiqueArme;
    //en fonction de l'arme, leger, lourd etc qui est la cle
    //on recupere une liste d'objet qui par convention sera
    //objet 1 : integer = degat de l'arme
    //objet 2 : integer = munition de l'arme
    //objet 3 : Position[] = pattern de l'arme


    protected HashMap<Integer, List<List<Object>>> caracteristiquePV;
    //clé : taille
    //premiere liste = version du bateau
    //2nd liste = PV | description des compartiments avec arme
    // 2nd liste . size ()  === taille (clé Integer)

    public Epoque(){
        caracteristiqueArme = new HashMap<>();
        caracteristiquePV = new HashMap<>();
        setLeger();
        setMedium();
        setLourde();
        setAtomique();
        setBateau();
    }

    public void put(Arme a, List<Object> l){
        this.caracteristiqueArme.put(a, l);
    }

    /**
     * essentiellement pour factoriser du code
     * @param a : l'arme
     * @param i : 0 = degat ; 1 = munition<
     * @return le param i de l'arme a
     */
    private int getParam(Arme a, int i) {
        int res = 0;
        List<Object> tmp = caracteristiqueArme.get(a);
        res = (int)tmp.get(i);
        return res;
    }

    public int getDegat(Arme a){
        return getParam(a, 0);
    }

    public int getMunition(Arme a){
        return getParam(a,1);
    }

    public Position[] getPattern(Arme a){
        List<Object> tmp = caracteristiqueArme.get(a);
        return (Position[])tmp.get(2);
    }

    abstract List<Object> setArme(int degat, int munition, Position[] pattern);
    abstract void setLeger();
    abstract void setMedium();
    abstract void setLourde();
    abstract void setAtomique();

    abstract void setBateau();

    public List<List<Object>> getDescription(int taille) {
        List<List<Object>> res = this.caracteristiquePV.get(taille);
        System.out.println("DEMANDE DE TAILLE "+ taille);
        assert(res != null):"BUG EPOQUE";
        return res;
    }
}
