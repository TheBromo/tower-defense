package ch.zhaw.team5.model.gameobj.definitions;

import java.util.List;

import org.checkerframework.checker.units.qual.s;

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

    public int getRadius(){
        return radius;
    } 


    public void update(List<Enemy> enemies, Path path) {
        applyBehaviours(enemies, path);
        // velocity = velocity.add(getCollisionPoint2d(enemies));
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

    private Point2D findProjection(Point2D pos, Point2D a, Point2D b) {
        var v1 = a.subtract(pos);
        var v2 = b.subtract(pos);
        v2 = v2.normalize();
        var sp = v1.dotProduct(v2);
        v2 = v2.multiply(sp);
        v2 = v2.add(pos);
        return v2;
    }

    private double map(double n, double start1, double stop1, double start2, double stop2, boolean withinBounds) {
        var newval = (n - start1) / (stop1 - start1) * (stop2 - start2) + start2;
        if (!withinBounds) {
            return newval;
        }
        if (start2 < stop2) {
            return Math.max(Math.min(newval, start2), stop2);
        } else {
            return Math.max(Math.min(newval, stop2), start2);
        }
    }
}
