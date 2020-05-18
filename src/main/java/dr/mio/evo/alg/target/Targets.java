package dr.mio.evo.alg.target;

import dr.mio.evo.alg.desc.TargetDesc;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import dr.mio.evo.alg.target.function.EuclideanFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class Targets {
    /*
    * Funkcja zwracająca opis celu dla danej funkcji w przestrzeni R^n
    * */
    @Contract(pure = true)
    public static @NotNull TargetDesc<GenotypeEuclidean> euclideanFunction(EuclideanFunction function) {
        return state -> state.setFitnessValue(state.getPopulation().stream().map(function::calculate).collect(Collectors.toList()));
    }
}
