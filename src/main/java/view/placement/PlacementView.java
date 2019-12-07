package main.java.view.placement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.controller.Controller;
import main.java.model.plateau.bateau.Bateau;
import main.java.view.choixBateau.PopUp;
import main.java.view.epoque.EpoqueView;

import java.io.IOException;
import java.util.List;

public class PlacementView {
    private Controller controller;

    @FXML
    private GridPane rightPane;

    @FXML
    private VBox leftPane;




    private Stage popUpStage;
    private PopUp popUp;
    private Scene popUpScene;

    public PlacementView(Controller controller){
        this.controller = controller;
        popUpStage = new Stage();

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
            buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int taille = b.getNbCompartiement();
                    initPopUp(taille);


                    popUpStage.setTitle("Details bateau taille "+taille);
                    popUpStage.setScene(popUpScene);
                    popUpStage.show();

                }

            });
            leftPane.getChildren().add(buttons[i]);
            i++;
        }


    }
    public void initPopUp (int taille) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("popUp.fxml"));
            popUpScene = new Scene(root, 800,600);
            //popUpScene.getStylesheets().addAll(this.getClass().getResource("../view/style.css").toExternalForm());

            /*build de la vue elle connait le controlleur */
             popUp = new PopUp(controller);

             popUp.setTaille(taille);

            popUp.initialize(popUpScene);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
