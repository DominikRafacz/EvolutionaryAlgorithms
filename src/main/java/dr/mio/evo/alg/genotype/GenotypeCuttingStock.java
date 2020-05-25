package dr.mio.evo.alg.genotype;

import dr.mio.evo.alg.space.SpaceDescCuttingStock;
import dr.mio.evo.alg.util.Rectangle;
import dr.mio.evo.random.GlobalRandom;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class GenotypeCuttingStock implements Genotype {
    private final List<Rectangle> rectangles;
    private final Random random = GlobalRandom.getRandom();
    private final SpaceDescCuttingStock space;

    public GenotypeCuttingStock(@NotNull SpaceDescCuttingStock space) {
        this.space = space;
        rectangles = new ArrayList<>();
    }

    @Contract(pure = true)
    public static @NotNull GenotypeCuttingStock getRandomCut(@NotNull SpaceDescCuttingStock space, int trials) {
        var ret = new GenotypeCuttingStock(space);
        ret.tryAddingRandomRectangles(space, trials);
        return  ret;
    }

    public void tryAddingRandomRectangles(@NotNull SpaceDescCuttingStock space, int trials) {
        for (int i = 0; i < trials; i++) {
            tryAddingRandomRectangle(space);
        }
    }

    private void tryAddingRandomRectangle(@NotNull SpaceDescCuttingStock space) {
        int radius = space.getCircle().getRadius();
        var x = (int) (random.nextDouble() * radius * 2 - radius);
        var y = (int) (random.nextDouble() * radius * 2 - radius);

        var template = space.getTemplates().get(random.nextInt(space.getTemplates().size()));
        tryAddingRectangle(new Rectangle(template, x, y), radius);
    }

    private boolean tryAddingRectangle(@NotNull Rectangle candidateRectangle, int radius) {
        var x = candidateRectangle.getX();
        var y = candidateRectangle.getY();
        if (x * x + y * y > radius * radius &&
                rectangles.stream().noneMatch(rectangle -> rectangle.intersectsWith(candidateRectangle))) {
            rectangles.add(candidateRectangle);
            return true;
        }
        return false;
    }

    public void removeRandomRectangle() {
        if (rectangles.size() == 0) return;
        rectangles.remove(random.nextInt(rectangles.size()));
    }

    public void tryShiftingRandomRectangles() {
        if (rectangles.size() == 0) return;
        for (int i = 0; i < rectangles.size() * 5; i++) {
            if (tryShiftingRandomRectangle()) break;
        }
    }

    private boolean tryShiftingRandomRectangle() {
        var radius = space.getCircle().getRadius();
        var index = random.nextInt(rectangles.size());
        var rectangle = rectangles.get(index);
        var xShift = (int) Math.ceil(random.nextGaussian() * radius / 10);
        var yShift = (int) Math.ceil(random.nextGaussian() * radius / 10);
        rectangles.remove(index);
        if (tryAddingRectangle(new Rectangle(
                rectangle.getTemplate(),
                rectangle.getX() + xShift,
                rectangle.getY() + yShift), radius)) {
            return true;
        } else {
            rectangles.add(rectangle);
            return false;
        }
    }
}
