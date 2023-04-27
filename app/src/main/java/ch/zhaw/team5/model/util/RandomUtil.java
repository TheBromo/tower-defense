package ch.zhaw.team5.model.util;


import java.util.List;
import java.util.Random;




public class RandomUtil {
    
    private static RandomUtil instance = new RandomUtil();
    private Random generator;

    private RandomUtil() {
        generator = new Random();
    }

    public static RandomUtil getInstance() {
        if (instance == null) {
            instance = new RandomUtil();
        }
        return instance;
    }

    public <T> T getRandomCollectionElement(List<T> list){
        return  list.get(generator.nextInt(list.size()));
    }


}
