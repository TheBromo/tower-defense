package ch.zhaw.team5.model.gameobj.definitions;

import ch.zhaw.team5.model.gameobj.Path;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class PathFollowingGameObject extends MovingGameObject {

    private int radius;
    private double maxForce;
    private double maxSpeed;
    private Point2D accelleration;

    public PathFollowingGameObject(Point2D position, Image sprite) {
        super(position, sprite);
        this.velocity = new Point2D(0, 0);
        this.accelleration = new Point2D(0, 0);
        this.maxSpeed = 3;
        this.maxForce = 0.1;
        this.radius = 3;
    }

    public Point2D findProjection(Point2D pos, Point2D a, Point2D b) {
        var v1 = a.subtract(pos);
        var v2 = b.subtract(pos);
        v2 = v2.normalize();
        var sp = v1.dotProduct(v2);
        v2 = v2.multiply(sp);
        v2 = v2.add(pos);
        return v2;
    }

    public Point2D followPath(Path path) {
        // Path following algorithm here!!

        // Step 1 calculate future position
        var future = new Point2D(velocity.getX(), velocity.getY());
        future = future.multiply(20);
        future = future.add(position);

        // Step 2 Is future on path?
        var target = findProjection(path.getStart(), future, path.getEnd());

        var d = future.distance(target);
        if (d > path.getRadius()) {
            return seek(target, false);
        } else {
            return new Point2D(0, 0);
        }
    }

    public Point2D seek(Point2D target, boolean arrival) {
        var force = target.subtract(position);
        var desiredSpeed = maxSpeed;
        if (arrival) {
            var slowRadius = 100;
            var distance = force.magnitude();

            if (distance < slowRadius) {
                desiredSpeed = map(distance, 0, slowRadius, 0, maxSpeed, false);
            }
        }
        force = setMagnitude(force, desiredSpeed);
        force = force.subtract(velocity);
        force = limit(force, this.maxForce);
        return force;

    }

    public void update() {
        velocity = velocity.add(accelleration);
        velocity = limit(velocity, maxSpeed);
        position = position.add(velocity);
        accelleration = new Point2D(0, 0);
    }

    public void applyForce(Point2D force) {
        accelleration = accelleration.add(force);
    }

    public Point2D setMagnitude(Point2D vector, double newMagnitude) {
        return vector.normalize().multiply(newMagnitude);
    }

    public Point2D limit(Point2D vector, double max) {
        if (vector.magnitude() > max) {
            return setMagnitude(vector, max);
        } else {
            return vector;
        }
    }

    public double map(double n, double start1, double stop1, double start2, double stop2, boolean withinBounds) {
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
