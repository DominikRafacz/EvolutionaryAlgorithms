package dr.mio.evo.alg.population;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.PopulationDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PopulationDescEuclideanSpace implements PopulationDesc<GenotypeEuclidean> {

    private final int dimension;
    private final int initialPopulationSize;
    private final double min;
    private final double max;

    private final Random random = new Random();

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
        var random = new Random();
        var population = new ArrayList<GenotypeEuclidean>();
        for (int i = 0; i < initialPopulationSize; i++) {
            population.add(new GenotypeEuclidean(random.doubles(dimension, min, max).toArray()));
        }
        state.setPopulation(population);
    }

    /*
    *  Funkcja do dokonywania selekcji metodą rankingową
    * */
    @Override
    public void reducePopulation(@NotNull State<GenotypeEuclidean> state) {
        var population = state.getPopulation();
        var fitnessValues = state.getFitnessValue();
        // ustalamy kolejność według wartości fitness value
        var order = IntStream.range(0, population.size())
                .boxed()
                .sorted(Comparator.comparingDouble(fitnessValues::get))
                .collect(Collectors.toList());

        var newPopulation = new ArrayList<GenotypeEuclidean>(initialPopulationSize);
        // wybieramy indeksy osobników, którzy przetrwają
        var newIndices = sample(getWeights(order, population.size()), initialPopulationSize);
        newIndices.forEach(index -> newPopulation.add(population.get(index)));
        // ustawiamy te osobniki jako nową populację
        state.setPopulation(newPopulation);
    }

    private @NotNull List<Integer> getWeights(@NotNull List<Integer> order, int populationSize) {
        var ret = new ArrayList<>(Collections.nCopies(populationSize, 0));
        for (int i = 0; i < populationSize; i++) {
            ret.set(order.get(i), (int) Math.floor(100 / (1 + Math.exp(((double) 8 * i) / populationSize - 4))));
        }
        return ret;
    }

    private @NotNull List<Integer> sample(@NotNull List<Integer> weights, int sampleSize) {
        var ret = new ArrayList<Integer>(sampleSize);
        long totalNum = weights.stream().mapToInt(value -> value).sum();
        for (int i = 0; i < sampleSize; i++) {
            var item = (long) Math.floor(random.nextFloat() * totalNum);
            int j = 0;
            long itemsPast = weights.get(0);
            while (itemsPast < item) {
                j++;
                itemsPast += weights.get(j);
            }
            ret.add(j);
            totalNum -= weights.get(j);
            weights.remove(j);
        }
        return ret;
    }
}
