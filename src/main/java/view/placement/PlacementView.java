package main.java.view.placement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.controller.Controller;
import main.java.model.plateau.bateau.Bateau;
import main.java.view.ViewManager;
import main.java.view.choixBateau.PopUp;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PlacementView implements Observer {
    private Controller controller;

    @FXML
    private GridPane rightPane;

    @FXML
    private VBox leftPane;

    @FXML
    private VBox placementPane;

    // tableau 2 dimensions de la grille des positions pour placer les bateaux
    private Button[][] buttons;

    private Stage popUpStage;
    private PopUp popUp;
    private Scene popUpScene;
    private Label selection;

    public PlacementView(Controller controller){
        this.controller = controller;
        popUpStage = new Stage();
        selection = new Label("");
        buttons = new Button[10][10];
    }

    @FXML
    public void initialize(Scene scene){
        rightPane = (GridPane) scene.lookup("#gridPane");
        Button tmp = (Button) scene.lookup("#start");
        tmp.setStyle("-fx-background-color: green");
        tmp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.switchToPlateauView();
            }
        });
        for(int h = 0; h<10; h++){
            for(int w = 0; w<10; w++){
                buttons[w][h] = new Button();
                buttons[w][h].setPrefSize(70.0,70.0);
                buttons[w][h].setStyle("\n-fx-background-color: transparent;\n" +
                        "-fx-background-radius: 0;");
                buttons[w][h].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.placer(event);
                        //controller.afficherFlotte();
                    }
                });
                String id = ""+((h*10)+w);
                //btt.setText(id);
                buttons[w][h].setId(id);
                buttons[w][h].setContentDisplay(ContentDisplay.TOP);

                rightPane.add(buttons[w][h],w,h);
            }

        }
        rightPane.setGridLinesVisible(true);

        leftPane = (VBox) scene.lookup("#flottePane");
        placementPane = (VBox) scene.lookup("#placementPane");
        List<Bateau> bateaux = controller.getPlateau().getBateaux();
        Button[] buttons = new Button[bateaux.size()];
        Button[] bttPlacement = new Button[bateaux.size()];
        int i = 0;
        for (Bateau b : bateaux) {
            buttons[i] = new Button(b.toString().substring(0,7));
            bttPlacement[i] = new Button("Placer");

            int finalI = i;
            buttons[i].setOnAction(event -> {
                int taille = b.getNbCompartiement();
                initPopUp(taille, finalI, popUpStage);


                popUpStage.setTitle("Details bateau taille "+taille);
                popUpStage.setScene(popUpScene);
                popUpStage.show();

            });

            bttPlacement[i].setOnAction(event -> {
                selection.setText(b.simpleToString());
                controller.setAPlacer(b);
            });

            placementPane.getChildren().add(bttPlacement[i]);
            leftPane.getChildren().add(buttons[i]);
            i++;
        }

        leftPane.getChildren().add(selection);
    }
    public void initPopUp(int taille, int posList, Stage popUpStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("popUp.fxml"));
            popUpScene = new Scene(root, 300,600);
            //popUpScene.getStylesheets().addAll(this.getClass().getResource("../view/style.css").toExternalForm());

            /*build de la vue elle connait le controlleur */
             popUp = new PopUp(controller);

             popUp.setStage(popUpStage);
             popUp.setTaille(taille);
             popUp.setPosList(posList);
             popUp.initialize(popUpScene);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Button getButton (int x, int y) {
        return buttons[x][y];
    }


    @Override
    public void update(Observable o, Object arg) {


    }

}
