package ch.zhaw.team5.model.gameobj;

import java.util.List;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.ImageLoader;
import ch.zhaw.team5.model.util.RandomUtil;
import ch.zhaw.team5.model.util.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class TowerPosition extends StaticGameObject {

    private Tower tower;
    private boolean built;
    private int positionNumber;

    public TowerPosition(Point2D position, int number) {
        super(position, Sprite.SpritePath.HOLE);
        tower = new Tower(position);
        positionNumber = number;
        height = 100;
        width = 100;
        built = false;
    }

    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        if (hasTower()) {
            tower.render(canvas);
        } else {
            g2d.drawImage(sprite.getSprite(), position.getX() - width / 2, position.getY() - height / 2,
                    width, height);
            g2d.setStroke(Color.GRAY);
            g2d.strokeText("" + positionNumber, position.getX(), position.getY());
        }
    }

    public void buildTower() {
        built = true;
    }

    public void upgradeTower() {
       tower.upgrade();
       tower.setSprite(Sprite.SpritePath.BIGTOWER);
    }

    public Tower getTower() {
        return tower;
    }

    public boolean hasTower() {
        return built;
    }

    public int getNumber() {
        return positionNumber;
    }
}
