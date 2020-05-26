// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

package dr.mio.evo.alg;

import dr.mio.evo.alg.genotype.Genotype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Results<T extends Genotype> {
    private T bestGenotype;
    private Double value = Double.MAX_VALUE;
}
