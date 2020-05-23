// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

public interface CriterionDesc<T extends Genotype> {
    boolean isCriterionMet(@NotNull State<T> state);
}
