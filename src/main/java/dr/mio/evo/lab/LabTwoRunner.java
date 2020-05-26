// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.lab;

import dr.mio.evo.alg.EvolutionaryAlgorithm;
import dr.mio.evo.alg.criterion.CriterionDescFixedIterations;
import dr.mio.evo.alg.crossing.CrossingDescRandomCuttingSplit;
import dr.mio.evo.alg.desc.EvolutionaryAlgorithmDesc;
import dr.mio.evo.alg.mutation.MutationDescRandomRectangleShiftingAddingOrRemoving;
import dr.mio.evo.alg.space.SpaceDescCuttingStock;
import dr.mio.evo.alg.util.RectangleTemplate;
import dr.mio.evo.alg.mating.MatingDescRandomPairs;
import dr.mio.evo.alg.genotype.GenotypeCuttingStock;
import dr.mio.evo.alg.selection.SelectionDescRank;
import dr.mio.evo.alg.population.PopulationInitDescRandomCutting;
import dr.mio.evo.alg.target.Targets;
import dr.mio.evo.random.GlobalRandom;

import javax.swing.*;
import java.util.List;

public class LabTwoRunner {
    public static void main(String[] args) {
        //ustawiamy seed
        GlobalRandom.setUp(1998);

        var desc = EvolutionaryAlgorithmDesc.<GenotypeCuttingStock>builder()
                .populationInitDesc(new PopulationInitDescRandomCutting(1000))
                .targetDesc(Targets.targetFunction(cut -> -cut.getRectangles().stream().mapToInt(rect -> rect.getTemplate().getValue()).sum()))
                .matingDesc(new MatingDescRandomPairs<>())
                .crossingDesc(new CrossingDescRandomCuttingSplit())
                .mutationDesc(new MutationDescRandomRectangleShiftingAddingOrRemoving())
                .criterionDesc(new CriterionDescFixedIterations<>(10000))
                .selectionDesc(new SelectionDescRank<>());

        EvolutionaryAlgorithm<GenotypeCuttingStock> algorithm;

        for (var size : new int[]{800, 850, 1000, 1100, 1200}) {
            algorithm = desc
                    .spaceDesc(SpaceDescCuttingStock.fromCSVFile("src/main/resources/csp/r" + size + ".csv", size))
                    .build()
                    .getAlgorithm();

            algorithm.run();
            var results = algorithm.getResults();
            System.out.println("radius: " + size + "\nvalue: " + results.getValue() + "\nrectangles:\n" + results.getBestGenotype() + "\n");
        }
    }
}
