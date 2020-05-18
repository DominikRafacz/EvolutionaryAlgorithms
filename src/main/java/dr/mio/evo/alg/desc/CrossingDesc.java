package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

public interface CrossingDesc<T extends Genotype> {
    void performCrossing(State<T> state);
}