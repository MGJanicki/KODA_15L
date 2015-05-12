package model;

import java.util.Random;

/**
 * Created by Marcin on 2015-05-12.
 */
public abstract class RandomSource extends Source {

    Random random;

    public RandomSource()
    {
        random = new Random();
    }
}
