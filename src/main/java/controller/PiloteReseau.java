package main.java.controller;

import main.java.model.EtatTir;
import main.java.model.Tir;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PiloteReseau extends UnicastRemoteObject implements Remote {

    // le controlleur de notre jeu
    private Controller gameController;

    // le pilote réseau du jeu adverse
    private PiloteReseau piloteAdverse;

    public PiloteReseau (Controller controller) throws RemoteException, NotBoundException {
        super();

        gameController = controller;

        Registry registry = LocateRegistry.getRegistry();

        System.out.println("RMI registry bindings: ");
        String[] registries = registry.list();

        for (String s : registries) {
            System.out.println(s + "\n");
        }

        String remoteObjectName = "pilote_adverse";
        piloteAdverse = (PiloteReseau) registry.lookup(remoteObjectName);
    }

    /* SERVER SIDE : fonction pour recevoir des informations */

    public void choisirEpoque (int choix) {
        //passer l'entier au controller pour que le game process le choix
        gameController.chooseEpoque(choix);
    }

    public void recevoirTir (Tir tir) {
        //passer le tir au controller pour que le game le process
        gameController.shoot(tir);
    }

    public void recevoirBilan (EtatTir[] etats) {
        //passer le bilan au controller pour que le game le process
        gameController.recevoirBilan(etats);
    }

    /* CLIENT SIDE : fonction pour envoyer des informations */

    public void renseignerEpoque (int choix) {
        piloteAdverse.choisirEpoque(choix);
    }

    public void envoyerTir (Tir tir) {
        piloteAdverse.recevoirTir(tir);
    }

    public void envoyerBilan (EtatTir[] etats) {
        piloteAdverse.recevoirBilan(etats);
    }

}
