package ch.zhaw.team5.model.gameobj;

import java.util.List;

import ch.zhaw.team5.model.gameobj.definitions.MovingGameObject;
import ch.zhaw.team5.model.util.ImageLoader;
import ch.zhaw.team5.model.util.RandomUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Enemy extends MovingGameObject {

    private final List<String> enemyImageNames = List.of("Enemy1", "Enemy2", "Enemy3", "Enemy4", "Enemy5");

    public Enemy(Point2D position) {
        // TODO update constructor
        super(position, null);
        width = 50;
        height = 50;
        sprite = ImageLoader.getInstance()
                .getByName(RandomUtil.getInstance().getRandomCollectionElement(enemyImageNames));
        velocity = new Point2D(10, 0);
    }

    public void hit() {
    }

    public void update() {
        this.position = position.add(velocity);
    }

    public Point2D getPosition() {
        return position;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(sprite, position.getX(), position.getY(), width, height);
    }

}
