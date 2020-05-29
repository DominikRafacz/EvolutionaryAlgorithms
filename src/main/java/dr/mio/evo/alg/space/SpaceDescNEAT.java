package dr.mio.evo.alg.space;

import dr.mio.evo.alg.desc.SpaceDesc;
import dr.mio.evo.alg.genotype.Genotype;
import dr.mio.evo.alg.genotype.GenotypeNEAT;
import dr.mio.evo.alg.util.neat.Connection;
import dr.mio.evo.alg.util.neat.ConnectionTemplate;
import dr.mio.evo.alg.util.neat.Node;
import dr.mio.evo.alg.util.neat.NodeTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpaceDescNEAT implements SpaceDesc<GenotypeNEAT> {
    private final int inputSize;

    private final List<NodeTemplate> nodeTemplates;
    private final List<ConnectionTemplate> connectionTemplates;

    public SpaceDescNEAT(int inputSize) {
        this.inputSize = inputSize;

        this.nodeTemplates = IntStream.range(0, inputSize + 1)
                .mapToObj(NodeTemplate::input)
                .collect(Collectors.toList());
        nodeTemplates.add(NodeTemplate.output(nodeTemplates));

        this.connectionTemplates = IntStream.range(0, inputSize + 1)
                .mapToObj(i -> new ConnectionTemplate(i, nodeTemplates.get(i), nodeTemplates.get(inputSize + 1)))
                .collect(Collectors.toList());
    }

    public ConnectionTemplate getConnectionTemplateBetweenTwoNodes(@NotNull Node precedingNode, @NotNull Node succeedingNode) {
        var precedingNodeTemplate = precedingNode.getTemplate();
        var succeedingNodeTemplate = succeedingNode.getTemplate();
        var potentialTemplate = connectionTemplates.stream()
                .filter(template -> template.getInputNode() == precedingNodeTemplate && template.getOutputNode() == succeedingNodeTemplate)
                .findFirst();
        if (potentialTemplate.isPresent()) return potentialTemplate.get();
        else {
            var connectionTemplate = new ConnectionTemplate(connectionTemplates.size(), precedingNodeTemplate, succeedingNodeTemplate);
            connectionTemplates.add(connectionTemplate);
            precedingNodeTemplate.getSucceedingNodes().add(succeedingNodeTemplate);
            succeedingNodeTemplate.getPrecedingNodes().add(precedingNodeTemplate);
            return connectionTemplate;
        }
    }

    public GenotypeNEAT getSimpleNetwork() {
        var nodes = nodeTemplates.subList(0, inputSize + 2).stream()
                .map(Node::new)
                .collect(Collectors.toList());

        var connections = IntStream.range(0, inputSize + 1)
                .mapToObj(i -> new Connection(connectionTemplates.get(i), nodes.get(i)))
                .collect(Collectors.toList());

        nodes.get(inputSize + 1).getRequiredConnections().addAll(connections);

        return new GenotypeNEAT(nodes, connections);
    }
}
