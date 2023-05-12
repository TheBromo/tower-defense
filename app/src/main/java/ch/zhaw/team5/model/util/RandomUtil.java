package ch.zhaw.team5.model.util;

import java.util.List;
import java.util.Random;

/**
 * Singleton class that provides utility methods for generating random values in the Tower Defense game.
 * @author strenman
 * @version 1.0.0
 */
public class RandomUtil {
    
    private static RandomUtil instance = new RandomUtil();
    private Random generator;

    private RandomUtil() {
        generator = new Random();
    }

    /**
     * Provides global access to the singleton instance of RandomUtil.
     *
     * @return the singleton instance of RandomUtil
     */
    public static RandomUtil getInstance() {
        if (instance == null) {
            instance = new RandomUtil();
        }
        return instance;
    }

    /**
     * Returns a random element from a given list.
     *
     * @param list the list from which an element should be picked randomly
     * @return a random element from the list
     */
    public <T> T getRandomCollectionElement(List<T> list){
        return  list.get(generator.nextInt(list.size()));
    }

    /**
     * Returns a random integer within a given range.
     *
     * @param bottom the lower bound of the range
     * @param top the upper bound of the range
     * @return a random integer within the range
     */
    public int getRandomInRange(int bottom, int top){
        return generator.nextInt(bottom,top);
    }

    /**
     * Returns a random double within a given range.
     *
     * @param bottom the lower bound of the range
     * @param top the upper bound of the range
     * @return a random double within the range
     */
    public double getRandomInRangeDouble(double bottom, double top){
        return generator.nextDouble(bottom, top);
    }
}
