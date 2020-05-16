package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

import java.util.List;

public interface PopulationDesc {
    List<? extends Genotype> initPopulation();

    List<? extends Genotype> reducePopulation(State state);
}
