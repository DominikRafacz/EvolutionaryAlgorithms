package dr.mio.evo.alg.util.neat;

import dr.mio.evo.random.GlobalRandom;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Connection {
    private final ConnectionTemplate template;
    private Node requiredNode;
    private double weight = GlobalRandom.getRandom().nextGaussian();
    private boolean active = true;

    public Connection(ConnectionTemplate template, Node requiredNode) {
        this.template = template;
        this.requiredNode = requiredNode;
    }
}
