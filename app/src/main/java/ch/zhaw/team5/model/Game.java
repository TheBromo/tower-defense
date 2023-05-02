package ch.zhaw.team5.model;

import java.util.ArrayList;
import java.util.List;

public class Game implements Renderable {
    private List<TowerPosition> towerPositions = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Phase> phases = new ArrayList<>();
    private Player player;
    private Wall wall;
    private Phase phase;
    private PhaseCurrent phaseCurrent;

    public Game(Player player, Wall wall, Phase phase, PhaseCurrent phaseCurrent) {
        this.player = player;
        this.wall = wall;
        this.phase = phase;
        this.phaseCurrent = phaseCurrent;
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
            double MS_PER_UPDATE = 0.0; // TODO question: idk how we determine this. Found nothing online.

            while (lag >= MS_PER_UPDATE) {
                // TODO update();
                lag -= MS_PER_UPDATE;
            }

            render();
        }
    }

    @Override
    public void render() {
    }
}
