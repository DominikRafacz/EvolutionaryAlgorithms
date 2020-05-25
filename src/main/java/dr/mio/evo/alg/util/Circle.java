package dr.mio.evo.alg.util;

import lombok.Data;

@Data
public class Circle {
    private int radius;

    public Circle(int radius) {

        this.radius = radius;
    }
}
