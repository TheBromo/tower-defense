package ch.zhaw.team5.model;

import ch.zhaw.team5.GameState;
import ch.zhaw.team5.model.gameobj.*;
import ch.zhaw.team5.model.gameobj.definitions.Renderable;
import ch.zhaw.team5.model.phases.AttackPhase;
import ch.zhaw.team5.model.phases.PausePhase;
import ch.zhaw.team5.model.phases.Phase;
import ch.zhaw.team5.model.util.RandomUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * The Game class represents the main structure of the game.
 * It is responsible for game object management and game logic.
 * @author all
 * @version 1.0.0
 */
public class Game implements Renderable {
    private List<TowerPosition> towerPositions = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Decorations> decorations = new ArrayList<>();
    private Path path;
    private Wall wall;

    private GameState gameState;

    private Phase currentPhase;
    private Deque<Phase> phases;

    /**
     * Initializes a new Game object.
     *
     * @param gameState the state of the game
     * @param canvas the canvas to draw the game on
     */
    public Game(GameState gameState, Canvas canvas) {
        this.wall = new Wall(new Point2D(canvas.getWidth() - 100, 0));
        this.gameState = gameState;
        phases = new ArrayDeque<>();
        currentPhase = new PausePhase(20);
        phases.addFirst(new AttackPhase(180));

        initEnviroment(canvas);
        initTowers(canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Initializes the game environment.
     *
     * @param canvas the canvas to draw the environment on
     */
    public void initEnviroment(Canvas canvas) {
        path = new Path(new Point2D(0, canvas.getHeight() / 2), new Point2D(canvas.getWidth(), canvas.getHeight() / 2));

        decorations.add(new Decorations(5, 0, 0, (int) canvas.getWidth() - 100, (int) canvas.getHeight() / 2));
        decorations.add(new Decorations(5, 0, (int) canvas.getHeight() / 2, (int) canvas.getWidth() - 100,
                (int) canvas.getHeight()));
    }

    /**
     * Initializes the game towers.
     *
     * @param width the width of the canvas
     * @param height the height of the canvas
     */
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

    /**
     * The main game loop.
     */
    public void loop() {
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

    private void updatePhases() {
        if (currentPhase.hasEnded()) {
            currentPhase.increaseDifficulty();
            phases.addLast(currentPhase);
            currentPhase = phases.removeFirst();
            currentPhase.restartTimer();
            gameState.setGamePhaseName(currentPhase.toString());
        } else {
            currentPhase.updatePhase();
            gameState.setProgress(currentPhase.getPhaseProgress());
        }
    }

    private void update() {

        for (Enemy enemy : enemies) {
            enemy.update(enemies, path);
            if (enemy.outOfScreen((int) path.getEnd().getX())) {
                gameState.enemyInvaded(enemy);
            } else if (!enemy.isAlive()) {
                gameState.addMoney(enemy.getReward());
            }
        }

        enemies.removeIf(e -> e.outOfScreen((int) path.getEnd().getX()) || !e.isAlive());

        for (TowerPosition towerPosition : towerPositions) {
            if (towerPosition.hasTower()) {
                var tower = towerPosition.getTower();
                tower.update();
                tower.shootAtEnemies(enemies);
            }
        }

        while (enemies.size() < currentPhase.getEnemyAmount()) {
            spawnEnemy();
        }

        if (gameState.healthProperty().get() <= 0) {
            gameState.setGameEnded(true);
        }

        updatePhases();
    }

    /**
     * Renders the game on the canvas.
     *
     * @param canvas the canvas to draw the game on
     */
    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        g2d.setFill(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Decorations decoration : decorations) {
            decoration.render(canvas);
        }
        path.render(canvas);

        for (Enemy enemy : enemies) {
            enemy.render(canvas);
        }

        for (TowerPosition towerPosition : towerPositions) {
            if (towerPosition.hasTower()) {
                var tower = towerPosition.getTower();
                tower.render(canvas);
            } else {
                towerPosition.render(canvas);
            }
        }

        wall.render(canvas);
    }

    /**
     * Builds or upgrades a tower.
     *
     * @param id the id of the tower position
     * @return true if the tower was built or upgraded, false otherwise
     */
    public boolean buildOrUpgradeTower(int id) {
        var towerPosition = towerPositions.get(id - 1);
        if (!towerPosition.hasTower()) {
            if (gameState.buyTower()) {
                towerPositions.get(id - 1).buildTower();
                return true;
            } else {
                return false;
            }
        } else if (towerPosition.isUpgradable()) {
            if (gameState.upgradeTower()) {
                towerPositions.get(id - 1).upgradeTower();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if a tower can be built or upgraded.
     *
     * @param id the id of the tower position
     * @return true if the tower can be built or upgraded, false otherwise
     */
    public boolean canUpgradeOrBuildTower(int id) {
        return !towerPositions.get(id - 1).hasTower() || towerPositions.get(id - 1).isUpgradable();
    }
}
