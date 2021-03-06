// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg;

import dr.mio.evo.alg.desc.*;
import dr.mio.evo.alg.genotype.Genotype;

public class EvolutionaryAlgorithm<T extends Genotype> {
    private final PopulationInitDesc<T> populationInitDesc;
    private final TargetDesc<T> targetDesc;
    private final CrossingDesc<T> crossingDesc;
    private final MatingDesc<T> matingDesc;
    private final MutationDesc<T> mutationDesc;
    private final SelectionDesc<T> selectionDesc;
    private final CriterionDesc<T> criterionDesc;

    private final State<T> state;

    public EvolutionaryAlgorithm(SpaceDesc<T> spaceDesc,
                                 PopulationInitDesc<T> populationInitDesc,
                                 TargetDesc<T> targetDesc,
                                 MatingDesc<T> matingDesc,
                                 CrossingDesc<T> crossingDesc,
                                 MutationDesc<T> mutationDesc,
                                 SelectionDesc<T> selectionDesc,
                                 CriterionDesc<T> criterionDesc) {
        this.populationInitDesc = populationInitDesc;
        this.targetDesc = targetDesc;
        this.mutationDesc = mutationDesc;
        this.matingDesc = matingDesc;
        this.crossingDesc = crossingDesc;
        this.selectionDesc = selectionDesc;
        this.criterionDesc = criterionDesc;
        state = new State<>(spaceDesc);
    }

    public void run(boolean verbose) {
        // nazwy są, mam nadzieję, dość samoopisujące - wszystkie dokonują zmian w obiekcie State
        // przechowującym stan populacji w danym momencie i delegują wykonanie metod do implementacji
        // interfejsów opisujących poszczególne kroki
        initPopulation();
        evalFitness();
        if (verbose) System.out.println("Best value at init: " + getResults().getValue());
        while (!isCriterionMet()) {
            incrementCounter();
            performCrossing();
            performMutating();
            evalFitness();
            if (verbose) System.out.println("Best value after " + state.getIteration() + " iteration: " + getResults().getValue());
            performSelection();
        }
    }

    public void run() {
        run(false);
    }

    private void incrementCounter() {
        state.incrementCounter();
    }

    private void initPopulation() {
        populationInitDesc.initPopulation(state);
    }

    private void evalFitness() {
        targetDesc.evalFitness(state);
        state.selectBest();
    }

    private boolean isCriterionMet() {
        return criterionDesc.isCriterionMet(state);
    }

    private void performCrossing() {
        matingDesc.performCrossing(state, crossingDesc);
    }

    private void performMutating() {
        mutationDesc.performMutating(state);
    }

    private void performSelection() {
        selectionDesc.performSelection(state);
    }

    public Results<T> getResults() {
        return state.getBest();
    }

}
