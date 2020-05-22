// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.population;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.PopulationDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PopulationDescEuclideanSpace implements PopulationDesc<GenotypeEuclidean> {

    private final int dimension;
    private final int initialPopulationSize;
    private final double min;
    private final double max;

    private final Random random = GlobalRandom.getRandom();

    public PopulationDescEuclideanSpace(int dimension, int initialPopulationSize, double min, double max) {
        this.dimension = dimension;
        this.initialPopulationSize = initialPopulationSize;
        this.min = min;
        this.max = max;
    }

    /*
    *  inicjalizujemy populację losowo z rozkładu normalnego
    * */
    @Override
    public void initPopulation(State<GenotypeEuclidean> state) {
        var population = new ArrayList<GenotypeEuclidean>();
        for (int i = 0; i < initialPopulationSize; i++) {
            population.add(new GenotypeEuclidean(random.doubles(dimension, min, max).toArray()));
        }
        state.setInitialPopulationSize(initialPopulationSize);
        state.setPopulation(population);
    }
}
