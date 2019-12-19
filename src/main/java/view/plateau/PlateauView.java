package main.java.view.plateau;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.java.controller.Controller;
import main.java.model.EtatTir;
import main.java.model.Position;
import main.java.model.image.ImageManager;

public class PlateauView extends AnchorPane {
    private Controller controller;
    @FXML
    private GridPane rightPane;
    @FXML
    private GridPane leftPane;

    private Button[][] boutonsJoueur;
    private Button[][] boutonsAdversaire;

    public PlateauView(Controller c){
        this.controller = c;
        boutonsJoueur = new Button[10][10];
        boutonsAdversaire = new Button[10][10];
    }

    @FXML
    public void initialize(Scene scene) {
        int i = 0;
        rightPane = (GridPane) scene.lookup("#adversaire");
        leftPane = (GridPane) scene.lookup("#joueur");


        for(int h = 0; h<10; h++){
            for(int w = 0; w<10; w++){

                //======boutons du joueur============
                boutonsJoueur[w][h] = new Button();
                boutonsJoueur[w][h].setPrefSize(40.0,40.0);
                boutonsJoueur[w][h].setStyle("-fx-background-color: transparent; -fx-background-radius: 0");
                boutonsJoueur[w][h].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.chooseWeapon(event);
                    }
                });
                String id = "J"+((h*10)+w);
                //bttJoueur.setText(id);
                boutonsJoueur[w][h].setId(id);
                leftPane.add(boutonsJoueur[w][h], w,h);
                //=====================================

                //======boutons plateau adversaire============

                boutonsAdversaire[w][h] = new Button();
                boutonsAdversaire[w][h].setPrefSize(40.0,40.0);
                boutonsAdversaire[w][h].setStyle("-fx-background-color: transparent; -fx-background-radius: 0");
                boutonsAdversaire[w][h].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.tirer(event);
                    }
                });
                String id2 = "A"+((h*10)+w);
                //bttAdv.setText(id2);
                boutonsAdversaire[w][h].setId(id2);
                rightPane.add(boutonsAdversaire[w][h], w,h);
            }
        }

        System.out.println(leftPane.getChildren().size());
        rightPane.setGridLinesVisible(true);
        leftPane.setGridLinesVisible(true);
        rightPane.setVisible(true);
        leftPane.setVisible(true);

        //System.out.println("end");
        //System.out.println(leftPane.getChildren().size());
        //System.out.println(rightPane.getChildren().size());
    }

    public Button getBoutonJoueur (int x, int y) {
        return boutonsJoueur[x][y];
    }

    private Position selectedArme;

    public void selectCase (Position p) {
        selectedArme = p;
        boutonsJoueur[p.getX()][p.getY()].setText("X");
    }

    public void unselectPreviousCase () {
        if (selectedArme == null) {
            return;
        }
        boutonsJoueur[selectedArme.getX()][selectedArme.getY()].setText("");
    }

    public void changeButtonTo (Position p, EtatTir et) {
        switch (et) {
            case TOUCHE_COULE:
                boutonsAdversaire[p.getX()][p.getY()].setStyle("-fx-background-color: black");
                break;

            case TOUCHE:
                boutonsAdversaire[p.getX()][p.getY()].setStyle("-fx-background-color: red");
                break;

            case LOUPE:
                boutonsAdversaire[p.getX()][p.getY()].setStyle("-fx-background-color: blue");
                break;

            default:
                break;
        }

    }

}
