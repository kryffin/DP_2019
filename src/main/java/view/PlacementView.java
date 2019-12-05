package main.java.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.controller.Controller;
import main.java.model.Bateau;
import main.java.model.Plateau;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlacementView extends GridPane {
    private Controller controller;

    @FXML
    private GridPane rightPane;

    @FXML
    private GridPane leftPane;

    public PlacementView(Controller controller){
        this.controller = controller;
    }

    @FXML
    public void initialize(Scene scene){
        rightPane = (GridPane) scene.lookup("#gridPane");

        for(int w = 0; w<10; w++){
            for(int h = 0; h<10; h++){
                Button btt = new Button();
                btt.setPrefSize(70.0,70.0);
                btt.setStyle("-fx-background-color: #0000FF; -fx-background-radius: 0");
                btt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.fireEvent(event);
                    }
                });
                String id = ""+((h*10)+w);
                btt.setText(id);
                btt.setId(id);
                rightPane.add(btt, w,h);
            }
        }

        leftPane = (GridPane) scene.lookup("#flottePane");
        List<Bateau> bateaux = controller.getPlateau().getBateaux();
        Label[] labels = new Label[bateaux.size()];
        int i = 0;
        for (Bateau b : bateaux) {
            labels[i] = new Label(b.toString());
            leftPane.add(labels[i], 1, i);
            i++;
        }

    }
}
