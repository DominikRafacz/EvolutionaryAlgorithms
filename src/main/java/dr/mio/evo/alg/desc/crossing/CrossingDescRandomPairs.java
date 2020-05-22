package dr.mio.evo.alg.desc.crossing;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.CrossingDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CrossingDescRandomPairs implements CrossingDesc<GenotypeEuclidean> {
    /*
    * Dokonujemy krzyżowania losowych par
    * */
    @Override
    public void performCrossing(@NotNull State<GenotypeEuclidean> state) {
        var population = state.getPopulation();
        var dimensions = population.get(0).getDimensions();
        int numPairs = population.size() / 2;
        // osobnika o indeksie i krzyżujemy z osobnikiem i + numPairs dla i w {0, ... numPairs - 1} i każda para ma dwójkę dzieci
        population.addAll(IntStream.range(0, numPairs)
                .boxed()
                .flatMap((Integer i) -> population.get(i)
                        .crossWith(population.get(i + numPairs),
                                new int[]{GlobalRandom.getRandom().nextInt(dimensions)})
                        .stream())
                .collect(Collectors.toList()));
        // przetasowujemy populację, by ususnąć informację, kto jest rodzicem, a kto potomkiem
        Collections.shuffle(population);
    }
}
