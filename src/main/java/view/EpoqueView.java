package main.java.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import main.java.controller.Controller;

/**
 * Cette vue sert à faire le choix entre les 2 différentes époques
 * @author Nicolas Kleinhentz
 */
public class  EpoqueView {

    /**
     * controller à lier pour manipuler le modèle
     */
    private Controller controller;

    /**
     * Constructeur de liaison avec le controlleur
     * @param controller controlleur à lier
     */
    public EpoqueView (Controller controller){
        this.controller = controller;
    }

    /**
     * initialisation de la vue au chargement par le FXML
     * @param scene scene sur laquelle apppliquer la vue
     */
    @FXML
    public void initialize(Scene scene){

        //boutons des époques
        Button epoque1 = (Button) scene.lookup("#epoque1");
        Button epoque2 = (Button) scene.lookup("#epoque2");

        //bouton de l'époque 1
        epoque1.setText("Epoque 1");
        epoque1.setPrefSize(100.d, 100.d);
        epoque1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.chooseEpoque(0); //appel sur le controlleur du choix de l'époque
            }
        });

        //bouton de l'époque 2
        epoque2.setText("Epoque 2");
        epoque2.setPrefSize(100.d, 100.d);
        epoque2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.chooseEpoque(1); //appel sur le controlleur du choix de l'époque
            }
        });
    }
}
