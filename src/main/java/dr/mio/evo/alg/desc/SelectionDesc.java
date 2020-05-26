// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

public interface SelectionDesc<T extends Genotype> {
    void performSelection(@NotNull State<T> state);
}
