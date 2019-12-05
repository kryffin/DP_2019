package main.java.controller;

import main.java.model.EtatTir;
import main.java.model.Server;
import main.java.model.Tir;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControllerReseau extends UnicastRemoteObject implements Server {

    public ControllerReseau () throws RemoteException {
        super();
    }

    @Override
    public void choixEpoque(int choix) throws RemoteException {
        return;
    }

    @Override
    public EtatTir[] envoyerTir(Tir tir) throws RemoteException {
        return null;
    }

    @Override
    public EtatTir[] recevoirTir(Tir tir) throws RemoteException {
        return null;
    }

}
