package ch.zhaw.team5.model.gameobj.definitions;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class MovingGameObject extends StaticGameObject {

    protected Point2D velocity;
    private Point2D rotation;




    public MovingGameObject(Point2D position, Image sprite) {
        super(position, sprite);

    }


    public Point2D getRotation(){return rotation;};

    public Point2D getVelocity(){return velocity;};
}
