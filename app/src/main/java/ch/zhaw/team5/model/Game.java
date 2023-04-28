package ch.zhaw.team5.model;

public class Game implements Renderable{

    //TODO: Sind dies alle Objekte?
    private Tower tower;
    private Wall wall;
    private Phase phase;
    private Path path;
    private Enemy enemy;
    private Money money;
    private Player player;
    private Arrow arrow;

    public Game(Tower tower, Wall wall, Phase phase, Path path, Enemy enemy, Money money, Player player, Arrow arrow) {
        this.tower = tower;
        this.wall = wall;
        this.phase = phase;
        this.path = path;
        this.enemy = enemy;
        this.money = money;
        this.player = player;
        this.arrow = arrow;
    }

    //TODO: Wie wird render implementiert?
    @Override
    public void render() {



    }
}
