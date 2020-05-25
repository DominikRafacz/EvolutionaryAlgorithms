package dr.mio.evo.alg.mutation;

import dr.mio.evo.alg.State;
import dr.mio.evo.alg.desc.MutationDesc;
import dr.mio.evo.alg.genotype.GenotypeCuttingStock;
import dr.mio.evo.alg.space.SpaceDescCuttingStock;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MutationDescRandomRectangleShiftingAddingOrRemoving implements MutationDesc<GenotypeCuttingStock> {
    private final Random random = GlobalRandom.getRandom();

    @Override
    public void performMutating(@NotNull State<GenotypeCuttingStock> state) {
        state.getPopulation().stream()
                .filter(genotype -> random.nextDouble() < 0.2)
                .forEach(genotype -> {
            var type = random.nextDouble();
            if (type < 0.05) genotype.removeRandomRectangle();
            else if (type < 0.2) genotype.tryAddingRandomRectangles((SpaceDescCuttingStock) state.getSpaceDesc(), 100);
            else genotype.tryShiftingRandomRectangles();
        });
    }
}
