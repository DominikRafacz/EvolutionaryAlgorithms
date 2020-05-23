package dr.mio.evo.alg.crossing;

import dr.mio.evo.alg.desc.CrossingDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrossingDescStandardEuclideanFamilyModel implements CrossingDesc<GenotypeEuclidean> {
    @Override
    public List<GenotypeEuclidean> cross(@NotNull List<GenotypeEuclidean> parents) {
        var dimension = parents.get(0).getSpaceDescEuclidean().getDimension();
        return parents.get(0).crossWith(parents.get(1), new int[]{GlobalRandom.getRandom().nextInt(dimension)});
    }
}
