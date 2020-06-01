// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.lab;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
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
import lombok.Data;
import org.apache.commons.collections.ArrayStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LabThreeRunner {
    public static void main(String[] args) throws IOException, CsvValidationException {
        //ustawiamy seed
        GlobalRandom.setUp(1998);

        var data = TrainData.readCSV("src/main/resources/neat/easy-training.csv");


        var algorithm = EvolutionaryAlgorithmDesc.<GenotypeNEAT>builder()
                .spaceDesc(new SpaceDescNEAT(1))
                .populationInitDesc(new PopulationInitDescRandomNEAT(1000))
                .targetDesc(Targets.targetFunction(net -> net.calculateMSE(data.getX(), data.getY())))
                .matingDesc(new MatingDescNone<>())
                .crossingDesc(new CrossingDescNone<>())
                .mutationDesc(new MutationDescNone<>())
                .criterionDesc(new CriterionDescFixedIterations<>(1))
                .selectionDesc(new SelectionDescRank<>())
                .build()
                .getAlgorithm();

        algorithm.run();
    }

    @Data
    static class TrainData {
        private final List<List<Double>> X;
        private final List<Double> y;

        private TrainData(@NotNull List<List<Double>> X, @NotNull List<Double> y) {
            this.X = X;
            this.y = y;
        }

        @Contract("_ -> new")
        public static @NotNull TrainData readCSV(String file) throws IOException, CsvValidationException {
            var X = new ArrayList<List<Double>>();
            var y = new ArrayList<Double>();
            try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
                String[] values = csvReader.readNext();
                while ((values = csvReader.readNext()) != null) {
                    boolean r = Boolean.parseBoolean(values[values.length - 1]);
                    y.add(r ? 1.0 : 0.0);
                    X.add(Arrays.stream(Arrays.copyOfRange(values, 0, values.length - 1))
                            .map(Double::parseDouble)
                            .collect(Collectors.toList()));
                }
            }
            return new TrainData(X, y);
        }
    }
}
