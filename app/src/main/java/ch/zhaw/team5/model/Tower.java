package ch.zhaw.team5.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tower extends StaticGameObject {

    public final int range = 900;
    public final int price = 50;

    private List<Arrow> arrows;


    public Tower(Point2D position, Image sprite) {
        super(position, sprite);
        arrows = new ArrayList<>();

        //TODO load texture for tower
    }

    @Override
    public void render() {
        arrows.removeIf(arrow -> arrow.hasHitTarget());

        //TODO call render on each arrow 

        //TODO call render on this tower

    }

    private boolean inRange(Enemy enemy){
        return position.distance(enemy.getPosition()) <= range;
    }

    public Enemy shootAtEnemies(List<Enemy> enemies){
        enemies.removeIf(enemy -> inRange(enemy));
        
        Random rand = new Random();
        var target = enemies.get(rand.nextInt(enemies.size()));
        
        arrows.add(new Arrow(target, this.position));

        return target;
    }
}
