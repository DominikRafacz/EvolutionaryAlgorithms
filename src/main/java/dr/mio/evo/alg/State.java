// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg;

import dr.mio.evo.alg.genotype.Genotype;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class State<T extends Genotype> {
    private int initialPopulationSize;
    private List<T> population;
    private List<Double> fitnessValue;
    private Results<T> best = new Results<>();
    private int iteration = 0;

    public void incrementCounter() {
        iteration++;
    }

    public void selectBest() {
        for (int i = 0; i < population.size(); i++) {
            if (fitnessValue.get(i) < best.getValue()) {
                best.setValue(fitnessValue.get(i));
                best.setBestGenotype(population.get(i));
            }
        }
    }
}
