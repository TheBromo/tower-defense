package ch.zhaw.team5;

import ch.zhaw.team5.model.Game;
import ch.zhaw.team5.model.gameobj.*;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Test class to test the gameState behaviour.
 *
 * @author mettlmi1
 * @version 1.0.0
 */
class GameTest {
    private Game game;
    private GameState gameState;
    private Canvas canvas;

    /**
     * sets up a game object with a real canvas & game state object.
     */
    @BeforeEach
    void setUp() {
        gameState = new GameState();
        canvas = new Canvas();
        canvas.setHeight(500);
        canvas.setWidth(1000);

        game = new Game(gameState, canvas);
    }

    /**
     * Tests that four enemies aren't positioned at the same position.
     */
    @Test
    public void testEnemyPositions() {
        game.spawnEnemy();
        game.spawnEnemy();
        game.spawnEnemy();
        game.spawnEnemy();

        for (int i = 0; i < game.getEnemies().size(); i++) {
            int enemyIndex = i;
            assertFalse(game.getEnemies().stream().anyMatch(item -> game.getEnemies().indexOf(item) != enemyIndex &&
                item.getPosition().equals(game.getEnemies().get(enemyIndex).getPosition())));
        }
    }

    /**
     * Tests if a tower is built correctly.
     */
    @Test
    public void testBuildTower() {
        game.buildOrUpgradeTower(1);
        assertTrue(game.getTowerPositions().get(0).hasTower());
    }

    /**
     * Tests that a tower that has not been built yet isn't placed.
     */
    @Test
    public void testNotBuiltTower() {
        assertFalse(game.getTowerPositions().get(0).hasTower());
    }

    /**
     * Tests that the canUpgradeOrBuildTower() method correctly detects that a tower is maxed out.
     */
    @Test
    public void testMaxedTowerNotUpgradable() {
        gameState.addMoney(5000);

        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        assertFalse(game.canUpgradeOrBuildTower(1));
    }

    /**
     * Tests that it is not allowed to upgrade a maxed out tower.
     */
    @Test
    public void testInvalidUpgradeMaxedTower() {
        gameState.addMoney(5000);
        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        game.buildOrUpgradeTower(1);
        assertFalse(game.buildOrUpgradeTower(1));
    }

    /**
     * Tests that the render method on the GameObjects is called once during the games render method.
     */
    @Test
    public void testRender() {
        Tower tower1 = mock(Tower.class);
        TowerPosition towerPosition1 = mock(TowerPosition.class);
        when(towerPosition1.getTower()).thenReturn(tower1);
        when(towerPosition1.hasTower()).thenReturn(true);
        Tower tower2 = mock(Tower.class);
        TowerPosition towerPosition2 = mock(TowerPosition.class);
        when(towerPosition2.hasTower()).thenReturn(true);
        when(towerPosition2.getTower()).thenReturn(tower2);

        List<TowerPosition> towerPositions = new ArrayList<>();
        towerPositions.add(towerPosition1);
        towerPositions.add(towerPosition2);

        Enemy enemy1 = mock(Enemy.class);
        Enemy enemy2 = mock(Enemy.class);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);

        Decorations deco1 = mock(Decorations.class);
        Decorations deco2 = mock(Decorations.class);
        List<Decorations> decorations = new ArrayList<>();
        decorations.add(deco1);
        decorations.add(deco2);

        Path path = mock(Path.class);
        Wall wall = mock(Wall.class);

        Game game = new Game(towerPositions, enemies, path, decorations, wall, gameState);

        game.render(canvas);
        for (var tower : towerPositions) {
            verify(tower.getTower(), atLeastOnce()).render(canvas);
        }
        for (var enemy : enemies) {
            verify(enemy, atLeastOnce()).render(canvas);
        }
        verify(path, atLeastOnce()).render(canvas);
        verify(wall, atLeastOnce()).render(canvas);
        for (var deco : decorations) {
            verify(deco, atLeastOnce()).render(canvas);
        }
    }

}

