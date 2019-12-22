package main.java.controller;

import main.java.model.Bilan;
import main.java.model.EtatTir;
import main.java.model.Tir;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PiloteReseau extends UnicastRemoteObject implements Remote {

    // le controlleur de notre jeu
    private Controller gameController;

    // le pilote réseau du jeu adverse
    private PiloteReseau piloteAdverse;

    public PiloteReseau (Controller controller) throws RemoteException, NotBoundException {
        super();

        gameController = controller;

        /*
        Registry registry = LocateRegistry.getRegistry();


        System.out.println("RMI registry bindings: ");
        String[] registries = registry.list();

        for (String s : registries) {
            System.out.println(s + "\n");
        }

        String remoteObjectName = "pilote_adverse";
        piloteAdverse = (PiloteReseau) registry.lookup(remoteObjectName);
         */
    }


    /* SERVER SIDE : fonction pour recevoir des informations */

    public void choisirEpoque (int choix) {
        //passer l'entier au controller pour que le game process le choix
        gameController.chooseEpoque(choix);
    }

    public void recevoirTir (Tir tir) {
        //passer le tir au controller pour que le game le process
        gameController.recevoirTir(tir);
    }

    public void recevoirBilan (Bilan bilan) {
        //passer le bilan au controller pour que le game le process
        gameController.recevoirBilan(bilan);
    }

    /* CLIENT SIDE : fonction pour envoyer des informations */

    public void setPiloteAdverse (PiloteReseau piloteAdverse){ this.piloteAdverse = piloteAdverse; }

    public void renseignerEpoque (int choix) {
        piloteAdverse.choisirEpoque(choix);
    }

    public void envoyerTir (Tir tir) {
        piloteAdverse.recevoirTir(tir);
    }

    public void envoyerBilan (Bilan bilan) {
        piloteAdverse.recevoirBilan(bilan);
    }

    public void passerTour() {
        piloteAdverse.jouer();
    }

    private void jouer() {
        gameController.play();
    }

    public void setComportement(int i) {
        piloteAdverse.getComportement(i);
    }

    private void getComportement(int i) {
        gameController.setStrat(i);
    }
}
