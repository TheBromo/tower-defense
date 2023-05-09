package ch.zhaw.team5.model.gameobj;

import ch.zhaw.team5.model.gameobj.definitions.MovingGameObject;
import ch.zhaw.team5.model.util.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Arrow extends MovingGameObject {

    Enemy enemy;
    Point2D position;
    Point2D target;
    
    float speed = 1.2f;
    Point2D velocity;

    public Arrow(Enemy target, Point2D startPosition) {
        super(startPosition, Sprite.SpritePath.DECO); //TODO insert Arrow Sprite here
        this.position = startPosition;
        this.target = target.getPosition(); //TODO get destination position of tower
        this.enemy = target;

        //compute the difference vector (start to end) = direction
        velocity = this.target.subtract(position);

        velocity.normalize();
        velocity.multiply(speed);
    }
    
    public void update() {
        position = position.add(velocity);
        if (position.equals(target)) {
            enemy.hit(20); //TODO magic number
        }
    }

    public boolean hasHitTarget() {
        return position.equals(target);
    }

    @Override
    public void render(Canvas canvas) {
        canvas.getGraphicsContext2D().drawImage(sprite.getSprite(), position.getX() - width / 2, position.getY() - height / 2,
            width, height);
    }
}
