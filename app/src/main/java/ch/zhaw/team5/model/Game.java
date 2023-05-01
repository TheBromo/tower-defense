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

        while (running) {
            render();

            //TODO game logic
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void render() {
    }
}
