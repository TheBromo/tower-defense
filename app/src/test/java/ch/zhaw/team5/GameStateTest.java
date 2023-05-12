package ch.zhaw.team5;

import ch.zhaw.team5.model.gameobj.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class to test the game state behaviour.
 *
 * @author mettlmi1
 * @version 1.0.0
 */
public class GameStateTest {

    private GameState gameState;

    /**
     * sets up a game state object with a real canvas & game state object.
     */
    @BeforeEach
    void setUp() {
        gameState = new GameState();
    }

    /**
     * Tests if money is correctly subtracted and health added at buyHealth method.
     */
    @Test
    public void testBuyHealth() {
        Enemy enemy = mock(Enemy.class);
        when(enemy.getDamage()).thenReturn(30);
        gameState.enemyInvaded(enemy);
        double healthBefore = gameState.healthProperty().get();
        double moneyBefore = gameState.moneyProperty().get();
        gameState.buyHealth();

        assertEquals(healthBefore + gameState.getHealthDose(), gameState.healthProperty().get());
        assertEquals(moneyBefore - gameState.getPriceOfHealthLoading(), gameState.moneyProperty().get());
    }

    /**
     * Negative test case to ensure that the player has enough money to buy health.
     */
    @Test
    public void testBuyHealthInsufficientMoney() {
        Enemy enemy = mock(Enemy.class);
        when(enemy.getDamage()).thenReturn(30);

        gameState.enemyInvaded(enemy);
        gameState.addMoney(-150);
        double healthBefore = gameState.healthProperty().get();
        double moneyBefore = gameState.moneyProperty().get();
        gameState.buyHealth();

        assertEquals(healthBefore, gameState.healthProperty().get());
        assertEquals(moneyBefore, gameState.moneyProperty().get());
    }

    /**
     * Tests if an invaded enemy subtracts health off the players' health.
     */
    @Test
    public void testEnemyInvaded() {
        double healthBefore = gameState.healthProperty().get();
        int damage = 30;

        Enemy enemy = mock(Enemy.class);
        when(enemy.getDamage()).thenReturn(damage);
        gameState.enemyInvaded(enemy);

        assertEquals(healthBefore - 30, gameState.healthProperty().get());
    }

    /**
     * Tests if the player can buy a tower and the money is correctly subtracted.
     */
    @Test
    public void testBuyTower() {
        int moneyBefore = gameState.moneyProperty().get();
        assertTrue(gameState.buyTower());

        assertEquals(moneyBefore - gameState.getBuyTowerCost(), gameState.moneyProperty().get());
    }

    /**
     * Tests if the player can upgrade a tower and the money is correctly subtracted.
     */
    @Test
    public void testUpgradeTower() {
        int moneyBefore = gameState.moneyProperty().get();
        assertTrue(gameState.upgradeTower());

        assertEquals(moneyBefore - gameState.getUpgradeTowerCost(), gameState.moneyProperty().get());
    }

    /**
     * Negative test case to ensure that the player has enough money to buy a tower.
     */
    @Test
    public void testBuyTowerInsufficientMoney() {
        int moneyBefore = gameState.moneyProperty().get();
        int sub = -150;
        gameState.addMoney(sub);
        assertFalse(gameState.buyTower());
        assertEquals(moneyBefore + sub, gameState.moneyProperty().get());
    }

    /**
     * Negative test case to ensure that the player has enough money to upgrade a tower.
     */
    @Test
    public void testUpgradeTowerInsufficientMoney() {
        int moneyBefore = gameState.moneyProperty().get();
        int sub = -150;
        gameState.addMoney(-150);
        assertFalse(gameState.upgradeTower());
        assertEquals(moneyBefore + sub, gameState.moneyProperty().get());
    }
}
