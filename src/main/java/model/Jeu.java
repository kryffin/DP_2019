package main.java.model;

import main.java.model.etat.Epoque;
import main.java.model.etat.Epoque1;
import main.java.model.etat.Epoque2;
import main.java.model.fabriqueEpoque.FabriqueEpoque;
import main.java.model.fabriqueEpoque.fabriqueEpoque1.FabriqueEpoque1;
import main.java.model.fabriqueEpoque.fabriqueEpoque2.FabriqueEpoque2;
import main.java.view.ViewManager;

import java.util.List;


public class Jeu {

    private Plateau plateau1;
    private Plateau plateau2;
    private Joueurs joueur1;
    private Joueurs joueur2;
    private Epoque epoque;
    private FabriqueEpoque fabriqueEpoque;
    private ViewManager viewManager;

    private boolean myTurn;
    private boolean finished;

    public void setViewManager (ViewManager vm) {
        this.viewManager = vm;
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

    public void chooseWeapon(Position position) {
        Arme arme;
        if (myTurn) {
            arme = this.plateau1.getArme(position);
        } else {
            arme = this.plateau2.getArme(position);
        }

        if(arme!=null){
            chooseTarget();
        }
    }

    public void chooseTarget(){

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

}
