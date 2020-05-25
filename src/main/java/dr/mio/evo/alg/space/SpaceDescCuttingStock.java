// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.space;

import dr.mio.evo.alg.desc.SpaceDesc;
import dr.mio.evo.alg.genotype.GenotypeCuttingStock;
import dr.mio.evo.alg.util.Circle;
import dr.mio.evo.alg.util.RectangleTemplate;
import lombok.Data;

import java.util.List;

@Data
public class SpaceDescCuttingStock implements SpaceDesc<GenotypeCuttingStock> {
    private final Circle circle;
    private final List<RectangleTemplate> templates;

    public SpaceDescCuttingStock(int circleRadius, List<RectangleTemplate> templates) {
        this.templates = templates;
        this.circle = new Circle(circleRadius);
    }
}
