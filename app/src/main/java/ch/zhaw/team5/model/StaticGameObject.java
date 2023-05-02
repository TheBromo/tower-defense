package ch.zhaw.team5.model;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class StaticGameObject implements Renderable {
    protected Point2D position;
    private Image sprite;
    protected int width, height;

    public StaticGameObject(Point2D position, Image sprite) {
        this.position = position;
        this.sprite = sprite;

    }


    public Point2D getPosition() {
        return position;
    }

    public Image getSprite() {
        return sprite;
    }

    public void renderDebudLines(GraphicsContext g2d){
        g2d.setFill(new Color(1, 0,0, 0.2));
        g2d.fillRect(position.getX(),position.getY(), width, height);
    }
}
