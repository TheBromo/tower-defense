package ch.zhaw.team5.model.gameobj;


import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.Sprite;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * The TowerPosition class represents a position in the game where a tower can be built.
 * It extends the StaticGameObject class and contains methods for managing the tower and its upgrades.
 *
 * @author strenman
 * @version 1.0.0
 */
public class TowerPosition extends StaticGameObject {

    private final Tower tower;
    private boolean built;
    private final int positionNumber;
    private int towerLevel;

    /**
     * Creates a new TowerPosition object at the specified position.
     *
     * @param position the position of the tower
     * @param number   the position number
     */
    public TowerPosition(Point2D position, int number) {
        super(position, Sprite.SpritePath.HOLE);
        tower = new Tower(position);
        positionNumber = number;
        height = 100;
        width = 100;
        built = false;
        towerLevel = 0;
    }

    /**
     * Renders the tower or tower position on the provided canvas.
     *
     * @param canvas the canvas to draw the tower or tower position on
     */
    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        if (hasTower()) {
            tower.render(canvas);
        } else {
            g2d.drawImage(sprite.getSprite(), position.getX() - width / 2, position.getY() - height / 2,
                width, height);
            g2d.setStroke(Color.GREY);
            g2d.strokeText(String.valueOf(positionNumber), position.getX(), position.getY());
        }
    }

    /**
     * Builds a tower at the tower position.
     */
    public void buildTower() {
        built = true;
    }

    /**
     * Upgrades the tower at the tower position.
     */
    public void upgradeTower() {
        towerLevel++;
        tower.upgrade();
        tower.setSprite(getTowerSprite(towerLevel));
    }

    private SpritePath getTowerSprite(int level) {
        return switch (level) {
            case 0 -> SpritePath.SMALLTOWER;
            case 1 -> SpritePath.WOODTOWER;
            case 2 -> SpritePath.ROCKTOWER;
            case 3 -> SpritePath.BIGTOWER;
            case 4 -> SpritePath.ARROWTOWER;
            default -> SpritePath.ARROWTOWER;
        };
    }

    public Tower getTower() {
        return tower;
    }

    /**
     * Checks if a tower has been built at the tower position.
     *
     * @return true if a tower has been built, false otherwise
     */
    public boolean hasTower() {
        return built;
    }

    /**
     * Checks if the tower at the tower position can be upgraded.
     *
     * @return true if the tower can be upgraded, false otherwise
     */
    public boolean isUpgradable() {
        return towerLevel < 4;
    }
}
