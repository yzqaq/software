/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();

    // Abstraction function:
    // 通过边方式实现Graph
    // Representation invariant:
    // Edge是不可变的（immutable)
    // Safety from rep exposure:
    // 所有变量是private，final的

    // constructor
    public ConcreteEdgesGraph() {
        checkRep();
    }

    // checkRep
    /**
     * 检查vertices和edges不为null;
     */
    private void checkRep() {
        assert vertices != null;
        assert edges != null;
    }

    @Override
    public boolean add(L vertex) {
        // throw new RuntimeException("not implemented");
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        // throw new RuntimeException("not implemented");
        int preWeight = 0;
        int size = edges.size();
        for (int i = 0; i < size; i++) {
            if (edges.get(i).getStartVertex().equals(source) && edges.get(i).getEndVertex().equals(target)) {
                preWeight = edges.get(i).getWeight();
                edges.remove(i);

                break;
            }


        }
        if (weight != 0) {
            Edge<L> ed = new Edge<L>(source, target, weight);
            edges.add(ed);

        }

        return preWeight;
    }

    @Override
    public boolean remove(L vertex) {
        // throw new RuntimeException("not implemented");
        if (vertices.contains(vertex)) {
            int size = edges.size();
            for (int i = 0; i < size; i++) {
                if (edges.get(i).getStartVertex().equals(vertex) || edges.get(i).getEndVertex().equals(vertex)) {
                    edges.remove(i);
                }
            }
            vertices.remove(vertex);
            return true;
        }
        return false;
    }

    @Override
    public Set<L> vertices() {
        // throw new RuntimeException("not implemented");
        return vertices;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        // throw new RuntimeException("not implemented");
        Map<L, Integer> map = new HashMap<L, Integer>();
        int size = edges.size();
        for (int i = 0; i < size; i++) {
            if (edges.get(i).getEndVertex().equals(target)) {
                map.put(edges.get(i).getStartVertex(), edges.get(i).getWeight());
            }
        }
        return map;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        // throw new RuntimeException("not implemented");
        Map<L, Integer> map = new HashMap<L, Integer>();
        int size = edges.size();
        for (int i = 0; i < size; i++) {
            if (edges.get(i).getStartVertex().equals(source)) {
                map.put(edges.get(i).getEndVertex(), edges.get(i).getWeight());
            }
        }
        return map;
    }

    // toString()
    /**
     * @return 该图的字符串行式 (点与边的顺序随机）: “点：... 边： ..."
     * 
     */
    @Override
    public String toString() {
        String s = "边:\n";
        String d = "点:";
        for (L l : vertices) {
            d = d + l.toString() + " ";
        }
        for (Edge<L> edge : edges) {
            s = s + edge.toString() + "\n";
        }
        return d + '\n' + s;
    }
}

/**
 * specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

    private final L startVertex;
    private final L endVertex;
    private final int weight;
    // Abstraction function:
    // 表示有向图的边，startVertex开始点，endVertex终点，weight权重
    // Representation invariant:
    // startVertex!=null endVertex!=null weight>0
    // Safety from rep exposure:
    // 所有变量都使用private final类型;因为 L 类型是immutable的，所以可以直接return。

    // constructor
    public Edge(L startVertex, L endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
        checkRep();
    }

    // checkRep
    /**
     * 检查startVertex和endVertex不为null，weight>0
     */
    private void checkRep() {
        assert weight > 0;
        assert startVertex != null;
        assert endVertex != null;
    }

    // methods
    /**
     * 
     * @return weight 两边权重
     */
    public int getWeight() {
        return weight;

    }

    /**
     * @return startVertex 起点
     */
    public L getStartVertex() {
        return startVertex;
    }

    /**
     * 
     * @return 终点
     */
    public L getEndVertex() {
        return endVertex;
    }

    /**
     * @return 该边的字符串形式 起点->终点：权重
     */
    @Override
    public String toString() {
        return startVertex.toString() + "->" + endVertex.toString() + ":" + weight;
    }

}
