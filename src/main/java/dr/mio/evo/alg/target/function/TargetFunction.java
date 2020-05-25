// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.target.function;

import dr.mio.evo.alg.genotype.Genotype;

public interface TargetFunction<T extends Genotype> {
     double calculate(T genotype);
}
