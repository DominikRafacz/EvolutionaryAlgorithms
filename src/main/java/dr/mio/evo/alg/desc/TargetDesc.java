// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

public interface TargetDesc<T extends Genotype> {
    void evalFitness(@NotNull State<T> state);
}
