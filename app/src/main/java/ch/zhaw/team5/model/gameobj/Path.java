package ch.zhaw.team5.model.gameobj;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.Sprite;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Path extends StaticGameObject {
    private final List<Sprite> sprites;
    private final int radius;
    private final Point2D end;

    public Path(Point2D start, Point2D end) {
        super(start);
        sprites = new ArrayList<>();
        height = 100;
        width = 100;
        radius = height / 2;
        this.end = end;
        int parts = (int) start.distance(end) / width;
        for (int i = 0; i < parts; i++) {
            sprites.add(new Sprite(SpritePath.STREET));
        }
    }

    @Override
    public void render(Canvas canvas) {
        int boundryX = 0;
        var g2d = canvas.getGraphicsContext2D();
        for (Sprite sprite : sprites) {
            g2d.drawImage(sprite.getSprite(), position.getX() + boundryX, position.getY() - radius, width, height);
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

    public List<Point2D> getPointsAList() {
        return List.of(position, end);
    }
}
