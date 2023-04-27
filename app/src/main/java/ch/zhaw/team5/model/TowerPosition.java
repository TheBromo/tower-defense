package ch.zhaw.team5.model;

import java.util.ArrayList;
import java.util.Arrays;

import ch.zhaw.team5.model.util.ImageLoader;
import ch.zhaw.team5.model.util.RandomUtil;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class TowerPosition extends StaticGameObject {

    private Tower tower;
    private int positionNumber;
    private final ArrayList<String> towerImageNames = new ArrayList<>(Arrays.asList("Tower1", "Tower2", "Tower3"));

    public TowerPosition(Point2D position, int number) {
        super(position, ImageLoader.getInstance().getByName("Hole"));
        positionNumber = number;
    }

    @Override
    public void render() {
        if (hasTower()) {
            tower.render();
        }

        //TODO implement render method
    }

    public boolean BuildTower() {
        if (!hasTower()) {
            tower = new Tower(position, getRandomSprite());
            return true;
        }
        return false;
    }

    private Image getRandomSprite() {
        var imageLoader = ImageLoader.getInstance();
        var random = RandomUtil.getInstance();
        return imageLoader.getByName(random.getRandomCollectionElement(towerImageNames));
    }

    public Tower getTower() {
        return tower;
    }

    public boolean hasTower() {
        return tower != null;
    }

    public int getNumber() {
        return positionNumber;
    }
}
