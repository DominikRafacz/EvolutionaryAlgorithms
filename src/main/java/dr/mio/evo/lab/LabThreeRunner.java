// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.lab;

import dr.mio.evo.alg.criterion.CriterionDescFixedIterations;
import dr.mio.evo.alg.crossing.CrossingDescNone;
import dr.mio.evo.alg.desc.EvolutionaryAlgorithmDesc;
import dr.mio.evo.alg.genotype.GenotypeNEAT;
import dr.mio.evo.alg.mating.MatingDescNone;
import dr.mio.evo.alg.mutation.MutationDescNone;
import dr.mio.evo.alg.population.PopulationInitDescRandomNEAT;
import dr.mio.evo.alg.selection.SelectionDescRank;
import dr.mio.evo.alg.space.SpaceDescNEAT;
import dr.mio.evo.alg.target.Targets;
import dr.mio.evo.random.GlobalRandom;

public class LabThreeRunner {
    public static void main(String[] args) {
        //ustawiamy seed
        GlobalRandom.setUp(1998);

        double[][] X = {{1}};
        double[] y = {1};

        var algorithm = EvolutionaryAlgorithmDesc.<GenotypeNEAT>builder()
                .spaceDesc(new SpaceDescNEAT(1))
                .populationInitDesc(new PopulationInitDescRandomNEAT(1000))
                .targetDesc(Targets.targetFunction(net -> net.calculateMSE(X, y)))
                .matingDesc(new MatingDescNone<>())
                .crossingDesc(new CrossingDescNone<>())
                .mutationDesc(new MutationDescNone<>())
                .criterionDesc(new CriterionDescFixedIterations<>(1))
                .selectionDesc(new SelectionDescRank<>())
                .build()
                .getAlgorithm();

        algorithm.run();
    }
}
