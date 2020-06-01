package dr.mio.evo.alg.util.neat;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node {
    private final NodeTemplate template;
    private double value;
    private final List<Connection> requiredConnections = new ArrayList<>();

    public Node(NodeTemplate template) {
        this.template = template;
    }

    public void calculateValue() {
        value = requiredConnections.stream()
                .mapToDouble(connection -> sigmoid(connection.getRequiredNode().value * connection.getWeight())).sum();
    }

    public void addRequiredConnection(Connection connection) {
        requiredConnections.add(connection);
    }

    private static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}
