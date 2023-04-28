package ch.zhaw.team5.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class MovingGameObject implements Renderable {
    private Point2D position;
    private Image sprite;
    private Point2D velocity;
    private Point2D rotation;




    public MovingGameObject(Point2D position, Image sprite, Point2D velocity, Point2D rotation) {
        this.position = position;
        this.sprite = sprite;
        this.rotation = rotation;
        this.velocity = velocity;

    }

    @Override
    public void render() {

    }

    public Point2D getPosition() {
        return position;
    }

    public Image getSprite() {
        return sprite;
    }

    public Point2D getRotation(){return rotation;};

    public Point2D getVelocity(){return velocity;};
}
