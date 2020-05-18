package dr.mio.evo.alg;

import dr.mio.evo.alg.genotype.Genotype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Results<T extends Genotype> {
    private T bestGenotype;
    private Double value = Double.MAX_VALUE;
}
