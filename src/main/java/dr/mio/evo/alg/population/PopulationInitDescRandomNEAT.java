// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.population;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.PopulationInitDesc;
import dr.mio.evo.alg.genotype.GenotypeNEAT;
import dr.mio.evo.alg.space.SpaceDescNEAT;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PopulationInitDescRandomNEAT implements PopulationInitDesc<GenotypeNEAT> {
    private final int initialPopulationSize;

    public PopulationInitDescRandomNEAT(int initialPopulationSize) {
        this.initialPopulationSize = initialPopulationSize;
    }

    @Override
    public void initPopulation(@NotNull State<GenotypeNEAT> state) {
        var population = new ArrayList<GenotypeNEAT>(initialPopulationSize);
        var desc = (SpaceDescNEAT) state.getSpaceDesc();

        for (int i = 0; i < initialPopulationSize; i++) {
            population.add(desc.getSimpleNetwork());
        }

        state.setPopulation(population);
        state.setInitialPopulationSize(initialPopulationSize);
    }
}
