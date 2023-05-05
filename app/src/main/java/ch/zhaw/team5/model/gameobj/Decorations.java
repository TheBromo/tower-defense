package ch.zhaw.team5.model.gameobj;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.RandomUtil;
import ch.zhaw.team5.model.util.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Decorations extends StaticGameObject {

    List<Sprite> sprites = new ArrayList<>();
    List<Point2D> positions = new ArrayList<>();

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

    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        for (int i = 0; i < sprites.size(); i++) {
            g2d.drawImage(sprites.get(i).getSprite(), positions.get(i).getX(), positions.get(i).getY(), 50, 50);
        }
    }

}
