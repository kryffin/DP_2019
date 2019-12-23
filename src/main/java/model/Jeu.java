package main.java.model;

import main.java.controller.PiloteReseau;
import main.java.model.etat.Epoque;
import main.java.model.etat.Epoque1;
import main.java.model.etat.Epoque2;
import main.java.model.joueur.Humain;
import main.java.model.joueur.Joueur;
import main.java.model.joueur.Machine;
import main.java.model.plateau.PlateauInfo;
import main.java.model.plateau.bateau.Bateau;
import main.java.model.fabriqueEpoque.FabriqueEpoque;
import main.java.model.fabriqueEpoque.fabriqueEpoque1.FabriqueEpoque1;
import main.java.model.fabriqueEpoque.fabriqueEpoque2.FabriqueEpoque2;
import main.java.model.plateau.Plateau;
import main.java.model.plateau.bateau.Arme;

import main.java.model.plateau.bateau.Compartiment;
import main.java.model.strategy.Aleatoire;
import main.java.model.strategy.Croix;
import main.java.model.strategy.IA;
import main.java.view.ViewManager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Jeu {

    private Plateau plateau1;
    private PlateauInfo plateau2;
    private Machine machine;
    private Epoque epoque;
    private FabriqueEpoque fabriqueEpoque;
    private ViewManager viewManager;

    private PiloteReseau piloteReseau;

    private Arme currentArme;
    private Tir tir;

    private boolean myTurn;
    private boolean finished;
    public Bilan bilan;
    private Position armePosition;
    private IA croix;
    private Compartiment compartiment;


    public Jeu(){
        plateau1 = new Plateau();
        plateau2 = new PlateauInfo();
        machine = new Machine();
        myTurn = true;
        finished = false;
        epoque = null;
        croix = new Croix();

    }

    public void update(){
        if(viewManager!=null)
            viewManager.update(this);
    }

    public void setViewManager (ViewManager vm) {
        this.viewManager = vm;
    }

    /**
     * choisi une arme dans à la position
     * @param position
     */
    public void chooseWeapon(Position position) {
        // retire l'affichage de l'ancienne sélection
        viewManager.getPlateauView().unselectPreviousCase();

        // selection d'une arme
        currentArme = plateau1.getArme(position);

        this.compartiment = plateau1.getCompartiment(position);
        if(compartiment!=null){
            viewManager.getPlateauScene().afficherComp(compartiment);
        }
        // affichage de la sélection
        if (currentArme != null && plateau1.getCompartiment(position).getMunition()>0) {
            this.armePosition = position;
            viewManager.getPlateauView().selectCase(position);
        }

    }

    /**
     * tire à la position
     * @param position
     */
    public void chooseTarget(Position position){
        if (this.currentArme != null && compartiment.getMunition()>0){
            //System.out.println("tir avec " + currentArme);
            tir = new Tir(currentArme, position);
            plateau1.getCompartiment(armePosition).decreaseMunition();
            piloteReseau.envoyerTir(tir);

        }
        //System.out.println("aucune arme selectionnée");
    }

    public void recevoirTir(Tir tir){
        // cible du tir
        Position target = tir.getCible();

        // positions du pattern du tir
        Position[] pattern = epoque.getPattern(tir.getArme());

        // construction du bilan à renvoyer
        Bilan bilan = new Bilan(tir.getCible());

        bilan.setPattern(pattern);

        // degats du tir
        int degat = epoque.getDegat(tir.getArme());

        for(Position p : pattern){
            // on initialise le tir à LOUPE
            bilan.setEtat(EtatTir.LOUPE, p);

            // prochaine position par rapport au pattern
            Position newPosition = calculerPosition(target,p);

            // vérification des bateaux
            for(Bateau b : plateau1.getBateaux()){
                // si le bateau est touché par l'attaque
                if(b.hasCompartiment(newPosition)){
                    // on retire de la vie au bateau
                    b.getCompartiment(newPosition).decreaseHP(degat);
                    // si le bateau meurs
                    if(b.isDead()) {
                        // etat du bilan à TOUCHE_COULE
                        bilan.setEtat(EtatTir.TOUCHE_COULE, p);
                    }else if(b.getCompartiment(newPosition).getPv() <= 0){
                            bilan.setEtat(EtatTir.COMP_COULE, p);
                    }else{
                        // sinon etat du bilan à TOUCHE
                        bilan.setEtat(EtatTir.TOUCHE, p);
                    }

                    System.out.println("Compartiment touché : hp = " + b.getCompartiment(newPosition).getPv());
                }
            }
        }

        //ici on va envoyer le bilan de l'attaque reçue à l'adversaire
        this.bilan = bilan;
        update();
        if(!isFinished()) {
            envoyerBilan(bilan);
        } else {
            piloteReseau.envoyerFin(finished);
            viewManager.getPlateauView().setLoose();
        }
    }

    private boolean isFinished() {
        boolean b1 = true;
        boolean b2 = true;

        //check si on a encore un bateau
        for(Bateau b : plateau1.getBateaux()){
            if(!b.isDead()) {
                b1 = false;
            }
        }

        //check si on a encore des munitions
        for (Bateau b : plateau1.getBateaux()){
            for(int i = 0; i < b.getNbCompartiement(); i++){
                if(b.getCompartiment(i).getMunition() > 0){
                    b2 = false;
                }
            }
        }
        finished = b1 || b2;
        return finished;
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
        if (epoque == null){
            if (choix == 0) {
                epoque = new Epoque1();
                fabriqueEpoque = new FabriqueEpoque1(epoque);
            } else {
                epoque = new Epoque2();
                fabriqueEpoque = new FabriqueEpoque2(epoque);
            }

            piloteReseau.renseignerEpoque(choix);
            //System.out.println("Epoque choisie : " + epoque);
            creerFlotte();
            //System.out.println("Flotte créée");
            if(viewManager!=null) //gere le cas ou c'est le CPU qui renseigne son epoque
                viewManager.displayPlacementView();
        }
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
        do{
            Random rand = new Random();
            for(int i=0; i < bateaux.size(); i++){
                bateaux.get(i).setPosition(rand.nextInt(9-i), rand.nextInt(9) );
            }
            System.out.println("mal placé");
        } while (!plateauBienForme());
    }

    public Plateau getPlateau () {
        return plateau1;
    }

    public List<List<Object>> getDescription(int taille) {
        return epoque.getDescription(taille);
    }

    public void createShip(int taille, int version, int posList) {
        //System.out.println("JE CREER UN BATEAU DE TAILLE" + taille + " A LA VERSION " + version);
        List<Bateau> listBateau = plateau1.getBateaux();
        List<Position> positions = listBateau.get(posList).getPositions();
        listBateau.set(posList,fabriqueEpoque.creerBateau(taille, version));
        Bateau aModif = listBateau.get(posList);
        aModif.setPosition(positions.get(0).getX(), positions.get(0).getY());
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public void recevoirBilan (Bilan bilan) {
        //ici on va étudier le bilan de notre attaque pour mettre à jour le plateau d'informations (celui de droite)

        // positions ayant été attaquées
        Position[] pos = new Position[bilan.getPattern().length];

        // parcours du pattern pour créer les positions
        for (int i = 0; i < bilan.getPattern().length; i++) {
            // positions ayant été touchées
            pos[i] = new Position(bilan.getTarget().getX() + bilan.getPattern()[i].getX(), bilan.getTarget().getY() + bilan.getPattern()[i].getY());
        }

        // parcours des états et envoie à la vue
        if(viewManager!=null){
            for (int i = 0; i < bilan.getEtats().length; i++) {
                viewManager.getPlateauView().changeButtonTo(pos[i], bilan.getEtats()[i]);
            }
            piloteReseau.passerTour();
        }

    }

    public void envoyerBilan (Bilan etats) {
        //ici on va envoyer le bilan au pilote pour qu'il le forward à l'
        //System.out.println("== envoie du bilan à l'adversaire == ");
        piloteReseau.envoyerBilan(etats);
        //System.out.println("Passage de tour");

        return;
    }

    public void setPiloteReseau (PiloteReseau pilote) {
        this.piloteReseau = pilote;
    }

    public boolean plateauBienForme () {
        return plateau1.plateauBienForme();
    }

    /**uniquement appelé pour le CPU*/
    public void play() {
        Arme arme = plateau1.getRandomArme();
        Position target = machine.getTarget();
        piloteReseau.envoyerTir(new Tir(arme, target));


    }

    public void setStrat(int i) {
        if(viewManager==null){
            switch (i){
                case 1:
                    machine.setComportement(croix);
                    break;
                case 2:
                    machine.setComportement(new Aleatoire());
                    break;
            }
        } else {
            switch (i){
                case 1:
                    piloteReseau.setComportement(i);
                    break;
                case 2:
                    piloteReseau.setComportement(i);
                    break;
            }
        }
    }

    public Compartiment getCompartiment() {
        return compartiment;
    }
}