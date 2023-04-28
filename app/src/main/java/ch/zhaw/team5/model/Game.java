package ch.zhaw.team5.model;

public class Game implements Renderable{
    private Tower tower;
    private Wall wall;
    private Phase phase;
    private Path path;
    private Enemy enemy;
    private Money money;
    private Player player;

    public Game(Tower tower, Wall wall, Phase phase, Path path, Enemy enemy, Money money, Player player) {
        this.tower = tower;
        this.wall = wall;
        this.phase = phase;
        this.path = path;
        this.enemy = enemy;
        this.money = money;
        this.player = player;
    }

    @Override
    public void render() {



    }
}
