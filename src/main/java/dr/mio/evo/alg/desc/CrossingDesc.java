package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CrossingDesc<T extends Genotype> {
    List<T> cross(@NotNull List<T> parents);
}
