package main.java.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.controller.Controller;

/**
 * Cette classe sert à manipuler les différentes vues, à les initialiser et notamment à passer d'une vue à l'autre
 * @author Nicolas Kleinhentz
 */
public class ViewManager {

    /**
     * stage sur lequel appliquer les différentes vues
     */
    private Stage stage;

    /**
     * controlleur à lier à la vue
     */
    private Controller controller;

    /**
     * scène pour le choix de l'époque
     */
    private Scene epoqueScene;

    /**
     * scène pour le placement des bateaux
     */
    private Scene placementScene;

    /**
     * Constructeur de liaison du controlleur
     * @param controller controlleur à lier
     */
    public ViewManager (Controller controller) {
        this.controller = controller;
    }

    /**
     * Lie le stage donné
     * @param stage stage à lier
     */
    public void setStage (Stage stage) {
        this.stage = stage;
    }

    /**
     * Affiche la vue du choix de l'époque
     */
    public void displayEpoqueView () {
        stage.setTitle("SEA TO SEA - choix de l'époque");
        stage.setScene(epoqueScene);
        stage.show();
    }

    /**
     * Affiche la vue du placement des bateaux
     */
    public void displayPlacementView () {
        initPlacementView(); //initialise la vue du placement des bateaux

        stage.setTitle("SEA TO SEA - placement");
        stage.setScene(placementScene);
        stage.show();
    }

    /**
     * Initialise la vue du choix de l'époque
     */
    public void initEpoqueView () {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/epoqueView.fxml"));
            epoqueScene = new Scene(root, 800,600);

            /*build de la vue elle connait le controlleur */
            EpoqueView epoqueView = new EpoqueView(controller);

            /* init bouttons */
            epoqueView.initialize(epoqueScene);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Initialise la vue du placement des bateaux
     */
    public void initPlacementView () {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/placementView.fxml"));
            placementScene = new Scene(root, 800,600);

            /*build de la vue elle connait le controlleur */
            PlacementView placementView = new PlacementView(controller);

            /* init bouttons */
            placementView.initialize(placementScene);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
