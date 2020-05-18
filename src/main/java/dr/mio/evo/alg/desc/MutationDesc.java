package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

public interface MutationDesc<T extends Genotype> {
    void performMutating(State<T> state);
}
