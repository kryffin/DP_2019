package main.java.view.choixBateau;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.controller.Controller;

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

        switch (taille){
            case 2:
            break;

            case 3:
            break;

            case 4:
            break;
        }
        for(int i = 0; i < taille ; i ++){


        }



    }

    public void setTaille(int taille) {
        this.taille = taille;
    }
}
