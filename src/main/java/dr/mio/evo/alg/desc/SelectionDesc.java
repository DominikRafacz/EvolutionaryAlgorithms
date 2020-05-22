package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

public interface SelectionDesc<T extends Genotype> {
    void performSelection(State<T> state);
}
