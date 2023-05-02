package ch.zhaw.team5.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
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
    public void render(Canvas canvas) {
        System.out.println("rendering tower");
        arrows.removeIf(arrow -> arrow.hasHitTarget());

        //TODO call render on each arrow 

        //TODO call render on this tower

    }

    private boolean outOfRange(Enemy enemy){
        return position.distance(enemy.getPosition()) > range;
    }

    public Enemy shootAtEnemies(List<Enemy> enemies){
        var possibleTargets= new ArrayList<>(enemies);
        possibleTargets.removeIf(enemy -> outOfRange(enemy));
        
        Random rand = new Random();
        var target = possibleTargets.get(rand.nextInt(possibleTargets.size()));
        
        arrows.add(new Arrow(target, this.position));

        return target;
    }

}
