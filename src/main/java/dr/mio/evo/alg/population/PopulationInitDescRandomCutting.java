// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.population;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.PopulationInitDesc;
import dr.mio.evo.alg.genotype.GenotypeCuttingStock;
import dr.mio.evo.alg.space.SpaceDescCuttingStock;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PopulationInitDescRandomCutting implements PopulationInitDesc<GenotypeCuttingStock> {

    private final int initialPopulationSize;

    public PopulationInitDescRandomCutting(int initialPopulationSize) {
        this.initialPopulationSize = initialPopulationSize;
    }
    @Override
    public void initPopulation(@NotNull State<GenotypeCuttingStock> state) {
        var population = new ArrayList<GenotypeCuttingStock>(initialPopulationSize);
        var space = (SpaceDescCuttingStock) state.getSpaceDesc();
        for (int i = 0; i < initialPopulationSize; i++) {
            population.add(GenotypeCuttingStock.getRandomCut(space, 100));
        }
        state.setPopulation(population);
    }
}
