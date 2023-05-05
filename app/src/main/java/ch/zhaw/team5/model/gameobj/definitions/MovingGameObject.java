package ch.zhaw.team5.model.gameobj.definitions;

import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;

public abstract class MovingGameObject extends StaticGameObject {

    protected Point2D velocity;
    private Point2D rotation;

    public MovingGameObject(Point2D position, SpritePath spritePath) {
        super(position, spritePath);
    }

    public MovingGameObject(Point2D position) {
        super(position);
    }

    public Point2D getRotation(){return rotation;};

    public Point2D getVelocity(){return velocity;};
}
