package dr.mio.evo.alg.mutation;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.MutationDesc;
import dr.mio.evo.alg.genotype.Genotype;
import org.jetbrains.annotations.NotNull;

public class MutationDescNone<T extends Genotype> implements MutationDesc<T> {
    @Override
    public void performMutating(@NotNull State<T> state) {
    }
}
