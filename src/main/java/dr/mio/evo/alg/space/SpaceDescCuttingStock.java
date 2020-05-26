// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.space;

import dr.mio.evo.alg.desc.SpaceDesc;
import dr.mio.evo.alg.genotype.GenotypeCuttingStock;
import dr.mio.evo.alg.util.Circle;
import dr.mio.evo.alg.util.RectangleTemplate;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class SpaceDescCuttingStock implements SpaceDesc<GenotypeCuttingStock> {
    private final Circle circle;
    private final List<RectangleTemplate> templates;

    public SpaceDescCuttingStock(int circleRadius, List<RectangleTemplate> templates) {
        this.templates = templates;
        this.circle = new Circle(circleRadius);
    }

    public static @Nullable SpaceDescCuttingStock fromCSVFile(String path, int radius) {
        var rectangles = new ArrayList<RectangleTemplate>();
        try (var csvReader = new BufferedReader(new FileReader(path))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                rectangles.add(new RectangleTemplate(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            }
            return new SpaceDescCuttingStock(radius, rectangles);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
