package main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import main.java.model.Jeu;
import main.java.model.plateau.Plateau;
import main.java.model.Position;
import main.java.model.plateau.bateau.Bateau;
import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import java.util.List;

public class Controller {

    private Jeu jeu ;

    /**
     * le bateau a placer (le dernier selectionné)
     */
    private Bateau aPlacer;

    private boolean isPlacer;

    public Controller(Jeu j){
        jeu = j ;
        isPlacer = false;
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

        Position position = new Position(col, row);
        jeu.chooseWeapon(position);

    }

    public void chooseEpoque (int e) {
        jeu.choixEpoque(e);
    }

    /**
     * les différents boutons du plateaux sont liés à cette fonction
     * permet de placer le bateau en attribut
     * @param actionEvent
     */
    public void placer(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        int tmp = Integer.parseInt(b.getId());

        int col = tmp%10;
        int row = (tmp - col)/10;

        if(isPlacer){
            int taille = aPlacer.getNbCompartiement();
            if(col+taille <= 10){
                aPlacer.setPosition(col, row);
            } else {
                System.out.println("Placement non autorisé");
            }

        }
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


    public List<List<Object>> getDescription(int taille) {
        return jeu.getDescription(taille);
    }

    public void setAPlacer(Bateau b) {
        isPlacer = true;
        this.aPlacer = b;
    }

    public void afficherFlotte() {
        for (Bateau b : jeu.getPlateau().getBateaux()){
            System.out.println(b);
        }
        System.out.println("=======");

    }
}
