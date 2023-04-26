package ch.zhaw.team5;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class StaticGameObject implements Game {
    private Point2D position;
    private Image sprite;
    public StaticGameObject(Point2D position, Image sprite){
        this.position = position;
        this.sprite = sprite;

    }

    @Override
    public void render() {

    }

    public Point2D getPosition(){
        return position;
    }

    public Image getSprite(){
        return sprite;
    }









}
