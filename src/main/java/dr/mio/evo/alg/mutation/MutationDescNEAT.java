// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.mutation;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.MutationDesc;
import dr.mio.evo.alg.genotype.GenotypeNEAT;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MutationDescNEAT implements MutationDesc<GenotypeNEAT> {
    private final Random random  = GlobalRandom.getRandom();

    @Override
    public void performMutating(@NotNull State<GenotypeNEAT> state) {
        double val;
        for (GenotypeNEAT individual : state.getPopulation()) {
            val = random.nextDouble();
            if (val < 0.5) {
                individual.mutateWeight();
            }
            val = random.nextDouble();
            if (val < 0.1) {
                individual.addRandomNode();
            }
        }

    }
}
