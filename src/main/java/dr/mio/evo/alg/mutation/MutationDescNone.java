// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.mutation;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.MutationDesc;
import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

public class MutationDescNone<T extends Genotype> implements MutationDesc<T> {
    @Override
    public void performMutating(@NotNull State<T> state) {
    }
}
