package main.java.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.java.controller.Controller;
import main.java.view.View;

import java.net.URL;

public class Model extends Application {
    public static void main(String[] argv){

        Jeu jeu = new Jeu();
     
        launch(argv);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {


            System.out.printf(getClass().getResource("./../view/").toString());
            Parent root = FXMLLoader.load(getClass().getResource("../view/mainView.fxml"));
            primaryStage.setTitle("SEA TO SEA");
            Scene scene = new Scene(root, 800,600);
            primaryStage.setScene(scene);

            /*build du controleur la vue le connait*/
            Controller controller = new Controller();

            /*build de la vue elle connait le controlleur */
            View view = new View(controller);

            /* init bouttons */
            view.initialize(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
