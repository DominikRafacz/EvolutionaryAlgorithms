// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.target;

import dr.mio.evo.alg.desc.TargetDesc;
import dr.mio.evo.alg.genotype.Genotype;
import dr.mio.evo.alg.target.function.TargetFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class Targets {
    /*
    * Funkcja zwracająca opis celu dla danej funkcji w przestrzeni genotypów T
    * */
    @Contract(pure = true)
    public static @NotNull <T extends Genotype> TargetDesc<T> targetFunction(TargetFunction<T> function) {
        return state -> state.setFitnessValue(state.getPopulation().stream().map(function::calculate).collect(Collectors.toList()));
    }
}
