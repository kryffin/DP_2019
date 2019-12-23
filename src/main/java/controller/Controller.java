package main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import main.java.model.*;
import main.java.model.plateau.Plateau;
import main.java.model.plateau.bateau.Bateau;
import main.java.view.ViewManager;

import java.util.List;

import static main.java.model.plateau.bateau.Arme.*;

public class Controller {

    private Jeu jeu ;

    /**
     * le bateau a placer (le dernier selectionné)
     */
    private Bateau aPlacer;

    private boolean isPlacer;

    private ViewManager vm;

    public Controller(Jeu j){
        jeu = j ;
        isPlacer = false;
    }

    public void setPlacementView (ViewManager vm) {
        this.vm = vm;
    }

    public Plateau getPlateau () {
        return jeu.getPlateau();
    }

    public void createShip(int taille, int version, int posList){
        jeu.createShip(taille,version, posList);
        //updateColor();
    }


    public void chooseWeapon(ActionEvent e){
        //de action event e on en tire le bouton qui a une position
        Button b = (Button) e.getSource();
        String name = b.getId().substring(1);
        int tmp = Integer.parseInt(name);

        int col = tmp%10;
        int row = (tmp - col)/10;

        //System.out.println("weapon clicked = " + col + " " + row);

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
                //System.out.println("Placement non autorisé");
            }
        }

        for(int x = 0 ; x < 10 ; x++){
            for(int y = 0 ; y <10 ;  y++) {
                vm.getPlacementView().getButton(x,y).setStyle("-fx-background-color: transparent");
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

        //System.out.println("BATEAU A CHANGER : " + b.toString());

    }

    public void afficherFlotte() {
        for (Bateau b : jeu.getPlateau().getBateaux()){
            //System.out.println(b);
        }
        //System.out.println("=======");
    }

    public void switchToPlateauView() {
        if (jeu.plateauBienForme()) {
            //System.out.println("Plateau bien formé, passage à la vue de jeu");
            jeu.getViewManager().displayPlateauView();
            return;
        }
        //System.out.println("Plateau mal formé, on ne change pas la vue");
    }

    public void recevoirTir(Tir tir) {
        jeu.recevoirTir(tir);
    }

    public void recevoirBilan (Bilan bilan) {
        jeu.recevoirBilan(bilan);
    }

    public void updateColor(){

        if (vm.isPlacement()) {
            updateColorPlacement();
        } else if (vm.isPlateau()) {
            updateColorPlateau();
        }

    }

    private void updateColorPlacement () {
        for(int x = 0 ; x < 10 ; x++){
            for(int y = 0 ; y <10 ;  y++){

                for(Bateau bateau : jeu.getPlateau().getBateaux()){
                    if(bateau.hasCompartiment(new Position(x,y))){
                        //System.out.println(placementView.getButton(x,y));
                        if(bateau.getCompartiment(new Position(x,y)).getArme()==LEGER){
                            vm.getPlacementView().getButton(x,y).setStyle("-fx-background-color: green");
                        } else if(bateau.getCompartiment(new Position(x,y)).getArme()==MOYENNE){
                            vm.getPlacementView().getButton(x,y).setStyle("-fx-background-color: blue");
                        } else if(bateau.getCompartiment(new Position(x,y)).getArme()==LOURDE){
                            vm.getPlacementView().getButton(x,y).setStyle("-fx-background-color: purple");
                        } else if(bateau.getCompartiment(new Position(x,y)).getArme()==ATOMIQUE){
                            vm.getPlacementView().getButton(x,y).setStyle("-fx-background-color: orange");
                        } else{
                            vm.getPlacementView().getButton(x,y).setStyle("-fx-background-color: white");
                        }
                    }
                }
            }
        }
    }

    private void updateColorPlateau () {
        for(int x = 0 ; x < 10 ; x++){
            for(int y = 0 ; y <10 ;  y++){

                for(Bateau bateau : jeu.getPlateau().getBateaux()){
                    if(bateau.hasCompartiment(new Position(x,y))){
                        //System.out.println(placementView.getButton(x,y));
                        if(bateau.getCompartiment(new Position(x,y)).getArme()==LEGER){
                            vm.getPlateauScene().getBoutonJoueur(x,y).setStyle("-fx-background-color: green");
                        } else if(bateau.getCompartiment(new Position(x,y)).getArme()==MOYENNE){
                            vm.getPlateauScene().getBoutonJoueur(x,y).setStyle("-fx-background-color: blue");
                        } else if(bateau.getCompartiment(new Position(x,y)).getArme()==LOURDE){
                            vm.getPlateauScene().getBoutonJoueur(x,y).setStyle("-fx-background-color: purple");
                        } else if(bateau.getCompartiment(new Position(x,y)).getArme()==ATOMIQUE){
                            vm.getPlateauScene().getBoutonJoueur(x,y).setStyle("-fx-background-color: orange");
                        } else{
                            vm.getPlateauScene().getBoutonJoueur(x,y).setStyle("-fx-background-color: white");
                        }
                    }
                }
            }
        }
    }

    /**uniquement appellé pour le CPU*/
    public void play() {
        jeu.play();
    }

    public void update() {

        Position ref = jeu.bilan.getTarget();
        for(Position p : jeu.bilan.getPattern()){
            int x = ref.getX()+p.getX();
            int y = ref.getY()+p.getY();
            if(x>=0 && x<=9 && y>=0 && y<=9){
                vm.getPlateauScene().getBoutonJoueur(x, y).setText("O");
            }
        }


    }

    public void setStrat(int i) {
        System.out.println("SET STRAT + "+ i);
        jeu.setStrat(i);
    }

    public void setWin(boolean finished) {
        if(vm!=null){
            if(finished){ //l'adversaire a perdu on a gagné
                vm.getPlateauView().setWin();
            } else {
                vm.getPlateauView().setLoose();
            }
        }
    }
}
