package ch.zhaw.team5.model.gameobj.definitions;


import ch.zhaw.team5.model.util.Sprite;
import ch.zhaw.team5.model.util.Sprite.SpritePath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class StaticGameObject implements Renderable {
    protected Point2D position;
    protected Sprite sprite;
    protected int width, height;

    public StaticGameObject(Point2D position, SpritePath spritePath) {
        this.position = position;
        this.sprite = new Sprite(spritePath);

    }

    public StaticGameObject(Point2D position) {
        this.position = position;
    }

    
    public StaticGameObject() {
    }


    public Point2D getPosition() {
        return position;
    }



    public void renderDebugLines(GraphicsContext g2d){
        g2d.setFill(new Color(1, 0,0, 0.2));
        g2d.fillRect(position.getX(),position.getY(), width, height);
    }
}
