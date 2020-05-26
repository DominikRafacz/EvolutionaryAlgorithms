// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.space;

import dr.mio.evo.alg.desc.SpaceDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import lombok.Data;

@Data
public class SpaceDescEuclidean implements SpaceDesc<GenotypeEuclidean> {
    private final int dimension;
    private final double min;
    private final double max;
}
