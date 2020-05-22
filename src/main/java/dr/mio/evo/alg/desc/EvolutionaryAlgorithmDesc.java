// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

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
    private MatingDesc<T> matingDesc;
    private MutationDesc<T> mutationDesc;
    private SelectionDesc<T> selectionDesc;
    private CriterionDesc<T> criterionDesc;

    public EvolutionaryAlgorithm<T> getAlgorithm() {
        return new EvolutionaryAlgorithm<>(
                populationDesc,
                targetDesc,
                matingDesc,
                crossingDesc,
                mutationDesc,
                selectionDesc,
                criterionDesc);
    }
}
