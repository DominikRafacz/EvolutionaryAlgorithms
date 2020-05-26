// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg;

import dr.mio.evo.alg.desc.SpaceDesc;
import dr.mio.evo.alg.genotype.Genotype;
import lombok.Data;

import java.util.List;

@Data
public class State<T extends Genotype> {
    private final SpaceDesc<T> spaceDesc;
    private int initialPopulationSize;
    private List<T> population;
    private List<Double> fitnessValue;
    private Results<T> best = new Results<>();
    private int iteration = 0;

    public State(SpaceDesc<T> spaceDesc){
        this.spaceDesc = spaceDesc;
    }

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
