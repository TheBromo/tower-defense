package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.MovingGameObject;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
        height = 10;
        width = 10;
        // compute the difference vector (start to end) = direction
        velocity = this.enemy.getPosition().subtract(position);

        velocity = velocity.normalize();
        velocity = velocity.multiply(speed);
        angle = Math.atan2(velocity.getY(), velocity.getX()) ;
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
        canvas.getGraphicsContext2D().save(); // saves the current state on stack, including the current transform

        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        rotate(canvas.getGraphicsContext2D(), angle, width/2, height/2);
        canvas.getGraphicsContext2D().drawImage(sprite.getSprite(), position.getX() - width / 2,
                position.getY() - height / 2,
                width, height);
        canvas.getGraphicsContext2D().restore(); // back to original state (before rotation)

        canvas.getGraphicsContext2D().strokeText("" + enemy.getHealth(), enemy.getPosition().getX() - width / 2,
                enemy.getPosition().getY() - height / 2);

    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
