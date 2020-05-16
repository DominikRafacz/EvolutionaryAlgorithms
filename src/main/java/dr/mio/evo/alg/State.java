package dr.mio.evo.alg;

import dr.mio.evo.alg.genotype.Genotype;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class State<T extends Genotype> {
    private List<T> population;
    private List<Double> fitnessValue;
    private Results best;
}
