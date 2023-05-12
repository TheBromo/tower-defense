package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.PathFollowingGameObject;
import ch.zhaw.team5.model.util.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * The Enemy class represents an enemy in the game.
 * It extends the PathFollowingGameObject class and contains methods for managing the enemy's health, damage, reward, and other properties.
 *
 * @author strenman mettlmi1
 * @version 1.0.0
 */
public class Enemy extends PathFollowingGameObject {

    private boolean isAlive = true;
    private int health = 100;
    private final int damage = 25;
    private final int reward = 5;

    /**
     * Creates a new Enemy object at the specified position.
     *
     * @param position the initial position of the enemy
     */
    public Enemy(Point2D position) {
        super(position, Sprite.SpritePath.ENEMY);
        width = 50;
        height = 50;
    }

    /**
     * Reduces the enemy's health by the specified damage.
     *
     * @param damage the amount of damage to inflict on the enemy
     */
    public void hit(int damage) {
        health -= damage;
        isAlive = health > 0;
    }

    /**
     * Returns whether the enemy is alive.
     *
     * @return true if the enemy is alive, false otherwise
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Updates the enemy's state based on the current list of enemies and the path.
     *
     * @param enemies the current list of enemies
     * @param path    the path that the enemies are following
     */
    @Override
    public void update(List<Enemy> enemies, Path path) {
        super.update(enemies, path);
    }

    /**
     * Returns whether the enemy is out of the screen.
     *
     * @param threshhold the threshhold value for determining whether the enemy is out of the screen
     * @return true if the enemy is out of the screen, false otherwise
     */
    public boolean outOfScreen(int threshhold) {
        return position.getX() > threshhold;
    }

    /**
     * Renders the enemy on the provided canvas.
     *
     * @param canvas the canvas to draw the enemy on
     */
    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        g2d.drawImage(sprite.getSprite(), position.getX() - width / 2, position.getY() - height / 2,
            width, height);
        g2d.setFill(Color.LIGHTGREEN);
        g2d.fillRect(position.getX() - width / 2, position.getY() - height / 2 - 10,
            (((double) width / 100) * health), 2);
    }

    public int getDamage() {
        return damage;
    }

    public int getReward() {
        return reward;
    }
}
