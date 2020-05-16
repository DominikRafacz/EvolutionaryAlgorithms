package dr.mio.evo.alg;

import dr.mio.evo.alg.desc.*;

public class EvolutionaryAlgorithm {
    private final PopulationDesc populationDesc;
    private final TargetDesc targetDesc;
    private final CrossingDesc crossingDesc;
    private final MutationDesc mutationDesc;
    private final CriterionDesc criterionDesc;

    private State state;

    public EvolutionaryAlgorithm(PopulationDesc populationDesc,
                                 TargetDesc targetDesc,
                                 CrossingDesc crossingDesc,
                                 MutationDesc mutationDesc,
                                 CriterionDesc criterionDesc) {
        this.populationDesc = populationDesc;
        this.targetDesc = targetDesc;
        this.mutationDesc = mutationDesc;
        this.crossingDesc = crossingDesc;
        this.criterionDesc = criterionDesc;
        state = new State();
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
