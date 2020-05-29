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
import dr.mio.evo.alg.mating.MatingDescRandomPairs;
import dr.mio.evo.alg.genotype.GenotypeCuttingStock;
import dr.mio.evo.alg.selection.SelectionDescRank;
import dr.mio.evo.alg.population.PopulationInitDescRandomCutting;
import dr.mio.evo.alg.target.Targets;
import dr.mio.evo.random.GlobalRandom;

public class LabTwoRunner {
    public static void main(String[] args) {
        //ustawiamy seed
        GlobalRandom.setUp(1998);

        // projektujemy algorytm
        // rozwiązaniem jest tutaj lista prostokątów ze współrzędnymi całkowitymi, zawierającymi się w zadanym kole
        var desc = EvolutionaryAlgorithmDesc.<GenotypeCuttingStock>builder()
                // populacja bedzie inicjalizowana losowo przez wstawianie w koła losowo prostokątów
                .populationInitDesc(new PopulationInitDescRandomCutting(1000))
                // funkcja celu (do minimalizacji) to minus suma wartości prostokątów
                .targetDesc(Targets.targetFunction(cut -> -cut.getRectangles().stream().mapToInt(rect -> rect.getTemplate().getValue()).sum()))
                // pary będą parowane losowo, każdy w jednej parze
                .matingDesc(new MatingDescRandomPairs<>())
                // krzyżowanie odbywa się przez wybór losowo miejsca przecięcia dwóch rodziców A i B horyzontalnie lub wertykalnie
                // dla przeciecia wertyklanego: dziecko C otrzymuje wszystkie prostokąty zawarte na lewo od cięcia z rodzica A
                // oraz wszystkie prostokądy zawarte na prawo od cięcia z rodzica B
                // a dziecko D otrzymuje wszystkie prostokądy zawarte na prawo od cięcia z rodzica A oraz wszystkie
                // prostokąty na lewo od cięcia z rodzica A
                .crossingDesc(new CrossingDescRandomCuttingSplit())
                // mutatcja danego prostokąta zachodzi z prawd. 0.2. Jeśli zachodzi, to
                // z prawd. 0.05 usuwany  jest jeden prostokąt z rozwiązania, z prawd.
                // z prawd. 0.15 próbujemy wstawić nowy prostokąt
                // z prawd. 0.8 przesuwamy jeden prostokąt o losową wartość z rozkładu normalnego
                .mutationDesc(new MutationDescRandomRectangleShiftingAddingOrRemoving())
                // ewolucja będzie trwać 10000 iteracji
                .criterionDesc(new CriterionDescFixedIterations<>(10000))
                // selekcja rankingowa
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
        //800: -9320.0
        //850: -107720.0
        //1000: -10300.0
        //1100: -10740.0
        //1200: -13580.0
    }
}
