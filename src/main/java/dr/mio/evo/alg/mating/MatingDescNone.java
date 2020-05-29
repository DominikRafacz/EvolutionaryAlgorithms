package dr.mio.evo.alg.mating;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.CrossingDesc;
import dr.mio.evo.alg.desc.MatingDesc;
import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

public class MatingDescNone<T extends Genotype> implements MatingDesc<T> {
    @Override
    public void performCrossing(@NotNull State<T> state, @NotNull CrossingDesc<T> crossingDesc) {
    }
}
