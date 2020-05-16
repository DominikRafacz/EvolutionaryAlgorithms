package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

import java.util.List;

public interface TargetDesc<T extends Genotype> {
    List<Double> evaFitness(State<T> population);
}
