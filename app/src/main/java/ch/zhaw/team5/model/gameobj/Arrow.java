package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.MovingGameObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Arrow extends MovingGameObject {

    private Enemy enemy;

    private double speed = 15;
    private Point2D velocity;

    int damage, maxlifecycle, countlifecycle;

    public Arrow(Enemy target, Point2D startPosition, int damage) {
        super(startPosition); // TODO insert Arrow Sprite here
        this.enemy = target;
        this.damage = damage;
        height = 10;
        width = 10;
        // compute the difference vector (start to end) = direction
        velocity = this.enemy.getPosition().subtract(position);

        velocity = velocity.normalize();
        velocity = velocity.multiply(speed);

        maxlifecycle = (int) (position.distance(this.enemy.getPosition()) / velocity.magnitude());
    }

    public void update() {
        position = position.add(velocity);
        countlifecycle++;
        if (hasHitTarget()) {
            enemy.hit(damage); // TODO magic number
        }
    }

    public boolean hasHitTarget() {
        return countlifecycle >= maxlifecycle;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillOval(position.getX() - width / 2,
                position.getY() - height / 2,
                width, height);
        canvas.getGraphicsContext2D().strokeText("" + enemy.getHealth(), enemy.getPosition().getX() - width / 2,
        enemy.getPosition().getY() - height / 2);
    }
}
