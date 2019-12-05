package main.java.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.java.controller.Controller;

public class PlateauView extends AnchorPane {
    private Controller controller;
    @FXML
    private GridPane rightPane;
    @FXML
    private GridPane leftPane;

    public PlateauView(Controller c){
        this.controller = c;

    }

    @FXML
    public void initialize(Scene scene) {
        int i = 0;
        rightPane = (GridPane) scene.lookup("#adversaire");
        leftPane = (GridPane) scene.lookup("#joueur");


        for(int w = 0; w<10; w++){
            for(int h = 0; h<10; h++){

                //======boutons du joueur============
                Button bttJoueur = new Button();
                bttJoueur.setPrefSize(40.0,40.0);
                bttJoueur.setStyle("-fx-background-color: #0000FF; -fx-background-radius: 0");
                bttJoueur.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.chooseWeapon(event);
                    }
                });
                String id = "J"+((h*10)+w);
                bttJoueur.setText(id);
                bttJoueur.setId(id);
                leftPane.add(bttJoueur, w,h);
                //=====================================

                //======boutons plateau adversaire============

                Button bttAdv = new Button();
                bttAdv.setPrefSize(40.0,40.0);
                bttAdv.setStyle("-fx-background-color: #0000F1; -fx-background-radius: 0");
                bttAdv.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.tirer(event);
                    }
                });
                String id2 = "A"+((h*10)+w);
                bttAdv.setText(id2);
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
}
