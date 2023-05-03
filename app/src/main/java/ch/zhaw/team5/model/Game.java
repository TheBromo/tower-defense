package ch.zhaw.team5.model;

import java.util.ArrayList;
import java.util.List;


import ch.zhaw.team5.GameState;
import ch.zhaw.team5.model.gameobj.Enemy;
import ch.zhaw.team5.model.gameobj.definitions.Renderable;
import ch.zhaw.team5.model.gameobj.TowerPosition;
import ch.zhaw.team5.model.gameobj.Wall;
import ch.zhaw.team5.model.phases.Phase;
import ch.zhaw.team5.model.phases.PhaseCurrent;
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
        enemies.add(new Enemy(new Point2D(100, 100)));

    }

    public void loop() {
        boolean running = true;

        double previous = System.currentTimeMillis();
        double lag = 0.0;

        while (true) {
            gameState.setRenderNeeded(false);
            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            double MS_PER_UPDATE = 100.0;

            while (lag >= MS_PER_UPDATE) {
                // TODO update();
                lag -= MS_PER_UPDATE;
            }
            gameState.setRenderNeeded(true);
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

    public void buildTower(int i) {
        towerPositions.get(i - 1).BuildTower();
    }
}
