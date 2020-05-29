package dr.mio.evo.alg.util.neat;

import lombok.Data;

@Data
public class ConnectionTemplate {
    private final int id;
    private final NodeTemplate inputNode;
    private final NodeTemplate outputNode;
}
