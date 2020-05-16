package dr.mio.evo.alg;

import dr.mio.evo.alg.genotype.Genotype;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class State {
    private List<? extends Genotype> population;
    private List<Double> fitnessValue;
    private Results best;
}
