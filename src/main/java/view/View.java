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
import main.java.controller.Controller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class View  extends GridPane {
    private Controller controller;
    @FXML
    private GridPane rightPane;

    public View (Controller controller){
        this.controller = controller;
    }

    @FXML
    public void initialize(Scene scene){
        int i = 0;
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

    }
}
