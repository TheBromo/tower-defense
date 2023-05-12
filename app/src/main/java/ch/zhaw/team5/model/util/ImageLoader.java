package ch.zhaw.team5.model.util;

import javafx.scene.image.Image;

import java.io.IOException;

/**
 * Singleton class to manage image loading in the Tower Defense game.
 *
 * @author strenman
 * @version 1.0.0
 */
public class ImageLoader {

    private static ImageLoader instance = new ImageLoader();

    private ImageLoader() {
    }

    /**
     * Provides global access to the singleton instance of ImageLoader.
     *
     * @return the instance of ImageLoader
     */
    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    /**
     * Returns an Image by its name.
     *
     * @param name the name of the image
     * @return the Image, or null if there's an error loading the image
     */
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