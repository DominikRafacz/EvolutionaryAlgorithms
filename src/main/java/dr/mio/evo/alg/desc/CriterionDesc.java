package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

public interface CriterionDesc<T extends Genotype> {
    boolean isCriterionMet(State<T> state);
}
