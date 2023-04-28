package ch.zhaw.team5.model;

import java.util.ArrayList;

public class Game implements Renderable {
    private List<TowerPosition> towerPositions = new ArrayList<>();
    private List<Enemies> enemies = new ArrayList<>();
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

    @Override
    public void render() {
    }
}
