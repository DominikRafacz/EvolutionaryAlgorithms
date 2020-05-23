package dr.mio.evo.alg.space;

import dr.mio.evo.alg.desc.SpaceDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import lombok.Data;

@Data
public class SpaceDescEuclidean implements SpaceDesc<GenotypeEuclidean> {
    private final int dimension;
    private final double min;
    private final double max;
}
