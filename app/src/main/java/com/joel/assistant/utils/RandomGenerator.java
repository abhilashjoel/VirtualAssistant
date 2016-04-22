package com.joel.assistant.utils;

import java.util.Random;

/**
 * Created by Joel on 15-04-2016.
 */
public class RandomGenerator {
    private static Random random = new Random();

    RandomGenerator() {
        random = new Random();
    }

    public static int get(int max) {
        return random.nextInt(max);
    }


}
