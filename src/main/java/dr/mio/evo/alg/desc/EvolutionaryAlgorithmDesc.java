package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.EvolutionaryAlgorithm;
import dr.mio.evo.alg.genotype.Genotype;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvolutionaryAlgorithmDesc<T extends Genotype> {
    private PopulationDesc<T> populationDesc;
    private TargetDesc<T> targetDesc;
    private CrossingDesc<T> crossingDesc;
    private MutationDesc<T> mutationDesc;
    private CriterionDesc<T> criterionDesc;

    public EvolutionaryAlgorithm<T> getAlgorithm() {
        return new EvolutionaryAlgorithm<>(
                populationDesc,
                targetDesc,
                crossingDesc,
                mutationDesc,
                criterionDesc);
    }
}
