package ch.zhaw.team5.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class MovingGameObject extends StaticGameObject{

    private Point2D velocity;
    private Point2D rotation;




    public MovingGameObject(Point2D position, Image sprite, Point2D velocity, Point2D rotation) {
        super(position, sprite);

        this.rotation = rotation;
        this.velocity = velocity;

    }


    public Point2D getRotation(){return rotation;};

    public Point2D getVelocity(){return velocity;};
}
