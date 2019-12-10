package main.java.model;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.controller.Controller;
import main.java.controller.PiloteReseau;
import main.java.view.ViewManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Model extends Application {

    private static ViewManager vm;

    public static void main(String[] argv) throws RemoteException, NotBoundException {
        Jeu jeu = new Jeu();
        Controller controller = new Controller(jeu);

        vm = new ViewManager(controller);
        jeu.setViewManager(vm); //liaison



        PiloteReseau pilote = new PiloteReseau(controller);
        jeu.setPiloteReseau(pilote);
        launch(argv);
    }

    @Override
    public void start (Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        vm.setStage(primaryStage); //lie le stage au manager
        vm.displayEpoqueView(); //affiche la vue du choix d'époque
    }

}
