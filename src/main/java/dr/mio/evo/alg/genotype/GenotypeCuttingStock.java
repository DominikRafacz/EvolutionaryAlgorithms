// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.genotype;

import dr.mio.evo.alg.space.SpaceDescCuttingStock;
import dr.mio.evo.alg.util.cutting.Rectangle;
import dr.mio.evo.alg.util.cutting.RectangleTemplate;
import dr.mio.evo.random.GlobalRandom;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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
        if (
                isInCircle(x, y, radius) &&
                isInCircle(x + candidateRectangle.getTemplate().getWidth(), y, radius) &&
                isInCircle(x, y + candidateRectangle.getTemplate().getHeight(), radius) &&
                isInCircle(x + candidateRectangle.getTemplate().getWidth(), y + candidateRectangle.getTemplate().getHeight(), radius) &&
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

    @Override
    public String toString() {
        Map<RectangleTemplate, List<Rectangle>> groups = rectangles.stream()
                .collect(Collectors.groupingBy(Rectangle::getTemplate));

        return groups.keySet().stream()
                .map(key -> "" + key.getWidth() + "x" + key.getHeight() + ": " + groups.get(key).size())
                .collect(Collectors.joining("\n"));
    }

    private boolean isInCircle(double x, double y, int radius) {
        return x * x + y * y <= radius * radius;
    }
}
