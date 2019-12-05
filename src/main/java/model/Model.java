package main.java.model;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.controller.Controller;
import main.java.view.ViewManager;

public class Model extends Application {

    private static ViewManager vm;

    public static void main(String[] argv){
        Jeu jeu = new Jeu();
        Controller controller = new Controller(jeu);
        vm = new ViewManager(controller);
        jeu.setViewManager(vm); //liaison
        launch(argv);
    }

    @Override
    public void start (Stage primaryStage) {
        primaryStage.setResizable(false);
        vm.setStage(primaryStage); //lie le stage au manager
        vm.initEpoqueView(); //initialise la vue du choix d'époque
        vm.displayEpoqueView(); //affiche la vue du choix d'époque
    }

}
