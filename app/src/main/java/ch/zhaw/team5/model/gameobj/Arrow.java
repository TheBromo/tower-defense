package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.MovingGameObject;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class Arrow extends MovingGameObject {

    private Enemy enemy;

    private double speed = 15;
    private Point2D velocity;
    double angle;

    int damage, maxlifecycle, countlifecycle;

    public Arrow(Enemy target, Point2D startPosition, int damage) {
        super(startPosition, SpritePath.ARROW);
        this.enemy = target;
        this.damage = damage;
        height = 20;
        width = 20;
        // compute the difference vector (start to end) = direction
        velocity = this.enemy.getPosition().subtract(position);

        velocity = velocity.normalize();
        velocity = velocity.multiply(speed);
        angle = Math.atan2(velocity.getY(), velocity.getX()) + 1.5708;
        maxlifecycle = (int) (position.distance(this.enemy.getPosition()) / velocity.magnitude());
    }

    public void update() {
        position = position.add(velocity);
        countlifecycle++;
        if (hasHitTarget()) {
            enemy.hit(damage);
        }
    }

    public boolean hasHitTarget() {
        return countlifecycle >= maxlifecycle;
    }

    @Override
    public void render(Canvas canvas) {
        var gc = canvas.getGraphicsContext2D();
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, Math.toDegrees(angle), position.getX(), position.getY());
        gc.drawImage(sprite.getSprite(), position.getX() - width / 2,
                position.getY() - height / 2,
                width, height);
        gc.restore(); // back to original state (before rotation)

    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
