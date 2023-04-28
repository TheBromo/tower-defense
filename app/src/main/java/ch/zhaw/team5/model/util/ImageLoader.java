package ch.zhaw.team5.model.util;

import java.io.IOException;

import javafx.scene.image.Image;

public class ImageLoader {

    private static ImageLoader instance = new ImageLoader();

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public Image getByName(String name) {
        var url = ClassLoader.getSystemClassLoader().getResource("images/" + name + ".PNG");
        try {
            return new Image(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}