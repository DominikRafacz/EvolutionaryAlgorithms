package dr.mio.evo.alg.util.neat;

import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class NodeTemplate {
    private final int id;
    private final NodeType type;
    private final List<NodeTemplate> precedingNodes = new ArrayList<>();
    private final List<NodeTemplate> succeedingNodes = new ArrayList<>();

    private NodeTemplate(int id, NodeType type) {
        this.id = id;
        this.type = type;
    }

    public NodeTemplate(int id, @NotNull  NodeTemplate preceding, @NotNull NodeTemplate succeeding) {
        this(id, NodeType.HIDDEN);
        this.precedingNodes.add(preceding);
        this.succeedingNodes.add(succeeding);
    }

    @Contract("_ -> new")
    public static @NotNull NodeTemplate input(int id) {
        return new NodeTemplate(id, NodeType.INPUT);
    }

    public static @NotNull NodeTemplate output(@NotNull List<NodeTemplate> inputs) {
        var ret = new NodeTemplate(inputs.size(), NodeType.OUTPUT);
        ret.precedingNodes.addAll(inputs);
        return ret;
    }

    public enum NodeType {
        INPUT,
        HIDDEN,
        OUTPUT
    }
}
