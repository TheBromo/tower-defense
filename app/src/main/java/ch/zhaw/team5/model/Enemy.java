package ch.zhaw.team5.model;


import java.util.List;

import ch.zhaw.team5.model.util.ImageLoader;
import ch.zhaw.team5.model.util.RandomUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Enemy extends MovingGameObject {

    private final List<String> enemyImageNames = List.of("Enemy1", "Enemy2", "Enemy3", "Enemy4", "Enemy5");

    public Enemy(Point2D position) {
        //TODO update constructor
        super(position, null, new Point2D(1, 1), null);
        width = 50;
        height = 50;
        sprite = ImageLoader.getInstance().getByName(RandomUtil.getInstance().getRandomCollectionElement(enemyImageNames));
        //TODO Auto-generated constructor stub
    }

    public void hit() {
    }

    public Point2D getPosition() {
        return position;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(sprite, position.getX(), position.getY(), width, height);
    }

}
