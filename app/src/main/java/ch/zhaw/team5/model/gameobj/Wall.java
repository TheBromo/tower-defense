package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.Sprite;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

/**
 * The Wall class represents a wall in the game.
 * It extends the StaticGameObject class and contains methods for managing the wall's health and rendering the wall.
 *
 * @author kuengpas
 * @version 1.0.0
 */
public class Wall extends StaticGameObject {
    private final Sprite wallTop;
    private final Sprite wallBottom;

    /**
     * Creates a new Wall object at the specified position.
     *
     * @param position the position of the wall
     */
    public Wall(Point2D position) {
        super(position);
        wallTop = new Sprite(SpritePath.WALLTOP);
        wallBottom = new Sprite(SpritePath.WALLBOTTOM);
        height = 200;
        width = 100;
    }

    /**
     * Renders the wall on the provided canvas.
     *
     * @param canvas the canvas to draw the wall on
     */
    @Override
    public void render(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(wallTop.getSprite(), position.getX(), position.getY(), width, height);
        canvas.getGraphicsContext2D().drawImage(wallBottom.getSprite(), position.getX(),
            position.getY() + canvas.getHeight() / 2 + 50, width, height);
    }
}
