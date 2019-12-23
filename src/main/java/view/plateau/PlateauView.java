package main.java.view.plateau;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.java.controller.Controller;
import main.java.model.EtatTir;
import main.java.model.Position;
import main.java.model.image.ImageManager;
import main.java.model.plateau.bateau.Compartiment;

import java.util.List;

public class PlateauView extends AnchorPane {
    private Controller controller;
    @FXML
    private GridPane rightPane;
    @FXML
    private GridPane leftPane;

    private Button[][] boutonsJoueur;
    private Button[][] boutonsAdversaire;

    @FXML
    private Label compStat;

    @FXML
    private Label endGame;

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
        Button croix = (Button) scene.lookup("#stratCroix");
        Button rand = (Button) scene.lookup("#stratRand");
        compStat = (Label) scene.lookup("#compStat");
        endGame = (Label) scene.lookup("#endGame");
        croix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.setStrat(1);
            }
        });

        rand.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.setStrat(2);
            }
        });


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

        //System.out.println(leftPane.getChildren().size());
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
        if(p.getX()<0 || p.getX() > 9 || p.getY() <0 || p.getY()>9){
            return;
        }
        switch (et) {
            case TOUCHE_COULE:
                for(int i=0; i < 4; i++){ //colorie tout le bateau en noir
                    if(p.getX()+i >= 10 || p.getX()-i < 0){
                        break;
                    }
                    if(boutonsAdversaire[p.getX()-i][p.getY()].getStyle().equals("-fx-background-color: red") ||
                            boutonsAdversaire[p.getX()-i][p.getY()].getStyle().equals("-fx-background-color: purple")){
                        boutonsAdversaire[p.getX()-i][p.getY()].setStyle("-fx-background-color: black");
                    }

                    if(boutonsAdversaire[p.getX()+i][p.getY()].getStyle().equals("-fx-background-color: red") ||
                            boutonsAdversaire[p.getX()+i][p.getY()].getStyle().equals("-fx-background-color: purple")){
                        boutonsAdversaire[p.getX()+i][p.getY()].setStyle("-fx-background-color: black");
                    }
                }
                break;

            case TOUCHE:
                boutonsAdversaire[p.getX()][p.getY()].setStyle("-fx-background-color: red");
                break;

            case LOUPE:
                boutonsAdversaire[p.getX()][p.getY()].setStyle("-fx-background-color: blue");
                break;

            case COMP_COULE:
                boutonsAdversaire[p.getX()][p.getY()].setStyle("-fx-background-color: purple");
                break;
            default:
                break;
        }

    }

    public void afficherComp(Compartiment compartiment) {
        compStat.setText(compartiment.toString());
    }

    public void setWin() {
        this.endGame.setText("YOU WIN !!");

    }

    public void setLoose() {
        this.endGame.setText("YOU LOOSE !!");

    }

    public void afficherDeadComp(List<Compartiment> deadComp) {
        for(Compartiment c : deadComp){
            boutonsJoueur[c.getPosition().getX()][c.getPosition().getY()].setStyle("-fx-background-color: black");
            boutonsJoueur[c.getPosition().getX()][c.getPosition().getY()].setText("KO");
        }
    }
}
