package ch.zhaw.team5;

public abstract class GameObjectAttributes implements Game {



    
    // ToDo - wie sollen wir das mit den Positionen machen? Eine Eigene Klasse vielleicht? Werden die die Methoden hier in dieser Klasse implementiert ?

    private Position postion;
    private Sprites sprites;

    @Override
    public void render() {

    }




    public abstract void postion();

    public abstract void image();





}
