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
public class ConcreteVerticesGraph<L> implements Graph<L> {

    private final List<Vertex<L>> vertices = new ArrayList<>();

    // Abstraction function:
    // 表示一個有向图，通过储存边方式
    // Representation invariant:
    // Vertices不为null
    // Safety from rep exposure:
    // vertices为private final类型，且Vertex<L>是immutable的

    // constructor
    public ConcreteVerticesGraph() {
        checkRep();
    }

    // checkRep
    /**
     * 检查vertices!=null
     */
    private void checkRep() {
        assert vertices != null;
    }

    @Override
    public boolean add(L vertex) {
        // throw new RuntimeException("not implemented");
        int size = vertices.size();
        for (int i = 0; i < size; i++) {
            if (vertices.get(i).getVertex().equals(vertex)) {
                return false;
            }
        }
        Vertex<L> ver = new Vertex<L>(vertex);
        vertices.add(ver);

        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        // throw new RuntimeException("not implemented");
        int size = vertices.size();
        int preWeight = 0;
        for (int i = 0; i < size; i++) {
            if (vertices.get(i).getVertex().equals(source)) {
                Map<L, Integer> map = vertices.get(i).getTargetVertex();
                if (map.containsKey(target)) {
                    preWeight = map.get(target);

                    if (weight != 0) {
                        map.replace(target, weight);
                    } else {
                        map.remove(target);
                    }
                } else {
                    map.put(target, weight);
                }
                vertices.remove(i);
                Vertex<L> e = new Vertex<L>(source, map);
                vertices.add(e);
                break;
            }
        }

        return preWeight;

    }

    @Override
    public boolean remove(L vertex) {
        // throw new RuntimeException("not implemented");
        int size = vertices.size();
        for (int i = 0; i < size; i++) {
            if (vertices.get(i).getVertex().equals(vertex)) {
                vertices.remove(i);
                for (int j = 0; j < size - 1; j++) {
                    vertices.get(i).getTargetVertex().remove(vertex);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<L> vertices() {
        // throw new RuntimeException("not implemented");
        Set<L> set = new HashSet<L>();
        for (int i = 0; i < vertices.size(); i++) {
            set.add(vertices.get(i).getVertex());
        }
        return set;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        // throw new RuntimeException("not implemented");
        int size = vertices.size();
        Map<L, Integer> map = new HashMap<L, Integer>();
        for (int i = 0; i < size; i++) {
            if (vertices.get(i).getTargetVertex().containsKey(target)) {
                map.put(vertices.get(i).getVertex(), vertices.get(i).getTargetVertex().get(target));
            }
        }

        return map;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        // throw new RuntimeException("not implemented");
        int size = vertices.size();
        Map<L, Integer> map = new HashMap<L, Integer>();
        for (int i = 0; i < size; i++) {
            if (vertices.get(i).getVertex().equals(source)) {
                map = vertices.get(i).getTargetVertex();
                return map;
            }
        }
        return map;
    }

    /**
     * @return 该图的字符串形式(边与点的顺序随机）： "点：... 边： ..."
     */
    @Override
    public String toString() {
        String d = "点:";
        String s = "边:\n";
        for (Vertex<L> v : vertices) {
            d = d + v.getVertex().toString() + " ";
            s = s + v.toString();
        }
        return d + "\n" + s;
    }
}

/**
 * specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph. 实现vertex，包含以vertex为起点的所有边
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

    private final L vertex;
    private final Map<L, Integer> targetVertex;
    // Abstraction function:
    // 表示一个顶点
    // Representation invariant:
    // targetVertex!=null
    // Safety from rep exposure:
    // 所有变量均为private final 且getTargetVertex返回一个复制的新对象

    // constructor
    /**
     * 新建一个vertex,其中tasrgetVertex=new Map
     * 
     * @param vertex
     *            顶点值，赋值给成员变量。
     */
    public Vertex(L vertex) {
        this.vertex = vertex;
        this.targetVertex = new HashMap<L, Integer>();
        checkRep();
    }

    /**
     * 新建一个vertex，并传入参数
     * 
     * @param vertex
     *            顶点值，赋值给成员变量
     * @param targetVertex
     *            该点的所有target点及对应权重值，要求targetVertex!=null
     */
    public Vertex(L vertex, Map<L, Integer> targetVertex) {
        this.vertex = vertex;
        this.targetVertex = targetVertex;
        checkRep();
    }

    // checkRep
    /**
     * 
     * 检查targetVertex!=null
     */
    private void checkRep() {
        assert targetVertex != null;
    }
    // methods

    /**
     * 
     * @return vertex
     */
    public L getVertex() {
        return vertex;
    }

    /**
     * 
     * @return targetVertex
     */
    public Map<L, Integer> getTargetVertex() {
        Map<L, Integer> map = new HashMap<>();
        map.putAll(targetVertex);
        return map;
    }

    /**
     * @return 以该点为起点所有的边：起点->终点:权重
     */
    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<L, Integer> entry : targetVertex.entrySet()) {
            s = s + vertex.toString() + "->" + entry.getKey().toString() + ":" + entry.getValue() + '\n';
        }
        return s;
    }

}
