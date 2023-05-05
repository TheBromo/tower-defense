package ch.zhaw.team5.model.gameobj;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.ImageLoader;
import ch.zhaw.team5.model.util.RandomUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Decorations extends StaticGameObject {

    List<Image> sprites = new ArrayList<>();
    List<Point2D> positions = new ArrayList<>();
    private final List<String> imageNames = List.of("Deco1", "Deco2", "Deco3");

    public Decorations( int amount, int topX, int topY, int bottomX, int bottomY) {
        super(null, null);
        generatePositions(amount, topX, topY, bottomX, bottomY);
    }

    private void generatePositions(int amount, int topX, int topY, int bottomX, int bottomY) {
        var imageLoader = ImageLoader.getInstance();
        var randomUtil = RandomUtil.getInstance();
        for (int i = 0; i < amount; i++) {
            sprites.add(imageLoader.getByName(randomUtil.getRandomCollectionElement(imageNames)));
            positions.add(new Point2D(randomUtil.getRandomInRange(topX, bottomX),
                    randomUtil.getRandomInRange(topY, bottomY)));
        }
    }

    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        for (int i = 0; i < sprites.size(); i++) {
            g2d.drawImage(sprites.get(i), positions.get(i).getX(), positions.get(i).getY(), 50, 50);
        }
    }

}
