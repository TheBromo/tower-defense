package ch.zhaw.team5.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class TowerPosition extends StaticGameObject {

    public TowerPosition(Point2D position, Image sprite) {
        super(position, sprite);
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    
    public boolean hasTower(){
        return false;

    }


    public int getNumber(){
        return 0;

    }


    public boolean BuildTower(){
        return false;

    }

    public Tower getTower(){

    }
}
