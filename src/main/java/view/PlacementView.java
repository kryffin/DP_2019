package main.java.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.controller.Controller;
import main.java.model.image.ImageManager;
import main.java.model.plateau.bateau.Bateau;

import java.util.List;

public class PlacementView {
    private Controller controller;

    @FXML
    private GridPane rightPane;

    @FXML
    private VBox leftPane;

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
                btt.setStyle("\n-fx-background-color: transparent;\n" +
                        "-fx-background-radius: 0;");
                btt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.fireEvent(event);
                    }
                });
                String id = ""+((h*10)+w);
                //btt.setText(id);
                btt.setId(id);
                btt.setContentDisplay(ContentDisplay.TOP);

                rightPane.add(btt,w,h);
            }
        }
        rightPane.setGridLinesVisible(true);

        leftPane = (VBox) scene.lookup("#flottePane");
        List<Bateau> bateaux = controller.getPlateau().getBateaux();
        Button[] buttons = new Button[bateaux.size()];
        int i = 0;
        for (Bateau b : bateaux) {
            buttons[i] = new Button(b.toString());
            leftPane.getChildren().add(buttons[i]);
            i++;
        }

    }
}
