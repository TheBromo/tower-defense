package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.Player;
import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.Sprite;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Wall extends StaticGameObject {
    private Player player;
    private final Sprite wallTop;
    private final Sprite wallBottom;

    public Wall(Point2D position) {
        super(position);
        health = initialHealth;
        hasWallPositiveHealth = true;
        wallTop = new Sprite(SpritePath.WALLTOP);
        wallBottom = new Sprite(SpritePath.WALLBOTTOM);
        height = 200;
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
        canvas.getGraphicsContext2D().drawImage(wallTop.getSprite(), position.getX(), position.getY(), width, height);
        canvas.getGraphicsContext2D().drawImage(wallBottom.getSprite(), position.getX(), position.getY() + canvas.getHeight() / 2 + 50, width, height);
    }
}
