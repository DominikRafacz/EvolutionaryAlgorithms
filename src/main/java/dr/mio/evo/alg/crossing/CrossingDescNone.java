package dr.mio.evo.alg.crossing;

import dr.mio.evo.alg.desc.CrossingDesc;
import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrossingDescNone<T extends Genotype> implements CrossingDesc<T> {
    @Override
    public List<T> cross(@NotNull List<T> parents) {
        return null;
    }
}
