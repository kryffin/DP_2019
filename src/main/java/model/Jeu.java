package main.java.model;

import main.java.controller.ControllerReseau;
import main.java.model.etat.Epoque;
import main.java.model.etat.Epoque1;
import main.java.model.etat.Epoque2;
import main.java.model.fabriqueEpoque.FabriqueEpoque;
import main.java.model.fabriqueEpoque.fabriqueEpoque1.FabriqueEpoque1;
import main.java.model.fabriqueEpoque.fabriqueEpoque2.FabriqueEpoque2;
import main.java.model.joueur.Joueur;
import main.java.model.plateau.Plateau;
import main.java.model.plateau.bateau.Arme;
import main.java.model.plateau.bateau.Bateau;
import main.java.view.ViewManager;

import java.rmi.RemoteException;
import java.util.List;

public class Jeu {
    private Plateau plateau1;
    private Plateau plateau2;
    private Joueur joueur1;
    private Joueur joueur2;
    private Epoque epoque;
    private FabriqueEpoque fabriqueEpoque;
    private ViewManager viewManager;

    private ControllerReseau cr;

    private boolean myTurn;
    private boolean finished;
    private enum etatTir {
        TOUCHER,
        PLOUF,
        TOUCHERCOULER
    };


    public void setViewManager (ViewManager vm) {
        this.viewManager = vm;
        try {
            cr = new ControllerReseau();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // myTurn a true : c'est le tour du joueur1 (humain)
    // myTurn a false : c'est le tour du joueur2 (machine)
    public Arme getArme(Position position){

        Arme arme;
        if (myTurn){
            arme = this.plateau1.getArme(position);
        }else{
            arme = this.plateau2.getArme(position);
        }

        return arme;
    }

    public Arme chooseWeapon(Position position) {
     return  plateau1.getArme(position);

    }

    public void chooseTarget(Position position){

    }

    public EtatTir[] shoot(Tir tir){
        Position target = tir.getCible();
        Position[] patterns = epoque.getPattern(tir.getArme());
        EtatTir[] etat = new EtatTir[patterns.length];
        int degat = epoque.getDegat(tir.getArme());
        int cpt = 0;
        for(Position p : patterns){
            etat[cpt] = EtatTir.LOUPE;
            Position newPosition = calculerPosition(target,p);
            for(Bateau b : plateau1.getBateaux()){
                if(b.hasCompartiment(newPosition)){
                    b.getCompartiment(newPosition).decreaseHP(degat);
                    if(b.isDead()){
                        etat[cpt]=EtatTir.TOUCHE_COULE;
                    }else{
                        etat[cpt]= EtatTir.TOUCHE;
                    }
                }
            }
            cpt++;
        }
        return etat;
    }

    public Position calculerPosition(Position posTarget, Position pattern){
        int x = posTarget.getX() + pattern.getX();
        int y = posTarget.getY() + pattern.getY();
        return new Position(x,y);
    }

    /**
     * Choisi l'époque de la partie, l'époque doit être choisie au départ
     * @param choix epoque à choisir, 0 = époque1, 1 = époque2
     */
    public void choixEpoque (int choix) {
        if (choix == 0) {
            epoque = new Epoque1();
            fabriqueEpoque = new FabriqueEpoque1();
        } else {
            epoque = new Epoque2();
            fabriqueEpoque = new FabriqueEpoque2();
        }
        System.out.println("Epoque choisie : " + epoque);
        creerFlotte();
        System.out.println("Flotte créée");
        viewManager.displayPlacementView();
    }

    public void creerFlotte () {
        // création de la flotte par rapport à l'époque
        List<Bateau> bateaux = fabriqueEpoque.creerFlotte();

        // affectation des armes aux bateaux
        for (int i = 0; i < bateaux.size(); i++) {
            bateaux.get(i).initArmes(epoque);
        }

        plateau1 = new Plateau();
        plateau1.setBateaux(bateaux);
    }

    public Plateau getPlateau () {
        return plateau1;
    }

}
