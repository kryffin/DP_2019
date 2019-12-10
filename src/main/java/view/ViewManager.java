package main.java.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.controller.Controller;
import main.java.model.Jeu;
import main.java.view.epoque.EpoqueView;
import main.java.view.placement.PlacementView;
import main.java.view.plateau.PlateauView;

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
    private Scene epoqueScene = null;

    /**
     * scène pour le placement des bateaux
     */
    private Scene placementScene;

    /**
     * scene pour le l'affichage du jeu
     */

    private Scene plateauScene;

    private Scene currentScene;

    private PlacementView placementView;

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
        if (epoqueScene == null) {
            initEpoqueView(); //initialise la vue du choix de l'époque si elle est null
        }
        currentScene = epoqueScene;
        stage.setTitle("SEA TO SEA - choix de l'époque");
        stage.setScene(epoqueScene);
        stage.show();
    }

    /**
     * Affiche la vue du placement des bateaux
     */
    public void displayPlacementView () {
        if (placementScene == null) {
            initPlacementView(); //initialise la vue du placement des bateaux si elle est null
        }
        currentScene = placementScene;
        stage.setTitle("SEA TO SEA - placement");
        stage.setScene(placementScene);
        stage.show();
        controller.setPlacementView(placementView);
        controller.updateColor();
    }

    /**
     * Affiche la vu des plateaux de jeu
     */
    public void displayPlateauView(){
        if (plateauScene == null) {
            initPlateauView(); //initialise la vue du placement des bateaux si elle est null
        }
        currentScene = plateauScene;
        stage.setTitle("SEA TO SEA - plateau");
        stage.setScene(plateauScene);
        stage.show();
    }
    /**
     * Initialise la vue du choix de l'époque
     */
    public void initEpoqueView () {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("epoque/epoqueView.fxml"));
            epoqueScene = new Scene(root, 800,600);
            epoqueScene.getStylesheets().addAll(this.getClass().getResource("../view/style.css").toExternalForm());

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
            Parent root = FXMLLoader.load(getClass().getResource("placement/placementView.fxml"));
            placementScene = new Scene(root, 800,600);
            placementScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            /*build de la vue elle connait le controlleur */
            placementView = new PlacementView(controller);

            /* init bouttons */
            placementView.initialize(placementScene);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Initialise la vue des plateaux de jeu
     */

    public void initPlateauView(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("plateau/plateauView.fxml"));
            plateauScene = new Scene(root, 800,600);
            plateauScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            /*build de la vue elle connait le controlleur */
            PlateauView plateauView = new PlateauView(controller);

            /* init bouttons */
            plateauView.initialize(plateauScene);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public PlacementView getPlacementView() {
        return placementView;
    }

    public void update(Jeu jeu) {
       // currentScene.update(jeu);

    }

}
