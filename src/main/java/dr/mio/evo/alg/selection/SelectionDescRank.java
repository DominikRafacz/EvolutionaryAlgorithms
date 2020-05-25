package dr.mio.evo.alg.selection;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.SelectionDesc;
import dr.mio.evo.alg.genotype.Genotype;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelectionDescRank<T extends Genotype> implements SelectionDesc<T> {

    private final Random random = GlobalRandom.getRandom();

    /*
     *  Funkcja do dokonywania selekcji metodą rankingową
     * */
    @Override
    public void performSelection(@NotNull State<T> state) {
        var population = state.getPopulation();
        var fitnessValues = state.getFitnessValue();
        // ustalamy kolejność według wartości fitness value
        var order = IntStream.range(0, population.size())
                .boxed()
                .sorted(Comparator.comparingDouble(fitnessValues::get))
                .collect(Collectors.toList());

        var newPopulation = new ArrayList<T>(state.getInitialPopulationSize());
        // wybieramy indeksy osobników, którzy przetrwają
        var newIndices = sample(getWeights(order, population.size()), state.getInitialPopulationSize());
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
