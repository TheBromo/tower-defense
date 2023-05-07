package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.Renderable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Arrow implements Renderable {

    Enemy enemy;
    Point2D position;
    Point2D target;
    
    float speed = 1.2f;
    Point2D velocity;


    public Arrow(Enemy target, Point2D startPosition) {
        super();
        this.position = startPosition;
        this.target = target.getPosition();
        this.enemy = target;

        //compute the difference vector (start to end) = direction
        velocity = this.target.subtract(position);

        velocity.normalize();
        velocity.multiply(speed);
    }

    public boolean hasHitTarget(){
        return enemy.isAlive();
    }

    @Override
    public void render(Canvas canvas) {
        //TODO ??
    }
}
