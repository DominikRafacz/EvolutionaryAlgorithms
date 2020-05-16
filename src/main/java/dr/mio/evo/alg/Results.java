package dr.mio.evo.alg;

import dr.mio.evo.alg.genotype.Genotype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Results {
    private Genotype best;
    private Double value;
}
