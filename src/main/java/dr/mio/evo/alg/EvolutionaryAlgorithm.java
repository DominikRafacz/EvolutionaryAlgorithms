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
        initPopulation();
        evalFitness();
        while (!isCriterionMet()) {
            performCrossing();
            performMutating();
            evalFitness();
            reducePopulation();
        }
    }

    private void initPopulation() {
        state.setPopulation(populationDesc.initPopulation());
    }

    private void evalFitness() {
        state.setFitnessValue(targetDesc.evaFitness(state));
    }

    private boolean isCriterionMet() {
        return criterionDesc.isCriterionMet(state);
    }

    private void performCrossing() {
        state.setPopulation(crossingDesc.performCrossing(state));
    }

    private void performMutating() {
        state.setPopulation(mutationDesc.performMutating(state));
    }

    private void reducePopulation() {
        state.setPopulation(populationDesc.reducePopulation(state));
    }

    private Results getResults() {
        return state.getBest();
    }

}
