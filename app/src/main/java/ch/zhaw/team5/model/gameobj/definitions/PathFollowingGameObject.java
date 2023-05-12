package ch.zhaw.team5.model.gameobj.definitions;

import ch.zhaw.team5.model.gameobj.Enemy;
import ch.zhaw.team5.model.gameobj.Path;
import ch.zhaw.team5.model.util.RandomUtil;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;

import java.util.List;

/**
 * Abstract class PathFollowingGameObject which extends the MovingGameObject implements all moving objects which are following a specific path.
 * Algorithm is based upon: The Nature of code by Daniel Shiffman Exercise 6.14, Chapter 6.12 Combinations.
 *
 * @author strenman
 * @version 1.0.0
 */
public abstract class PathFollowingGameObject extends MovingGameObject {

    private final int radius;
    private final double maxForce;
    private final double maxSpeed;
    private Point2D acceleration;

    /**
     * Constructor for PathFollowingGameObject.
     *
     * @param position   of the PathFollowingGameObject
     * @param spritePath of the PathFollowingGameObject
     */
    public PathFollowingGameObject(Point2D position, SpritePath spritePath) {
        super(position, spritePath);
        var randomUtil = RandomUtil.getInstance();
        this.velocity = new Point2D(0, 0);
        this.acceleration = new Point2D(0, 0);
        this.maxSpeed = randomUtil.getRandomInRangeDouble(1, 2);
        this.maxForce = 3;
        this.radius = 20;
    }

    /**
     * Update method to apply behaviours and add velocity.
     *
     * @param enemies - List of enemies
     * @param path    - Path for submitting parameter to applyBehaviours()
     */
    public void update(List<Enemy> enemies, Path path) {
        applyBehaviours(enemies, path);
        velocity = velocity.add(acceleration);
        velocity = limit(velocity, maxSpeed);
        position = position.add(velocity);
        acceleration = acceleration.multiply(0);
    }

    /**
     * Seperates from other enemies and seeks the path end.
     *
     * @param enemies - List of enemies
     * @param path    - Path for submitting parameter to applyBehaviours()
     */
    public void applyBehaviours(List<Enemy> enemies, Path path) {
        Point2D seperate = separate(enemies);
        Point2D seek = seek(path.getEnd());

        seperate = seperate.multiply(1.5);
        seek = seek.multiply(0.5);

        applyForce(seperate);
        applyForce(seek);
    }

    /**
     * Add force to acceleration.
     *
     * @param force to add to acceleration
     */
    public void applyForce(Point2D force) {
        acceleration = acceleration.add(force);
    }

    private Point2D separate(List<Enemy> enemies) {
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
