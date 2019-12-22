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
        Jeu jeuClient = new Jeu();
        Controller controller = new Controller(jeuClient);

        vm = new ViewManager(controller);
        jeuClient.setViewManager(vm); //liaison

        PiloteReseau pilote = new PiloteReseau(controller);
        jeuClient.setPiloteReseau(pilote);

        /* création d'un adversaire */
        Jeu jeuAdversaire = new Jeu();
        Controller controllerAdversaire = new Controller(jeuAdversaire);
        PiloteReseau piloteAdversaire = new PiloteReseau(controllerAdversaire);
        jeuAdversaire.setPiloteReseau(piloteAdversaire);

        piloteAdversaire.setPiloteAdverse(pilote);
        pilote.setPiloteAdverse(piloteAdversaire);


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
