package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.genotype.Genotype;

import java.util.List;

public interface CrossingDesc<T extends Genotype> {
    List<T> performCrossing(State<T> state);
}
