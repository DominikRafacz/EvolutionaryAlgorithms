// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.genotype;

import dr.mio.evo.alg.space.SpaceDescEuclidean;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class GenotypeEuclidean implements Genotype {
    private final double[] genome;
    @Getter
    private final SpaceDescEuclidean spaceDescEuclidean;

    @Contract(pure = true)
    public GenotypeEuclidean(double @NotNull [] genome, SpaceDescEuclidean spaceDescEuclidean) {
        this.genome = genome;
        this.spaceDescEuclidean = spaceDescEuclidean;
    }

    public double at(int position) {
        return genome[position];
    }

    public void mutateAt(int position, double mutation) {
        genome[position] = genome[position] + mutation;
    }

    public List<GenotypeEuclidean> crossWith(@NotNull GenotypeEuclidean other, int @NotNull [] positions) {
        var offspringOne = this.genome.clone();
        var offspringTwo = other.genome.clone();
        for (var position : positions) {
            offspringOne[position] = other.genome[position];
            offspringTwo[position] = this.genome[position];
        }
        return List.of(new GenotypeEuclidean(offspringOne, spaceDescEuclidean), new GenotypeEuclidean(offspringTwo, spaceDescEuclidean));
    }

    @Override
    public String toString() {
        return Arrays.toString(genome);
    }
}
