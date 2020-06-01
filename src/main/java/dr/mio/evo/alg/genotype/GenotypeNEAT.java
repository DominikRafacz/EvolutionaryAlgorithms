package dr.mio.evo.alg.genotype;

import dr.mio.evo.alg.space.SpaceDescNEAT;
import dr.mio.evo.alg.util.neat.Connection;
import dr.mio.evo.alg.util.neat.Node;
import dr.mio.evo.alg.util.neat.NodeTemplate;
import dr.mio.evo.random.GlobalRandom;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GenotypeNEAT implements Genotype {
    private final List<Node> nodes;
    private final List<Connection> connections;
    private final SpaceDescNEAT spaceDesc;
    private final Random random = GlobalRandom.getRandom();

    public  @NotNull GenotypeNEAT(@NotNull List<Node> nodes, @NotNull List<Connection> connections, SpaceDescNEAT spaceDescNEAT) {

        this.nodes = nodes;
        this.connections = connections;
        this.spaceDesc = spaceDescNEAT;
    }

    public double feedForward(@NotNull List<Double> input) {

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
                    node.setValue(input.get(node.getTemplate().getId() - 1));
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
            nodesToMove.forEach(Node::calculateValue);
            calcNodes.addAll(nodesToMove);
            notCalcNodes.removeAll(nodesToMove);
        } while (!nodesToMove.contains(outputNode));

        return outputNode.getValue();
    }

    public double calculateMSE(@NotNull List<List<Double>> X, @NotNull List<Double> y) {
        double sum = 0;
        for (int i = 0; i < X.size(); i++) {
            sum += Math.pow(feedForward(X.get(i)) - y.get(i), 2);
        }
        return sum / X.size();
    }

    public void mutateWeight() {
        var connection = selectRandomConnection();
        if (random.nextDouble() < 0.9)
            connection.setWeight(connection.getWeight() + random.nextGaussian());
        else
            connection.setWeight(random.nextGaussian());
    }

    public void addRandomNode() {
        while (true) {
            if (!tryAddingRandomNode()) break;
        }
    }

    public boolean tryAddingRandomNode() {
        Connection connection = selectRandomConnection();
        NodeTemplate nodeTemplate = spaceDesc.getNodeTemplateOnConnection(connection);
        if (nodes.stream().map(Node::getTemplate).noneMatch(template -> template == nodeTemplate)) {
            var node = new Node(nodeTemplate);
            var precedingConnection = new Connection(
                    spaceDesc.getConnectionTemplateBetweenTwoNodes(connection.getRequiredNode(), node),
                    connection.getRequiredNode());
            var succeedingConnection = new Connection(
                    spaceDesc.getConnectionTemplateBetweenTwoNodes(node, new Node(connection.getTemplate().getOutputNode())),
                    node);
            node.addRequiredConnection(precedingConnection);
            nodes.add(node);
            connection.setActive(false);
            connections.add(precedingConnection);
            connections.add(succeedingConnection);
            return true;
        }
        return false;
    }

    private Connection selectRandomConnection() {
        return connections.get(random.nextInt(connections.size()));
    }
}
