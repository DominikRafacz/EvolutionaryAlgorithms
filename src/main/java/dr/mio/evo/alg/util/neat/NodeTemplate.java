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
    private final NodeTemplate precedingNode;
    private final NodeTemplate succeedingNode;

    private NodeTemplate(int id, NodeType type, NodeTemplate preceding, NodeTemplate succeeding) {
        this.id = id;
        this.type = type;
        this.precedingNode = preceding;
        this.succeedingNode = succeeding;
    }

    public NodeTemplate(int id, @NotNull NodeTemplate preceding, @NotNull NodeTemplate succeeding) {
        this(id, NodeType.HIDDEN, preceding, succeeding);
    }

    @Contract("_ -> new")
    public static @NotNull NodeTemplate input(int id) {
        return new NodeTemplate(id, NodeType.INPUT, null, null);
    }

    public static @NotNull NodeTemplate output(@NotNull List<NodeTemplate> inputs) {
        return new NodeTemplate(inputs.size(), NodeType.OUTPUT, null, null);
    }

    public enum NodeType {
        INPUT,
        HIDDEN,
        OUTPUT
    }
}
