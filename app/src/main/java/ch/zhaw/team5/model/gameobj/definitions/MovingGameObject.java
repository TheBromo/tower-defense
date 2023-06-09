package ch.zhaw.team5.model.gameobj.definitions;

import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;

/**
 * Abstract class MovingGameObject which extends the StaticGameObject implements all moving objects in the game.
 *
 * @author strenman
 * @version 1.0.0
 */
public abstract class MovingGameObject extends StaticGameObject {

    protected Point2D velocity;

    /**
     * Constructor of the Class MovingGameObject.
     *
     * @param position   of the MovingGameObject
     * @param spritePath of the MovingGameObject
     */
    public MovingGameObject(Point2D position, SpritePath spritePath) {
        super(position, spritePath);
    }

}
