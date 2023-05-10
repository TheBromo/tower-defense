package ch.zhaw.team5.model.util;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.scene.image.Image;

public class Sprite {
    private List<Image> sprites;
    private int intervalMs = 300;
    private int index = 0;
    private long lastUpdate = 0;

    public Sprite(SpritePath sprite) {
        var randomUtil = RandomUtil.getInstance();
        var imageLoader = ImageLoader.getInstance();
        sprites = new ArrayList<>();
        intervalMs = randomUtil.getRandomInRange(sprite.minAnimationTimer, sprite.maxAnimationTimer);
        lastUpdate = randomUtil.getRandomInRange(sprite.minAnimationTimer, sprite.maxAnimationTimer);

        int variant = 1;
        if (sprite.variants != 1) {
            variant = randomUtil.getRandomInRange(1, sprite.variants + 1);
        }

        for (String path : sprite.animationName) {
            sprites.add(imageLoader.getByName(path + variant));
        }
    }

    public Image getSprite() {
        if (System.currentTimeMillis() - lastUpdate >= intervalMs) {
            continueIndex();
            lastUpdate = System.currentTimeMillis();
        }
        return sprites.get(index);

    }

    private void continueIndex() {
        index++;
        if (index >= sprites.size()) {
            index = 0;
        }
    }

    public int getIntervalMs() {
        return intervalMs;
    }


    public enum SpritePath {
        ARROW(1,List.of("Arrow")),
        DECO(3, List.of("Deco")),
        SMALLTOWER(3, List.of("SmallTower", "SmallTowerb"), 1000, 9000),
        WOODTOWER(3, List.of("WoodTower", "WoodTowerb"), 1000, 9000),
        ROCKTOWER(3, List.of("RockTower", "RockTowerb"), 1000, 9000),
        BIGTOWER(3, List.of("BigTower", "BigTowerb"), 1000, 9000),
        ARROWTOWER(3, List.of("ArrowTower", "ArrowTowerb"), 1000, 9000),
        HOLE(1, List.of("Hole")),
        ENEMY(5, List.of("Enemy", "Enemyb", "Enemyc"), 100, 300),
        STREET(2, List.of("Street")),
        WALLBOTTOM(1, List.of("WallBottom")),
        WALLTOP(1, List.of("WallTop"));

        public final int variants, minAnimationTimer, maxAnimationTimer;
        public final List<String> animationName;

        private SpritePath(int variants, List<String> animationName, int minAnimationTimer, int maxAnimationTimer) {
            this.variants = variants;
            this.animationName = animationName;
            this.minAnimationTimer = minAnimationTimer;
            this.maxAnimationTimer = maxAnimationTimer;
        }

        private SpritePath(int variants, List<String> animationName) {
            this.variants = variants;
            this.animationName = animationName;
            this.minAnimationTimer = 100000;
            this.maxAnimationTimer = 100001;
        }

    }

}
