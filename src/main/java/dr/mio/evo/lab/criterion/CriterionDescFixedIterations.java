package dr.mio.evo.lab.criterion;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.CriterionDesc;
import dr.mio.evo.alg.genotype.Genotype;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import org.jetbrains.annotations.NotNull;

public class CriterionDescFixedIterations<T extends Genotype> implements CriterionDesc<T> {
    private final int iterations;

    public CriterionDescFixedIterations(int iterations) {
        this.iterations = iterations;
    }

    /*
    * Kryterium to uznajemy za spełnione, jeśli osiągnięta zostanie określona liczba iteracji
     */
    @Override
    public boolean isCriterionMet(@NotNull State<T> state) {
        return state.getIteration() >= iterations;
    }
}
