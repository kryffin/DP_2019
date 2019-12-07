package main.java.view.choixBateau;

import java.util.HashMap;

public class SingletonDescription {


    private static HashMap<Integer, String[]> descr;


    private static SingletonDescription instance = new SingletonDescription();
    public static SingletonDescription getInstance() {
        return instance;
    }

    private SingletonDescription(){
        descr = new HashMap<>();


    }
}
