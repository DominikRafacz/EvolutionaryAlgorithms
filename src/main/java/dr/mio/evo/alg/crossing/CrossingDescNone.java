// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.crossing;

import dr.mio.evo.alg.desc.CrossingDesc;
import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrossingDescNone<T extends Genotype> implements CrossingDesc<T> {
    @Override
    public List<T> cross(@NotNull List<T> parents) {
        return null;
    }
}
