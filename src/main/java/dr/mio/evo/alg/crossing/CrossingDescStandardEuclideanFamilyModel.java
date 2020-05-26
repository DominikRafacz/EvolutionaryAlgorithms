// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.crossing;

import dr.mio.evo.alg.desc.CrossingDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrossingDescStandardEuclideanFamilyModel implements CrossingDesc<GenotypeEuclidean> {
    @Override
    public List<GenotypeEuclidean> cross(@NotNull List<GenotypeEuclidean> parents) {
        var dimension = parents.get(0).getSpaceDescEuclidean().getDimension();
        return parents.get(0).crossWith(parents.get(1), new int[]{GlobalRandom.getRandom().nextInt(dimension)});
    }
}
