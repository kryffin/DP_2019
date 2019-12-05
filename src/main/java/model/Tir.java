package main.java.model;

public class Tir {
        private Arme arme;
        private Position position;
        public Tir(Arme a , Position p){
            arme = a;
            position = p;
        }

    public Arme getArme() {
        return arme;
    }

    public Position getPosition() {
        return position;
    }
}
