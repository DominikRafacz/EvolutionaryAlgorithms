package dr.mio.evo.alg.genotype;

import dr.mio.evo.alg.util.neat.Connection;
import dr.mio.evo.alg.util.neat.Node;
import dr.mio.evo.alg.util.neat.NodeTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenotypeNEAT implements Genotype {
    private final List<Node> nodes;
    private final List<Connection> connections;

    public  @NotNull GenotypeNEAT(@NotNull List<Node> nodes, @NotNull List<Connection> connections) {

        this.nodes = nodes;
        this.connections = connections;
    }

    public double feedForward(double[] input) {

        var calcNodes = new ArrayList<Node>();
        var notCalcNodes = new ArrayList<Node>();
        var calcConnections = new ArrayList<Connection>();
        var notCalcConnections = new ArrayList<>(connections);

        for (var node : nodes) {
            if (node.getTemplate().getType() == NodeTemplate.NodeType.INPUT) {
                var id = node.getTemplate().getId();
                if (id == 0)
                    node.setValue(1);
                else
                    node.setValue(input[node.getTemplate().getId() - 1]);
                calcNodes.add(node);
            } else {
                notCalcNodes.add(node);
            }
        }

        var outputNode = nodes.stream()
                .filter(node -> node.getTemplate().getType() == NodeTemplate.NodeType.OUTPUT)
                .findFirst()
                .orElseThrow();

        List<Connection> connectionsToMove;
        List<Node> nodesToMove;
        do {
            connectionsToMove = notCalcConnections.stream()
                    .filter(connection -> calcNodes.contains(connection.getRequiredNode()))
                    .collect(Collectors.toList());
            calcConnections.addAll(connectionsToMove);
            notCalcConnections.removeAll(connectionsToMove);

            nodesToMove = notCalcNodes.stream()
                    .filter(node -> calcConnections.containsAll(node.getRequiredConnections()))
                    .collect(Collectors.toList());
            calcNodes.addAll(nodesToMove);
            notCalcNodes.removeAll(nodesToMove);
        } while (!nodesToMove.contains(outputNode));

        return outputNode.getValue();
    }

    public double calculateMSE(double[] @NotNull [] X, double[] y) {
        double sum = 0;
        for (int i = 0; i < X.length; i++) {
            sum += Math.pow(feedForward(X[i]) - y[i], 2);
        }
        return sum / X.length;
    }
}
