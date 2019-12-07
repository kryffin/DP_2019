package main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import main.java.model.Jeu;
import main.java.model.plateau.Plateau;
import main.java.model.Position;

import java.util.List;

public class Controller {

    private Jeu jeu ;

    public Controller(Jeu j){
        jeu = j ;
    }

    public Plateau getPlateau () {
        return jeu.getPlateau();
    }

    public void createShip(int taille, int version, int posList){
        jeu.createShip(taille,version, posList);
    }


    public void chooseWeapon(ActionEvent e){
        //de action event e on en tire le bouton qui a une position
        Button b = (Button) e.getSource();
        String name = b.getId().substring(1);
        int tmp = Integer.parseInt(name);

        int col = tmp%10;
        int row = (tmp - col)/10;

        //todo verifier si c'est col/row ou row/col
        Position position = new Position(col, row);
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
        int tmp = Integer.parseInt(b.getId());

        int col = tmp%10;
        int row = (tmp - col)/10;
        System.out.println("col:"+col);
        System.out.println("row:"+row);
        System.out.println("====");
        //System.out.println(b.getText());
    }

    public void tirer(ActionEvent e) {
        //de action event e on en tire le bouton qui a une position
        Button b = (Button) e.getSource();
        String name = b.getId().substring(1);
        int tmp = Integer.parseInt(name);

        int col = tmp%10;
        int row = (tmp - col)/10;

        //todo tirer a la position avec l'arme sauvegarder au préalable
        Position position = new Position(col, row);

        jeu.chooseTarget(position);

    }

    public void placerShip(ActionEvent event) {


    }

    public List<List<Object>> getDescription(int taille) {
        return jeu.getDescription(taille);
    }
}
