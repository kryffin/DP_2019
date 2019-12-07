package main.java.view.choixBateau;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.controller.Controller;

import java.util.List;

public class PopUp {


    @FXML
    private VBox leftBox;

    @FXML
    private VBox rightBox;

    private Controller controller;
    private int taille;


    public PopUp(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public void initialize(Scene scene) {

        leftBox = (VBox) scene.lookup("#VBoxLeft");
        rightBox = (VBox) scene.lookup("#VBoxRight");

        List<List<Object>> descr = controller.getDescription(taille);
        System.out.println(descr);
        for(int i = 0; i < descr.size() ; i ++){
            //on itère sur les différentes versions

            Button btn = new Button("v"+i);
            TextArea t = new TextArea();
            for (Object o : descr.get(i)){
                if(o == null)
                    o = "X";
                t.setText(t.getText()+"\n"+o.toString());
            }

            int finalI = i;
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controller.createShip(taille, finalI);
                }
            });
            leftBox.getChildren().add(t);
            rightBox.getChildren().add(btn);
        }



    }

    public void setTaille(int taille) {
        this.taille = taille;
    }
}
