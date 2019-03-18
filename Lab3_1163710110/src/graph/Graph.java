package graph;

/*
 *  yz
 */
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A mutable weighted directed graph with labeled vertices. Vertices have
 * distinct labels of an immutable type {@code L} when compared using the
 * {@link Object#equals(Object) equals} method. Edges are directed and have a
 * positive weight of type {@code int}.
 * 
 * <p>
 * PS2 instructions: this is a required ADT interface. You MUST NOT change the
 * specifications or add additional methods.
 * 
 * @param <L>
 *            type of vertex labels in this graph, must be mutable
 */
public interface Graph<L, E> {

    /**
     * Create 构造一张图的空实例
     * 
     * @param <L>
     *            节点类型，mutable <E> 边的类型，immutable
     * @return 一张新图的空实例
     */
    public static <L, E> Graph<L, E> empty() {
 
      return null;

    }

    /**
     * 向图中增加一个节点
     * 
     * @param v
     *            新顶点
     * @return true if this graph did not already include a vertex with the given
     *         label; otherwise false (and this graph is not modified)
     */
    public boolean addVertex(L v);

    /**
     * 从图中删除一个节点 v。如果 v 是某条边的两端之一,则该边被删除;如果某节点属于某条超边,若该
     * 节点删除后该条超边仍可合法存在,则该超边继续保留,否则就删除之。
     * 
     * @param v
     *            label of the vertex to remove
     * @return true if this graph included a vertex with the given label; otherwise
     *         false (and this graph is not modified)
     */
    public boolean removeVertex(L v);

    /**
     * 返回图的所有节点集合
     * 
     * @return 返回图的所有节点的集合
     */
    public Set<L> vertices();

    /**
     * Get the source vertices with edges to a target vertex and the weights of
     * those edges.
     * 
     * @param target
     *            a label
     * @return 返回的Map中Key为source节点, List<Double>为当前节点与该source节点之间的所有边的权值;如果
     *         与target相连的边包括无向边,则无向边的另一端节点也需包含在返 回值Map中;不需考虑超边。
     */
    public Map<L, List<Double>> sources(L target);

    /**
     * Get the target vertices with edges from a source vertex and the weights of
     * those edges.
     * 
     * @param source
     *            a label
     * @return 返回的Map中Key为target节点, List<Double>为当前节点与该target节点之间的所有边的权值;如果
     *         与source相连的边包括无向边,则无向边的另一端节点也需包含在返 回值Map中;不需考虑超边。
     */
    public Map<L, List<Double>> targets(L source);

    /**
     * 增加一条边(包括超边）
     * 
     * @param edge
     *            一条边
     * @return true 如果该edge不存在于图中，否则返回false
     */
    public boolean addEdge(E edge);
    
    /**
     * 删除一条边（包括超边）
     * @param edge
     *    待删除的目标边
     *    @return true如果edge存在于图中，否则返回false
     */
    public boolean removeEdge(E edge);
    /**
     * 返回边的集合（包括超边）
     * @return 返回边的集合（包括超边）
     */
    public Set<E> edges();
}
