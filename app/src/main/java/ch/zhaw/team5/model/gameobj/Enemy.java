package ch.zhaw.team5.model.gameobj;

import java.util.List;

import ch.zhaw.team5.model.gameobj.definitions.PathFollowingGameObject;
import ch.zhaw.team5.model.util.ImageLoader;
import ch.zhaw.team5.model.util.RandomUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Enemy extends PathFollowingGameObject {

    private final static List<String> enemyImageNames = List.of("Enemy1", "Enemy2", "Enemy3", "Enemy4", "Enemy5");
    private final Path path;

    public Enemy(Point2D position, Path path) {
        // TODO update constructor
        super(position, ImageLoader.getInstance()
                .getByName(RandomUtil.getInstance().getRandomCollectionElement(enemyImageNames)));
        width = 50;
        height = 50;
        this.path = path;
        velocity = new Point2D(10, 0);
    }

    public void hit() {
    }

    @Override
    public void update() {

        super.update();
    }

    public Point2D getPosition() {
        return position;
    }

    @Override
    public void render(Canvas canvas) {
         canvas.getGraphicsContext2D().drawImage(sprite, position.getX() - width / 2,
         position.getY() - height / 2,width, height);
    }

}
