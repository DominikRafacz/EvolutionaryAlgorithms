package dr.mio.evo.random;

import java.util.Random;

public class GlobalRandom {
    private static GlobalRandom INSTANCE;

    private final Random random;

    private GlobalRandom() {
        random = new Random();
    }

    private GlobalRandom(long seed) {
        random = new Random(seed);
    }

    public static void setUp(long seed) {
        INSTANCE = new GlobalRandom(seed);
    }

    public static GlobalRandom get() {
        return INSTANCE;
    }

    public static Random getRandom() {
        if (INSTANCE == null)
            INSTANCE = new GlobalRandom();
        return INSTANCE.random;
    }
}
