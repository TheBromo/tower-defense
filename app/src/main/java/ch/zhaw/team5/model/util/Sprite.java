package ch.zhaw.team5.model.util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Sprite {
    private List<Image> sprites;
    private int intervalMs = 500;
    private int index = 0;
    private long lastUpdate = 0;

    public Sprite(SpritePath sprite) {
        var randomUtil = RandomUtil.getInstance();
        var imageLoader = ImageLoader.getInstance();
        sprites = new ArrayList<>();
        intervalMs = randomUtil.getRandomInRange(300, 500);
        lastUpdate = randomUtil.getRandomInRange(300, 500);
        
        int variant = 1;
        if (sprite.variants != 1) {
            variant = randomUtil.getRandomInRange(1, sprite.variants+1);
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

        DECO(3, List.of("Deco")),
        TOWER(3, List.of("Tower")),
        HOLE(1, List.of("Hole")),
        ENEMY(2, List.of("Enemy","Enemyb","Enemyc")),
        STREET(2, List.of("Street")),
        WALLBOTTOM(1, List.of("WallBottom")),
        WALLTOP(1, List.of("WallTop"));

        public final int variants;
        public final List<String> animationName;

        private SpritePath(int variants, List<String> animationName) {
            this.variants = variants;
            this.animationName = animationName;
        }

    }

}
