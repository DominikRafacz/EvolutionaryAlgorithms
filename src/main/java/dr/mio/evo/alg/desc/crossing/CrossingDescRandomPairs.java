package dr.mio.evo.alg.desc.crossing;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.CrossingDesc;
import dr.mio.evo.alg.genotype.Genotype;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CrossingDescRandomPairs implements CrossingDesc<GenotypeEuclidean> {
    private final Random random = new Random();
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
                                new int[]{random.nextInt(dimensions)})
                        .stream())
                .collect(Collectors.toList()));
        // przetasowujemy populację, by ususnąć informację, kto jest rodzicem, a kto potomkiem
        Collections.shuffle(population);
    }
}
