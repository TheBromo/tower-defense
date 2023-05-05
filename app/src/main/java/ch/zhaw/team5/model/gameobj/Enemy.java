package ch.zhaw.team5.model.gameobj;

import java.util.List;

import ch.zhaw.team5.model.gameobj.definitions.PathFollowingGameObject;
import ch.zhaw.team5.model.util.ImageLoader;
import ch.zhaw.team5.model.util.RandomUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy extends PathFollowingGameObject {

    private final static List<String> enemyImageNames = List.of("Enemy1_small", "Enemy2_small", "Enemy3_small",
            "Enemy4_small", "Enemy5_small");

    public Enemy(Point2D position) {
        super(position, ImageLoader.getInstance()
                .getByName(RandomUtil.getInstance().getRandomCollectionElement(enemyImageNames)));
        width = 50;
        height = 50;
    }

    public void hit() {
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
        g2d.drawImage(sprite, position.getX() - width / 2, position.getY() - height / 2,
                width, height);

    }

    private void debugEnemy(GraphicsContext g2d) {
        g2d.setFill(Color.RED);
        g2d.fillOval(position.getX() + velocity.getX(), position.getY() + velocity.getY(),
                5, 5);

        g2d.strokeText("" + velocity, position.getX() - width / 2, position.getY() - height / 2);
    }
}
