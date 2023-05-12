package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Tower class represents a tower in the game that can shoot at enemies.
 * It extends the StaticGameObject class and contains methods for upgrading the tower and shooting at enemies.
 *
 * @author strenman mettlmi1
 * @version 1.0.0
 */
public class Tower extends StaticGameObject {

    public final int range = 200;
    private final List<Arrow> arrows;
    private int damage = 20;
    private long interval = 4000, lastShot = 0;

    /**
     * Creates a new Tower object at the specified position.
     *
     * @param position the position of the tower
     */
    public Tower(Point2D position) {
        super(position, Sprite.SpritePath.SMALLTOWER);
        arrows = new ArrayList<>();
        height = 100;
        width = 100;
    }

    /**
     * Renders the tower and its arrows on the provided canvas.
     *
     * @param canvas the canvas to draw the tower and arrows on
     */
    @Override
    public void render(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(sprite.getSprite(), position.getX() - width / 2,
            position.getY() - height / 2,
            width, height);

        for (Arrow arrow : arrows) {
            arrow.render(canvas);
        }

    }

    /**
     * Upgrades the tower, increasing its damage and decreasing the interval between shots.
     */
    public void upgrade() {
        damage += 8;
        interval *= .75;
    }

    private boolean outOfRange(Enemy enemy) {
        return position.distance(enemy.getPosition()) > range;
    }

    /**
     * Shoots at the provided list of enemies if the interval between shots has passed.
     *
     * @param enemies the list of enemies to shoot at
     */
    public void shootAtEnemies(List<Enemy> enemies) {

        if (enemies.size() > 0 && interval < System.currentTimeMillis() - lastShot) {

            var possibleTargets = new ArrayList<>(enemies);
            possibleTargets.removeIf(this::outOfRange);

            if (possibleTargets.size() == 0)
                return;

            Random rand = new Random();
            var target = possibleTargets.get(rand.nextInt(possibleTargets.size()));

            arrows.add(new Arrow(target, this.position, damage));
            lastShot = System.currentTimeMillis();
        }
    }

    /**
     * Updates the tower, removing any arrows that have hit their target and updating the remaining arrows.
     */
    public void update() {
        arrows.removeIf(Arrow::hasHitTarget);
        for (Arrow arrow : arrows) {
            arrow.update();
        }
    }

}
