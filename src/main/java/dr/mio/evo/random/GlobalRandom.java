// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

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
