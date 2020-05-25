package dr.mio.evo.alg.crossing;

import dr.mio.evo.alg.desc.CrossingDesc;
import dr.mio.evo.alg.genotype.GenotypeCuttingStock;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CrossingDescRandomCuttingSplit implements CrossingDesc<GenotypeCuttingStock> {
    private final Random random = GlobalRandom.getRandom();

    @Override
    public List<GenotypeCuttingStock> cross(@NotNull List<GenotypeCuttingStock> parents) {
        var radius = parents.get(0).getSpace().getCircle().getRadius();
        var split = (int) (random.nextDouble() * radius - (double) radius / 2);

        var offspring = List.of(
                new GenotypeCuttingStock(parents.get(0).getSpace()),
                new GenotypeCuttingStock(parents.get(0).getSpace()));
        if (random.nextBoolean()) {
            offspring.get(0).getRectangles().addAll(
                    parents.get(0)
                            .getRectangles()
                            .stream()
                            .filter(rect -> rect.getX() + rect.getTemplate().getWidth() <= split)
                            .collect(Collectors.toList())
            );
            offspring.get(0).getRectangles().addAll(
                    parents.get(1)
                            .getRectangles()
                            .stream()
                            .filter(rect -> rect.getX() > split)
                            .collect(Collectors.toList())
            );
            offspring.get(1).getRectangles().addAll(
                    parents.get(1)
                            .getRectangles()
                            .stream()
                            .filter(rect -> rect.getX() + rect.getTemplate().getWidth() <= split)
                            .collect(Collectors.toList())
            );
            offspring.get(1).getRectangles().addAll(
                    parents.get(0)
                            .getRectangles()
                            .stream()
                            .filter(rect -> rect.getX() > split)
                            .collect(Collectors.toList())
            );
        } else {
            offspring.get(0).getRectangles().addAll(
                    parents.get(0)
                            .getRectangles()
                            .stream()
                            .filter(rect -> rect.getY() + rect.getTemplate().getHeight() <= split)
                            .collect(Collectors.toList())
            );
            offspring.get(0).getRectangles().addAll(
                    parents.get(1)
                            .getRectangles()
                            .stream()
                            .filter(rect -> rect.getY() > split)
                            .collect(Collectors.toList())
            );
            offspring.get(1).getRectangles().addAll(
                    parents.get(1)
                            .getRectangles()
                            .stream()
                            .filter(rect -> rect.getY() + rect.getTemplate().getHeight() <= split)
                            .collect(Collectors.toList())
            );
            offspring.get(1).getRectangles().addAll(
                    parents.get(0)
                            .getRectangles()
                            .stream()
                            .filter(rect -> rect.getY() > split)
                            .collect(Collectors.toList())
            );
        }
        return offspring;
    }
}
