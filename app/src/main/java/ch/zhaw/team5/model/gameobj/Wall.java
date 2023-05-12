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
    private final int reductionOfHealth = 5;
    private final int increaseOfHealth = 5;
    private final int initialHealth = 100;
    private int health;
    private boolean hasWallPositiveHealth;
    /**
     * Creates a new Wall object at the specified position.
     *
     * @param position the position of the wall
     */
    public Wall(Point2D position) {
        super(position);
        health = initialHealth;
        hasWallPositiveHealth = true;
        wallTop = new Sprite(SpritePath.WALLTOP);
        wallBottom = new Sprite(SpritePath.WALLBOTTOM);
        height = 200;
        width = 100;
    }

    /**
     * Reduces the health of the wall.
     *
     * @return the current health of the wall after reduction
     */
    public int reduceHealthOfWall() {
        health -= reductionOfHealth;
        return health;
    }

    /**
     * Recovers the health of the wall.
     *
     * @return the current health of the wall after recovery
     */
    public int recoverHealthOfWall() {
        health += increaseOfHealth;
        return health;
    }

    /**
     * Checks the health of the wall.
     *
     * @return true if the wall has positive health, false otherwise
     */
    public boolean checkHealthOfWall() {
        hasWallPositiveHealth = health >= 0;
        return hasWallPositiveHealth;
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
