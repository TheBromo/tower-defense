package ch.zhaw.team5.model.gameobj;

import java.util.List;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.Sprite;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class TowerPosition extends StaticGameObject {

    private Tower tower;
    private boolean built;
    private int positionNumber, towerLevel;

    public TowerPosition(Point2D position, int number) {
        super(position, Sprite.SpritePath.HOLE);
        tower = new Tower(position);
        positionNumber = number;
        height = 100;
        width = 100;
        built = false;
        towerLevel = 0;
    }

    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        if (hasTower()) {
            tower.render(canvas);
        } else {
            g2d.drawImage(sprite.getSprite(), position.getX() - width / 2, position.getY() - height / 2,
                    width, height);
            g2d.setStroke(Color.GREY);
            g2d.strokeText("" + positionNumber, position.getX(), position.getY());
        }
    }

    public void buildTower() {
        built = true;
    }

    public void upgradeTower() {
        towerLevel++;
        tower.upgrade();
        tower.setSprite(getTowerSprite(towerLevel));
    }

    private SpritePath getTowerSprite(int level) {
        switch (level) {
            case 0:
                return SpritePath.SMALLTOWER;
            case 1:
                return SpritePath.WOODTOWER;
            case 2:
                return SpritePath.ROCKTOWER;
            case 3:
                return SpritePath.BIGTOWER;
            case 4:
                return SpritePath.ARROWTOWER;
            default:
                return SpritePath.ARROWTOWER;
        }
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

    public boolean isUpgradable() {
        return towerLevel < 4;
    }
}
