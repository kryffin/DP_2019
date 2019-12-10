package main.java.view.choixBateau;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.controller.Controller;

import java.util.List;

public class PopUp {


    @FXML
    private VBox leftBox;

    @FXML
    private GridPane gridPane;

    private Controller controller;
    private int taille;
    private int posList;
    private Stage stage;


    public PopUp(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public void initialize(Scene scene) {

        gridPane = (GridPane) scene.lookup("#popUpGridPane");


        List<List<Object>> descr = controller.getDescription(taille);
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        System.out.println(descr);
        for(int i = 0; i < descr.size() ; i ++){
            //on itère sur les différentes versions

            Button btn = new Button("v"+i);
            Label t = new Label();
            for (Object o : descr.get(i)){
                if(o == null) {
                    o = "X";
                }
                t.setText(t.getText()+"\n"+o.toString());
            }

            int finalI = i;
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controller.createShip(taille, finalI, posList);
                    stage.close();
                }
            });
            t.setAlignment(Pos.CENTER);
            btn.setAlignment(Pos.CENTER);
            gridPane.add(t,0,i);
            gridPane.add(btn, 1, i);
        }



    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setPosList(int posList) {
        this.posList = posList;
    }

    public void setStage(Stage popUpStage) {
        this.stage = popUpStage;
    }
}
