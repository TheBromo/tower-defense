package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.RandomUtil;
import ch.zhaw.team5.model.util.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * The Decorations class represents a collection of decorations in the game.
 * It extends the StaticGameObject class and contains methods for managing the decorations' positions and rendering.
 *
 * @author strenman
 * @version 1.0.0
 */
public class Decorations extends StaticGameObject {

    List<Sprite> sprites = new ArrayList<>();
    List<Point2D> positions = new ArrayList<>();

    /**
     * Creates a new Decorations object with the specified amount of decorations within the specified boundaries.
     *
     * @param amount  the number of decorations to generate
     * @param topX    the top boundary on the x-axis
     * @param topY    the top boundary on the y-axis
     * @param bottomX the bottom boundary on the x-axis
     * @param bottomY the bottom boundary on the y-axis
     */
    public Decorations(int amount, int topX, int topY, int bottomX, int bottomY) {
        super();
        generatePositions(amount, topX, topY, bottomX, bottomY);
    }

    private void generatePositions(int amount, int topX, int topY, int bottomX, int bottomY) {
        var randomUtil = RandomUtil.getInstance();
        for (int i = 0; i < amount; i++) {
            sprites.add(new Sprite(Sprite.SpritePath.DECO));
            positions.add(new Point2D(randomUtil.getRandomInRange(topX, bottomX),
                randomUtil.getRandomInRange(topY, bottomY)));
        }
    }

    /**
     * Renders the decorations on the provided canvas.
     *
     * @param canvas the canvas to draw the decorations on
     */
    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        for (int i = 0; i < sprites.size(); i++) {
            g2d.drawImage(sprites.get(i).getSprite(), positions.get(i).getX(), positions.get(i).getY(), 50, 50);
        }
    }

}
