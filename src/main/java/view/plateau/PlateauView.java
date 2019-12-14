package main.java.view.plateau;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.java.controller.Controller;
import main.java.model.image.ImageManager;

public class PlateauView extends AnchorPane {
    private Controller controller;
    @FXML
    private GridPane rightPane;
    @FXML
    private GridPane leftPane;

    private Button[][] boutonsJoueur;

    public PlateauView(Controller c){
        this.controller = c;
        boutonsJoueur = new Button[10][10];
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

                Button bttAdv = new Button();
                bttAdv.setPrefSize(40.0,40.0);
                bttAdv.setStyle("-fx-background-color: transparent; -fx-background-radius: 0");
                bttAdv.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.tirer(event);
                    }
                });
                String id2 = "A"+((h*10)+w);
                //bttAdv.setText(id2);
                bttAdv.setId(id2);
                rightPane.add(bttAdv, w,h);
            }
        }

        System.out.println(leftPane.getChildren().size());
        rightPane.setGridLinesVisible(true);
        leftPane.setGridLinesVisible(true);
        rightPane.setVisible(true);
        leftPane.setVisible(true);

        System.out.println("end");
        System.out.println(leftPane.getChildren().size());
        System.out.println(rightPane.getChildren().size());
    }

    public Button getBoutonJoueur (int x, int y) {
        return boutonsJoueur[x][y];
    }

}
