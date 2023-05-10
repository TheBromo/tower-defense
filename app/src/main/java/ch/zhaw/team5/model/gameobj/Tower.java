package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.StaticGameObject;
import ch.zhaw.team5.model.util.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tower extends StaticGameObject {

    public final int range = 200;
    public final int price = 50;

    private int level = 0;

    private int damageLevelOne = 20; // TODO prob needs improvement
    private int damageLevelTwo = 50;
    private long interval = 5000, lastShot = 0;

    private List<Arrow> arrows;

    public Tower(Point2D position) {
        super(position, Sprite.SpritePath.SMALLTOWER);
        arrows = new ArrayList<>();
        height = 100;
        width = 100;
    }

    @Override
    public void render(Canvas canvas) {
        // TODO maybe remove this and move it to an update function

        canvas.getGraphicsContext2D().drawImage(sprite.getSprite(), position.getX() - width / 2,
                position.getY() - height / 2,
                width, height);

        canvas.getGraphicsContext2D().strokeOval((position.getX()) - range*2 / 2,
                (position.getY()) - range*2 / 2, range * 2, range * 2);

        for (Arrow arrow : arrows) {
            arrow.render(canvas);
        }

    }

    public void upgrade() {
        level++;
    }

    private boolean outOfRange(Enemy enemy) {
        return position.distance(enemy.getPosition()) > range;
    }

    public void shootAtEnemies(List<Enemy> enemies) {

        if (enemies.size() > 0 && interval < System.currentTimeMillis() - lastShot) {

            var possibleTargets = new ArrayList<>(enemies);
            possibleTargets.removeIf(enemy -> outOfRange(enemy));

            if (possibleTargets.size() == 0)
                return;

            Random rand = new Random();
            var target = possibleTargets.get(rand.nextInt(possibleTargets.size()));

            arrows.add(new Arrow(target, this.position, level == 0 ? damageLevelOne : damageLevelTwo));
            lastShot = System.currentTimeMillis();
        }
    }

    public void update() {
        arrows.removeIf(Arrow::hasHitTarget);
        for (Arrow arrow : arrows) {
            arrow.update();
        }
    }

}
