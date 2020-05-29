package dr.mio.evo.alg.genotype;

import dr.mio.evo.alg.space.SpaceDescNEAT;
import dr.mio.evo.alg.util.neat.Connection;
import dr.mio.evo.alg.util.neat.Node;
import dr.mio.evo.alg.util.neat.NodeTemplate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        var notCalcConnections = new ArrayList<Connection>();
        Collections.copy(notCalcConnections, connections);

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

        calc: while (true) {
            for (var connection: notCalcConnections) {
                if (calcNodes.contains(connection.getRequiredNode())) {
                    calcConnections.add(connection);
                    notCalcConnections.remove(connection);
                }
            }
            for (var node: notCalcNodes) {
                if (calcConnections.containsAll(node.getRequiredConnections())) {
                    node.calculateValue();
                    calcNodes.add(node);
                    notCalcNodes.remove(node);
                    if (node == outputNode) break calc;
                }
            }
        }

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
