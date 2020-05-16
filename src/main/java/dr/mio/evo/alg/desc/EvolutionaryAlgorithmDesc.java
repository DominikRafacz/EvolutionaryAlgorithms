package dr.mio.evo.alg.desc;

import dr.mio.evo.alg.EvolutionaryAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvolutionaryAlgorithmDesc {
    private PopulationDesc populationDesc;
    private TargetDesc targetDesc;
    private CrossingDesc crossingDesc;
    private MutationDesc mutationDesc;
    private CriterionDesc criterionDesc;

    EvolutionaryAlgorithm getAlgorithm() {
        return new EvolutionaryAlgorithm(
                populationDesc,
                targetDesc,
                crossingDesc,
                mutationDesc,
                criterionDesc);
    }
}
