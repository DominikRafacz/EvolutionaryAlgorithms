// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.mating;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.CrossingDesc;
import dr.mio.evo.alg.desc.MatingDesc;
import dr.mio.evo.alg.genotype.Genotype;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatingDescRandomPairs<T extends Genotype> implements MatingDesc<T> {
    /*
    * Dokonujemy krzyżowania losowych par
    * */
    @Override
    public void performCrossing(@NotNull State<T> state, @NotNull CrossingDesc<T> crossingDesc) {
        var population = state.getPopulation();
        int numPairs = population.size() / 2;
        // osobnika o indeksie i krzyżujemy z osobnikiem i + numPairs dla i w {0, ... numPairs - 1} i każda para ma dwójkę dzieci
        population.addAll(IntStream.range(0, numPairs)
                .boxed()
                .flatMap(i -> crossingDesc.cross(List.of(population.get(i), population.get(i + numPairs))).stream())
                .collect(Collectors.toList()));
        // przetasowujemy populację, by ususnąć informację, kto jest rodzicem, a kto potomkiem
        Collections.shuffle(population, GlobalRandom.getRandom());
    }
}
