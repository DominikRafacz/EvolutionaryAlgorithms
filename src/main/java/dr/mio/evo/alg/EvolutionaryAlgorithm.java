// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg;

import dr.mio.evo.alg.desc.*;
import dr.mio.evo.alg.genotype.Genotype;

public class EvolutionaryAlgorithm<T extends Genotype> {
    private final PopulationDesc<T> populationDesc;
    private final TargetDesc<T> targetDesc;
    private final CrossingDesc<T> crossingDesc;
    private final MutationDesc<T> mutationDesc;
    private final CriterionDesc<T> criterionDesc;

    private final State<T> state;

    public EvolutionaryAlgorithm(PopulationDesc<T> populationDesc,
                                 TargetDesc<T> targetDesc,
                                 CrossingDesc<T> crossingDesc,
                                 MutationDesc<T> mutationDesc,
                                 CriterionDesc<T> criterionDesc) {
        this.populationDesc = populationDesc;
        this.targetDesc = targetDesc;
        this.mutationDesc = mutationDesc;
        this.crossingDesc = crossingDesc;
        this.criterionDesc = criterionDesc;
        state = new State<>();
    }

    public void run() {
        // nazwy są, mam nadzieję, dość samoopisujące - wszystkie dokonują zmian w obiekcie State
        // przechowującym stan populacji w danym momencie i delegują wykonanie metod do implementacji
        // interfejsów opisujących poszczególne kroki
        initPopulation();
        evalFitness();
        while (!isCriterionMet()) {
            incrementCounter();
            performCrossing();
            performMutating();
            evalFitness();
            reducePopulation();
        }
    }

    private void incrementCounter() {
        state.incrementCounter();
    }

    private void initPopulation() {
        populationDesc.initPopulation(state);
    }

    private void evalFitness() {
        targetDesc.evalFitness(state);
        state.selectBest();
    }

    private boolean isCriterionMet() {
        return criterionDesc.isCriterionMet(state);
    }

    private void performCrossing() {
        crossingDesc.performCrossing(state);
    }

    private void performMutating() {
        mutationDesc.performMutating(state);
    }

    private void reducePopulation() {
        populationDesc.reducePopulation(state);
    }

    public Results<T> getResults() {
        return state.getBest();
    }

}
