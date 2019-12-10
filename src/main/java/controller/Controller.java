package main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import main.java.model.EtatTir;
import main.java.model.Jeu;
import main.java.model.Tir;
import main.java.model.plateau.Plateau;
import main.java.model.Position;
import main.java.model.plateau.bateau.Bateau;
import main.java.model.plateau.bateau.Compartiment;
import main.java.view.ViewManager;
import main.java.view.placement.PlacementView;

import java.util.List;

import static main.java.model.plateau.bateau.Arme.*;

public class Controller {

    private Jeu jeu ;

    /**
     * le bateau a placer (le dernier selectionné)
     */
    private Bateau aPlacer;

    private boolean isPlacer;
    private PlacementView placementView ;

    public Controller(Jeu j){
        jeu = j ;
        isPlacer = false;
    }

    public void setViewManager(ViewManager v){
        this.placementView = v.getPlacementView();
    }
    public Plateau getPlateau () {
        return jeu.getPlateau();
    }

    public void createShip(int taille, int version, int posList){
        jeu.createShip(taille,version, posList);
        updateColor();

    }


    public void chooseWeapon(ActionEvent e){
        //de action event e on en tire le bouton qui a une position
        Button b = (Button) e.getSource();
        String name = b.getId().substring(1);
        int tmp = Integer.parseInt(name);

        int col = tmp%10;
        int row = (tmp - col)/10;

        Position position = new Position(col, row);
        jeu.chooseWeapon(position);

    }

    public void chooseEpoque (int e) {
        jeu.choixEpoque(e);
    }

    /**
     * les différents boutons du plateaux sont liés à cette fonction
     * permet de placer le bateau en attribut
     * @param actionEvent
     */
    public void placer(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        int tmp = Integer.parseInt(b.getId());
        int col = tmp%10;
        int row = (tmp - col)/10;

        if(isPlacer){
            int taille = aPlacer.getNbCompartiement();
            if(col+taille <= 10){
                aPlacer.setPosition(col, row);
                afficherFlotte();
            } else {
                System.out.println("Placement non autorisé");
            }
        }

        for(int x = 0 ; x < 10 ; x++){
            for(int y = 0 ; y <10 ;  y++) {
                placementView.getButton(x,y).setStyle("-fx-background-color: transparent");
            }
        }

        updateColor();

    }

    public void tirer(ActionEvent e) {
        //de action event e on en tire le bouton qui a une position
        Button b = (Button) e.getSource();
        String name = b.getId().substring(1);
        int tmp = Integer.parseInt(name);

        int col = tmp%10;
        int row = (tmp - col)/10;

        //todo tirer a la position avec l'arme sauvegarder au préalable
        Position position = new Position(col, row);

        jeu.chooseTarget(position);

    }


    public List<List<Object>> getDescription(int taille) {
        return jeu.getDescription(taille);
    }

    public void setAPlacer(int positionFlotte) {
        isPlacer = true;
        Bateau b = jeu.getPlateau().getBateaux().get(positionFlotte);
        this.aPlacer = b;

        System.out.println("BATEAU A CHANGER : " + b.toString());

    }

    public void afficherFlotte() {
        for (Bateau b : jeu.getPlateau().getBateaux()){
            System.out.println(b);
        }
        System.out.println("=======");
    }

    public void switchToPlateauView() {
        jeu.getViewManager().displayPlateauView();
    }

    public void shoot (Tir tir) {
        jeu.shoot(tir);
    }

    public void recevoirBilan (EtatTir[] etats) {
        jeu.recevoirBilan(etats);
    }
    public void updateColor(){
        for(int x = 0 ; x < 10 ; x++){
            for(int y = 0 ; y <10 ;  y++){

                for(Bateau bateau : jeu.getPlateau().getBateaux()){
                    if(bateau.hasCompartiment(new Position(x,y))){
                        System.out.println(placementView);
                        //System.out.println(placementView.getButton(x,y));
                        if(bateau.getCompartiment(new Position(x,y)).getArme()==LEGER){
                            placementView.getButton(x,y).setStyle("-fx-background-color: green");
                        } else if(bateau.getCompartiment(new Position(x,y)).getArme()==MOYENNE){
                            placementView.getButton(x,y).setStyle("-fx-background-color: blue");
                        } else if(bateau.getCompartiment(new Position(x,y)).getArme()==LOURDE){
                            placementView.getButton(x,y).setStyle("-fx-background-color: purple");
                        } else if(bateau.getCompartiment(new Position(x,y)).getArme()==ATOMIQUE){
                            placementView.getButton(x,y).setStyle("-fx-background-color: orange");
                        } else{
                            placementView.getButton(x,y).setStyle("-fx-background-color: white");
                        }
                    }
                }
            }
        }

    }

}
