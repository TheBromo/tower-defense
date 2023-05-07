package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tower extends StaticGameObject {

    public final int range = 900;
    public final int price = 50;

    private List<Arrow> arrows;

    public Tower(Point2D position) {
        super(position, Sprite.SpritePath.SMALLTOWER);
        arrows = new ArrayList<>();
        height = 100;
        width = 100;
    }

    @Override
    public void render(Canvas canvas) {
        //TODO maybe remove this and move it to an update function
        arrows.removeIf(arrow -> arrow.hasHitTarget());

        canvas.getGraphicsContext2D().drawImage(sprite.getSprite(), position.getX() - width / 2, position.getY() - height / 2,
                width, height);

        //TODO call render on each arrow

    }

    private boolean outOfRange(Enemy enemy) {
        return position.distance(enemy.getPosition()) > range;
    }

    public Enemy shootAtEnemies(List<Enemy> enemies) {
        var possibleTargets = new ArrayList<>(enemies);
        possibleTargets.removeIf(enemy -> outOfRange(enemy));

        Random rand = new Random();
        var target = possibleTargets.get(rand.nextInt(possibleTargets.size()));

        arrows.add(new Arrow(target, this.position));

        return target;
    }

}
