package main.java.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {

    void choixEpoque (int choix) throws RemoteException;
    EtatTir[] envoyerTir (Tir tir) throws RemoteException;
    EtatTir[] recevoirTir (Tir tir) throws RemoteException;

}
