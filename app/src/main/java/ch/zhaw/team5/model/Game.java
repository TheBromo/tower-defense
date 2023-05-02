package ch.zhaw.team5.model;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.g;
import org.checkerframework.checker.units.qual.h;

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

    public Game(Player player, GameState gameState, Canvas canvas) {
        this.player = player;
        this.wall = new Wall(new Point2D(canvas.getWidth() - 100, 0));
        this.phase = new PhaseCurrent();
        this.phaseCurrent = new PhaseCurrent();
        this.gameState = gameState;
        initTestEnv();
        initTowers(canvas.getWidth(), canvas.getHeight());
    }

    public void initTowers(double width, double height) {
        var distanceW = width / 6;
        var distanceH = height / 8;
        var deltaW = distanceW;
        for (int i = 0; i < 6; i++) {
            towerPositions.add(new TowerPosition(new Point2D(distanceW, distanceH), i + 1));
            i++;
            towerPositions.add(new TowerPosition(new Point2D(distanceW, height - 2 * distanceH), i + 1));
            distanceW += deltaW * 1.5;
        }
    }

    public void initTestEnv() {
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
            double MS_PER_UPDATE = 500.0; // TODO question: idk how we determine this. Found nothing online.

            while (lag >= MS_PER_UPDATE) {
                // TODO update();
                lag -= MS_PER_UPDATE;
                gameState.setRenderNeeded(true);

            }
            System.out.println("calling rendering");
        }
    }

    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        g2d.setFill(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Enemy enemy : enemies) {
            enemy.render(canvas);
        }

        for (TowerPosition towerPosition : towerPositions) {
            towerPosition.render(canvas);
        }

        wall.render(canvas);
    }
}
