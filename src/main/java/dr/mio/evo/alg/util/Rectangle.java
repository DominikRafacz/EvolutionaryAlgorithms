// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class Rectangle {
    private RectangleTemplate template;
    private int x;
    private int y;

    public boolean intersectsWith(@NotNull Rectangle other) {
        return !(other.x                              > this.x + this.template.getWidth() ||
                 other.x + other.template.getWidth()  < this.x                            ||
                 other.y + other.template.getHeight() > this.y                            ||
                 other.y                              < this.y + this.template.getHeight());
    }
}
