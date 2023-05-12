package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.MovingGameObject;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

/**
 * The Arrow class represents a moving arrow in the game.
 * It extends the MovingGameObject class and contains methods for updating the arrow's position and rendering it.
 *
 * @author strenman mettlmi1
 * @version 1.0.0
 */
public class Arrow extends MovingGameObject {

    private final Enemy enemy;
    private final double speed = 15;
    double angle;
    int damage, maxLifecycle, countLifecycle;
    private Point2D velocity;

    /**
     * Creates a new Arrow object that targets a specific enemy.
     *
     * @param target        the enemy that the arrow is targeting
     * @param startPosition the starting position of the arrow
     * @param damage        the damage value of the arrow
     */
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
        maxLifecycle = (int) (position.distance(this.enemy.getPosition()) / velocity.magnitude());
    }

    /**
     * Updates the arrow's position and checks if it has hit its target.
     */
    public void update() {
        position = position.add(velocity);
        countLifecycle++;
        if (hasHitTarget()) {
            enemy.hit(damage);
        }
    }

    /**
     * Checks if the arrow has hit its target.
     *
     * @return true if the arrow has hit its target, false otherwise
     */
    public boolean hasHitTarget() {
        return countLifecycle >= maxLifecycle;
    }

    /**
     * Renders the arrow on the provided canvas.
     *
     * @param canvas the canvas to draw the arrow on
     */
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
