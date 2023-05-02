package ch.zhaw.team5.model;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.g;

import ch.zhaw.team5.GameState;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Game implements Renderable {
    private List<TowerPosition> towerPositions = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private Wall wall;


    private Player player;
    private Phase phase;
    private List<Phase> phases = new ArrayList<>();
    private PhaseCurrent phaseCurrent;
    private GameState gameState;

    public Game(Player player, GameState gameState,Wall wall) {
        this.player = player;
        this.wall = wall;
        this.phase = new PhaseCurrent();
        this.phaseCurrent =new PhaseCurrent() ;
        this.gameState = gameState;
        initTestEnv();
    }

    public void initTestEnv(){
        enemies.add(new Enemy());
        
    }


    public void loop() {
        boolean running = true;

        double previous = System.nanoTime();
        double lag = 0.0;
        while (true) {
            double current = System.nanoTime();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            // TODO processInput();
            double MS_PER_UPDATE = 100.0; // TODO question: idk how we determine this. Found nothing online.

            while (lag >= MS_PER_UPDATE) {
                // TODO update();
                lag -= MS_PER_UPDATE;
            }
            System.out.println("calling rendering");
            gameState.setRenderNeeded(true);
        }
    }

    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        g2d.setFill(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        System.out.println("rendering...");
        
        for (Enemy enemy : enemies) {
            enemy.render(canvas);
        }

        for (TowerPosition towerPosition : towerPositions){
            towerPosition.render(canvas);
        }
        

        wall.render(canvas);
        
    }
}
