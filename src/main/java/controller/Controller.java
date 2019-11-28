package main.java.controller;

import main.java.model.Jeu;
import main.java.model.Position;

import java.awt.event.*;
public class Controller {
    private Jeu jeu ;

    public void Controller(Jeu j){
        jeu = j ;
    }

    public void createShip(int taille, int version){
       // jeu.createShip(taille,version);
    }
    public void chooseWeapon(Position position){
        jeu.chooseWeapon(position);

    }
}
