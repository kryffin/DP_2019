package main.java.model.image;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageManager {


    private static final ImageManager instance = new ImageManager();

    private final ImageView water1 = new ImageView(new Image(getClass().getResourceAsStream("../image/water_tile.jpg")));
    private final ImageView water2 = new ImageView(new Image(getClass().getResourceAsStream("../image/water_tile2.jpg")));

    private ImageManager() {
    }


    public static ImageManager getInstance() {
        return instance;
    }

    public ImageView getWater1(){
        return water1;
    }

    public ImageView getWater2(){
        return water2;
    }
}
