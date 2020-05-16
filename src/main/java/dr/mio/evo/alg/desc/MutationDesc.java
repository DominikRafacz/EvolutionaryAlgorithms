package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

import java.util.List;

public interface MutationDesc<T extends Genotype> {
    List<T> performMutating(State<T> population);
}
