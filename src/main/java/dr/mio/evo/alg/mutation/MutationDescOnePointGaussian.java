// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.mutation;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.MutationDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MutationDescOnePointGaussian implements MutationDesc<GenotypeEuclidean> {
    private final double probability;
    private final double mean;
    private final double deviation;
    private final Random random = GlobalRandom.getRandom();

    public MutationDescOnePointGaussian(double probability, double mean, double deviation) {
        this.probability = probability;
        this.mean = mean;
        this.deviation = deviation;
    }

    public MutationDescOnePointGaussian(double probability) {
        this(probability, 0, 1);
    }

    /*
    * Losowo wybranym osobnikom dodajemy losowe mutacje z rozkładu normalnego
    * */
    @Override
    public void performMutating(@NotNull State<GenotypeEuclidean> state) {
        var dimensions = state.getPopulation().get(0).getSpaceDescEuclidean().getDimension();
        state.getPopulation()
                .forEach(genotype -> {
                    if (random.nextDouble() < probability)
                        genotype.mutateAt(random.nextInt(dimensions), random.nextGaussian() * deviation + mean);
                });
    }
}
