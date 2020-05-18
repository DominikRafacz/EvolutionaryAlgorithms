package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

public interface TargetDesc<T extends Genotype> {
    void evalFitness(State<T> state);
}
