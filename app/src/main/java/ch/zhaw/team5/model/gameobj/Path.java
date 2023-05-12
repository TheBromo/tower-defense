package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.Sprite;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * The Path class represents a path in the game from one point to another.
 * It extends the StaticGameObject class and contains methods for getting the start and end points of the path,
 * the radius, and a list of points along the path.
 *
 * @author strenman
 * @version 1.0.0
 */
public class Path extends StaticGameObject {
    private final List<Sprite> sprites;
    private final int radius;
    private final Point2D end;

    /**
     * Creates a new Path object from the start point to the end point.
     *
     * @param start the start point of the path
     * @param end   the end point of the path
     */
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

    /**
     * Renders the path on the provided canvas.
     *
     * @param canvas the canvas to draw the path on
     */
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
