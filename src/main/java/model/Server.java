package main.java.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {

    EtatTir envoyerTir (Tir tir) throws RemoteException;
    void choixEpoque (int choix) throws RemoteException;

}
