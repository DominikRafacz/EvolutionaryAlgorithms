// Oświadczam, że niniejsza praca stanowiąca podstawę do uznania osiągnięcia efektów uczenia się z przedmiotu Metody Inteligencji Obliczeniowej w Analizie Danych została wykonana przeze mnie samodzielnie.
// Dominik Rafacz
// 291128

// Potwierdzam samodzielność powyższej pracy oraz niekorzystanie przeze mnie z niedozwolonych źródeł.
// Dominik Rafacz

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
            return connectionTemplate;
        }
    }

    public NodeTemplate getNodeTemplateOnConnection(@NotNull Connection connection) {
        var outputNode = connection.getTemplate().getOutputNode();
        var inputNode = connection.getTemplate().getInputNode();
        var potentialTemplate = nodeTemplates.stream()
                .filter(template -> template.getPrecedingNode() == inputNode && template.getSucceedingNode() == outputNode)
                .findFirst();
        if (potentialTemplate.isPresent()) return potentialTemplate.get();
        else {
            var nodeTemplate = new NodeTemplate(nodeTemplates.size(), inputNode, outputNode);
            nodeTemplates.add(nodeTemplate);
            return nodeTemplate;
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

        return new GenotypeNEAT(nodes, connections, this);
    }
}
