// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.lab;

import dr.mio.evo.alg.crossing.CrossingDescStandardEuclideanFamilyModel;
import dr.mio.evo.alg.desc.EvolutionaryAlgorithmDesc;
import dr.mio.evo.alg.mating.MatingDescRandomPairs;
import dr.mio.evo.alg.genotype.GenotypeEuclidean;
import dr.mio.evo.alg.population.PopulationDescEuclideanSpace;
import dr.mio.evo.alg.selection.SelectionDescRank;
import dr.mio.evo.alg.target.Targets;
import dr.mio.evo.alg.criterion.CriterionDescFixedIterations;
import dr.mio.evo.alg.mutation.MutationDescOnePointGaussian;
import dr.mio.evo.random.GlobalRandom;

import java.util.stream.IntStream;

public class LabOneRunner {
    public static void main(String[] args) {
        // ustawiamy seed
        GlobalRandom.setUp(1998);

        // tworzymy algorytm ewolucyjny
        var algorithm = EvolutionaryAlgorithmDesc.<GenotypeEuclidean>builder()
                // każdy osobnik z naszej populacji będzie punktem w 3-wymiarowej przestrzeni o maks./min. wartości wymiaru +-10; populacja będzie liczyć 1000 osobnikóœ
                .populationDesc(new PopulationDescEuclideanSpace(3, 1000, -10, 10))
                // naszym celem jest minimalizacja zadanej funckji
                .targetDesc(Targets.targetFunction(x -> x.at(0) * x.at(0) + x.at(1) * x.at(1) + 2 * x.at(2) * x.at(2)))
                // krzyżywane pary będą dobierane losowo
                .matingDesc(new MatingDescRandomPairs<>())
                // dwoje rodzicow - dwoje dzieci
                .crossingDesc(new CrossingDescStandardEuclideanFamilyModel())
                // mutacja będzie zachodziła u ok 20% osobników i będzie dotyczyła jednej współrzędnej
                .mutationDesc(new MutationDescOnePointGaussian(0.2))
                // wykonanych zostanie 1000 iteracji
                .criterionDesc(new CriterionDescFixedIterations<>(1000))
                // selekcja będzie dokonywana metodą rankingową
                .selectionDesc(new SelectionDescRank<>())
                // budujemy wzorzec algorytmu
                .build()
                // uzyskujemy algorytm
                .getAlgorithm();
        //uruchamiamy algorytm - to kluczowa funkcja całego programu
        algorithm.run();
        //wypisujemy wyniki
        System.out.println(algorithm.getResults().toString());
        // bestGenotype=[0.056271070833511336, -0.2000371245861285, 1.6493333649530733], value=0.0019387734117407693

        // analogicznie jak wyżej, zmienia się funkcja celu
        algorithm = EvolutionaryAlgorithmDesc.<GenotypeEuclidean>builder()
                .populationDesc(new PopulationDescEuclideanSpace(5, 1000, -5.12, 5.12))
                .targetDesc    (Targets.targetFunction(x -> 50 + IntStream.range(0, 5).mapToDouble(i -> x.at(i) * x.at(i) - 10 * Math.cos(2 * Math.PI * x.at(i))).sum()))
                .matingDesc    (new MatingDescRandomPairs<>())
                .crossingDesc  (new CrossingDescStandardEuclideanFamilyModel())
                .mutationDesc  (new MutationDescOnePointGaussian(0.2))
                .criterionDesc (new CriterionDescFixedIterations<>(1000))
                .selectionDesc (new SelectionDescRank<>())
                .build()
                .getAlgorithm();
        algorithm.run();
        System.out.println(algorithm.getResults().toString());
        // bestGenotype=[0.035019690059640896, 1.0298196287914652, -1.0857039449239803, -0.0269322544928296, -0.02863954212613734], value=4.377650314246928

    }
}
