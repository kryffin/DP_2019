package main.java.model.plateau;

import main.java.model.Position;

public class PlateauInfo {
    private boolean[][] infos;

    public PlateauInfo(){
        infos = new boolean[10][10];
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++)
                infos[i][j]= false;
    }

    public void setInfo(Position p, boolean touched){
        infos[p.getX()][p.getY()] = touched;
    }

    public boolean getInfo(Position p){
        return infos[p.getX()][p.getY()];
    }
}
