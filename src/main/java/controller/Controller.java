package main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import main.java.model.Jeu;
import main.java.model.Plateau;
import main.java.model.Position;

public class Controller {

    private Jeu jeu ;

    public Controller(Jeu j){
        jeu = j ;
    }

    public Plateau getPlateau () {
        return jeu.getPlateau();
    }

    public void createShip(int taille, int version){
       // jeu.createShip(taille,version);
    }

    public void chooseWeapon(Position position){
        jeu.chooseWeapon(position);

    }

    public void chooseEpoque (int e) {
        jeu.choixEpoque(e);
    }

    /**
     * les différents boutons du plateaux sont liés à cette fonction
     * @param actionEvent
     */
    public void fireEvent(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        int tmp = Integer.parseInt(b.getText());

        int col = tmp%10;
        int row = (tmp - col)/10;
        System.out.println("col:"+col);
        System.out.println("row:"+row);
        System.out.println("====");
        //System.out.println(b.getText());
    }
}
