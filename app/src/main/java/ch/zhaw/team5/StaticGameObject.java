package ch.zhaw.team5;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class StaticGameObject implements Game {
    private Point2D postion;
    private Image sprites;
    public StaticGameObject(Point2D postion, Image sprites){
        this.postion = postion;
        this.sprites = sprites;

    }

    @Override
    public void render() {

    }

    public Point2D getPosition(){
        return postion;
    }

    public Image getSprites(){
        return sprites;
    }










}
