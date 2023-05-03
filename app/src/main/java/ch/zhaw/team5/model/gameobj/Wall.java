package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.ImageLoader;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Wall extends StaticGameObject {
    private final Image wallTop;
    private final Image wallBottom;

    public Wall(Point2D position) {
        super(position, null);
        health = initialHealth;
        hasWallPositiveHealth = true;
        wallTop = ImageLoader.getInstance().getByName("WallTop");
        wallBottom = ImageLoader.getInstance().getByName("WallBottom");
        height =200;
        width = 100;
    }

    private int health;
    private final int reductionOfHealth = 5;
    private final int increaseOfHealth = 5;

    private final int initialHealth = 100;
    private boolean hasWallPositiveHealth;

    public int reduceHealthOfWall() {
        health -= reductionOfHealth;
        return health;
    }

    public int recoverHealthOfWall() {
        health += increaseOfHealth;
        return health;
    }

    public boolean checkHealthOfWall() {
        if (health < 0) {
            hasWallPositiveHealth = false;
        } else {
            hasWallPositiveHealth = true;
        }
        return hasWallPositiveHealth;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(wallTop, position.getX(), position.getY(), width, height);
        canvas.getGraphicsContext2D().drawImage(wallBottom, position.getX(), position.getY() + canvas.getHeight() / 2 + 50, width, height);
    }
}
