package ch.zhaw.team5.model.gameobj.definitions;

import java.util.List;


import ch.zhaw.team5.model.gameobj.Enemy;
import ch.zhaw.team5.model.gameobj.Path;
import ch.zhaw.team5.model.util.RandomUtil;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class PathFollowingGameObject extends MovingGameObject {

    private int radius;
    private double maxForce;
    private double maxSpeed;
    private Point2D accelleration;

    public PathFollowingGameObject(Point2D position, Image sprite) {
        super(position, sprite);
        var randomUtil = RandomUtil.getInstance();
        this.velocity = new Point2D(0, 0);
        this.accelleration = new Point2D(0, 0);
        this.maxSpeed = randomUtil.getRandomInRangeDouble(1, 2);
        this.maxForce = 3;
        this.radius = 20;
    }


    public void update(List<Enemy> enemies, Path path) {
        applyBehaviours(enemies, path);
        velocity = velocity.add(accelleration);
        velocity = limit(velocity, maxSpeed);
        position = position.add(velocity);
        accelleration = accelleration.multiply(0);
    }

    public void applyBehaviours(List<Enemy> enemies, Path path) {
        Point2D seperate = seperate(enemies);
        Point2D seek = seek(path.getEnd());

        seperate = seperate.multiply(1.5);
        seek = seek.multiply(0.5);

        applyForce(seperate);
        applyForce(seek);
    }

    public void applyForce(Point2D force) {
        accelleration = accelleration.add(force);
    }

    private Point2D seperate(List<Enemy> enemies) {
        double desiredSeparation = radius * 2;
        Point2D sum = new Point2D(0, 0);
        int count = 0;
        for (Enemy enemy : enemies) {
            double distance = position.distance(enemy.getPosition());
            if ((distance < desiredSeparation) && (distance > 0)) {
                Point2D diff = position.subtract(enemy.getPosition());
                diff = diff.normalize();
                diff = diff.multiply(1.0 / distance);
                sum = sum.add(diff);
                count++;
            }
        }

        if (count > 0) {
            sum = sum.multiply(1.0 / count);
            sum = setMagnitude(sum, maxSpeed);
            Point2D steer = sum.subtract(velocity);

            steer = limit(steer, maxSpeed);
            return steer;
        }
        return new Point2D(0, 0);
    }

    private Point2D seek(Point2D target) {
        Point2D desiredLocaction = target.subtract(position);
        desiredLocaction = desiredLocaction.normalize();
        desiredLocaction = desiredLocaction.multiply(maxSpeed);
        Point2D steer = desiredLocaction.subtract(velocity);
        steer = limit(steer, maxForce);
        return steer;
    }

    private Point2D setMagnitude(Point2D vector, double newMagnitude) {
        return vector.normalize().multiply(newMagnitude);
    }

    private Point2D limit(Point2D vector, double max) {
        if (vector.magnitude() > max) {
            return setMagnitude(vector, max);
        } else {
            return vector;
        }
    }

    public int getRadius() {
        return radius;
    }
}