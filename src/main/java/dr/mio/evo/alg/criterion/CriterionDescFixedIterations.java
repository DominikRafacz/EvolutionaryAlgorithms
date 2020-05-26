// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.criterion;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.CriterionDesc;
import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

public class CriterionDescFixedIterations<T extends Genotype> implements CriterionDesc<T> {
    private final int iterations;

    public CriterionDescFixedIterations(int iterations) {
        this.iterations = iterations;
    }

    /*
    * Kryterium to uznajemy za spełnione, jeśli osiągnięta zostanie określona liczba iteracji
     */
    @Override
    public boolean isCriterionMet(@NotNull State<T> state) {
        return state.getIteration() >= iterations;
    }
}
