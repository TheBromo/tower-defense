package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.PathFollowingGameObject;
import ch.zhaw.team5.model.util.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Enemy extends PathFollowingGameObject {

    private boolean isAlive = true;
    private int health = 100;
    private int damage = 25;

    public Enemy(Point2D position) {
        super(position, Sprite.SpritePath.ENEMY);
        width = 50;
        height = 50;
    }

    public void hit(int damage) {
        health -= damage;
        isAlive = health > 0;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void update(List<Enemy> enemies, Path path) {
        super.update(enemies, path);
    }

    public Point2D getPosition() {
        return position;
    }

    public boolean outOfScreen(int threshhold) {
        return position.getX() > threshhold;
    }

    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        g2d.drawImage(sprite.getSprite(), position.getX() - width / 2, position.getY() - height / 2,
                width, height);
        g2d.setFill(Color.LIGHTGREEN);
        g2d.fillRect(position.getX() - width / 2, position.getY() - height / 2-10,
                (((double) width / 100) * health), 2);
        // debugEnemy(canvas.getGraphicsContext2D());
    }

    private void debugEnemy(GraphicsContext g2d) {
        g2d.setFill(Color.RED);
        g2d.strokeText("" + health, position.getX() - width / 2, position.getY() - height / 2);
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }
}
