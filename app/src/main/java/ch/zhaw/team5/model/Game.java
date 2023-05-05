package ch.zhaw.team5.model;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.team5.GameState;
import ch.zhaw.team5.model.gameobj.Enemy;
import ch.zhaw.team5.model.gameobj.Path;
import ch.zhaw.team5.model.gameobj.definitions.Renderable;
import ch.zhaw.team5.model.gameobj.TowerPosition;
import ch.zhaw.team5.model.gameobj.Wall;
import ch.zhaw.team5.model.phases.Phase;
import ch.zhaw.team5.model.phases.PhaseCurrent;
import ch.zhaw.team5.model.util.RandomUtil;
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
    private Path path;
    private int wantedEnemies = 50;

    public Game(Player player, GameState gameState, Canvas canvas) {
        this.player = player;
        this.wall = new Wall(new Point2D(canvas.getWidth() - 100, 0));
        this.phase = new PhaseCurrent();
        this.phaseCurrent = new PhaseCurrent();
        this.gameState = gameState;
        path = new Path(new Point2D(0, canvas.getHeight() / 2), new Point2D(canvas.getWidth(), canvas.getHeight() / 2));

        initTowers(canvas.getWidth(), canvas.getHeight());
    }

    public void initTowers(double width, double height) {
        var distanceW = width / 6;
        var distanceH = height / 4;
        var deltaW = distanceW;
        for (int i = 0; i < 6; i++) {
            towerPositions.add(new TowerPosition(new Point2D(distanceW, distanceH), i + 1));
            i++;
            towerPositions.add(new TowerPosition(new Point2D(distanceW, 3 * distanceH), i + 1));
            distanceW += deltaW * 1.5;
        }
    }

    private void spawnEnemy() {
        int radius = path.getRadius();
        int y = (int) path.getStart().getY();
        var startY = RandomUtil.getInstance().getRandomInRange(y - radius, y + radius);
        var startX = RandomUtil.getInstance().getRandomInRange(-200, 0);
        var newPost = new Point2D(startX, startY);
        while (interSects(newPost)) {
            newPost = newPost.add(-50, 0);
        }
        enemies.add(new Enemy(newPost));
    }

    private boolean interSects(Point2D newPos) {
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().distance(newPos) <= enemy.getRadius() * 2) {
                return true;
            }
        }
        return false;
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
                update();
                lag -= MS_PER_UPDATE;
            }
            gameState.setRenderNeeded(true);
        }
    }

    private void update() {
        for (Enemy enemy : enemies) {
            enemy.update(enemies, path);
            if (enemy.outOfScreen((int) wall.getPosition().getX())) {
                // TODO add Damage
            }
        }
        enemies.removeIf(e -> e.outOfScreen((int) wall.getPosition().getX()));

        while (enemies.size() < wantedEnemies) {
            spawnEnemy();
        }
    }

    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        g2d.setFill(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        path.render(canvas);
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
