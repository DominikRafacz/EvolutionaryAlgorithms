package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

import java.util.List;

public interface PopulationDesc<T extends Genotype> {
    void initPopulation(State<T> state);

    void reducePopulation(State<T> state);
}