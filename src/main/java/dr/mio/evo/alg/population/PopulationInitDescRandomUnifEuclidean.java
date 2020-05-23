// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.population;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.PopulationInitDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import dr.mio.evo.alg.space.SpaceDescEuclidean;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class PopulationInitDescRandomUnifEuclidean implements PopulationInitDesc<GenotypeEuclidean> {

    private final int initialPopulationSize;

    private final Random random = GlobalRandom.getRandom();

    public PopulationInitDescRandomUnifEuclidean(int initialPopulationSize) {
        this.initialPopulationSize = initialPopulationSize;
    }

    /*
    *  inicjalizujemy populację losowo z rozkładu normalnego
    * */
    @Override
    public void initPopulation(@NotNull State<GenotypeEuclidean> state) {
        var population = new ArrayList<GenotypeEuclidean>();
        var spaceDesc = (SpaceDescEuclidean) state.getSpaceDesc();
        var dimension = spaceDesc.getDimension();
        var min = spaceDesc.getMin();
        var max = spaceDesc.getMax();
        for (int i = 0; i < initialPopulationSize; i++) {
            population.add(new GenotypeEuclidean(random.doubles(dimension, min, max).toArray(), spaceDesc));
        }
        state.setInitialPopulationSize(initialPopulationSize);
        state.setPopulation(population);
    }
}
