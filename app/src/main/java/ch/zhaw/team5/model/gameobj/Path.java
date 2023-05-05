package ch.zhaw.team5.model.gameobj;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.ImageLoader;
import ch.zhaw.team5.model.util.RandomUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Path extends StaticGameObject {
    private final List<String> pathImageNames = List.of("Street1_small", "Street2_small");
    private final List<Image> sprites;
    private final int radius;
    private final Point2D end;

    public Path(Point2D start, Point2D end) {
        super(start, null);
        sprites = new ArrayList<>();
        height = 100;
        width = 100;
        radius = height / 2;
        this.end = end;
        int parts = (int) start.distance(end) / width;
        var imageLoader = ImageLoader.getInstance();
        var randomUtil = RandomUtil.getInstance();
        for (int i = 0; i < parts; i++) {
            sprites.add(imageLoader.getByName(randomUtil.getRandomCollectionElement(pathImageNames)));
        }
    }

    @Override
    public void render(Canvas canvas) {
        int boundryX = 0;
        var g2d = canvas.getGraphicsContext2D();
        for (Image sprite : sprites) {
            g2d.drawImage(sprite, position.getX() + boundryX, position.getY() - radius, width, height);
            boundryX += width;
        }
    }

    public Point2D getStart() {
        return position;
    }

    public Point2D getEnd() {
        return end;
    }

    public int getRadius() {
        return radius;
    }

    public List<Point2D> getPointsAList(){
        return List.of(position, end);
    }
}
