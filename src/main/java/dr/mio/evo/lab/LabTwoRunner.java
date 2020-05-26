// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

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

        //wyniki dla poszczególnych zadań:
        //800: -19840.0
        //850: -153280.0
        //1000: -16960.0
        //1100: -14720.0
        //1200: -16340.0
    }
}
