package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import edge.*;
import vertex.*;

public class ConcreteGraph implements Graph<Vertex, Edge> {

    private final Set<Vertex> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    // Abstraction function:
    // graph的一个实现
    // Representation invariant:
    //
    // Safety from rep exposure:
    // 所有变量是private，final的

    @Override
    public boolean addVertex(Vertex v) {
        if (vertices.contains(v))
            return false;
        vertices.add(v);
        return true;
    }

    @Override
    public boolean removeVertex(Vertex v) {
        if (vertices.remove(v)) {
            for (int i = 0; i < edges.size(); i++) {
                if (edges.get(i).containVertex(v)) {
                    if (edges.get(i).vertices().size() <= 2) {
                        edges.remove(i);
                    } else {
                        edges.get(i).setVertices().remove(v);
                    }

                }

            }

            return true;
        }
        return false;
    }

    @Override
    public Set<Vertex> vertices() {

        Set<Vertex> set = new HashSet<>();
        for (Vertex v : vertices) {
            set.add(v.newVertex());

        }
        return set;
    }

    @Override
    public Map<Vertex, List<Double>> sources(Vertex target) {
        // 通过Edge.targetVertices()和Edge.sourceVertices()的返回情况，来判断边的情况，有向-无向 超边-非超边 loop
        Map<Vertex, List<Double>> map = new HashMap<>();
        int size = edges.size();
        for (int i = 0; i < size; i++) {
            // 如果边有向
            if (edges.get(i).targetVertices() != null) {
                if (edges.get(i).targetVertices().contains(target)) {
                    for (Vertex v : edges.get(i).sourceVertices()) {
                        //
                        if (map.containsKey(v)) {
                            map.get(v).add(edges.get(i).getWeight());
                        } else {
                            List<Double> list = new ArrayList<>();
                            list.add(edges.get(i).getWeight());
                            map.put(v.newVertex(), list);

                        }
                    }
                }

            }
            // 无向且非loop
            else if (edges.get(i).vertices().size() == 2) {
                if (edges.get(i).containVertex(target)) {
                    for (Vertex v : edges.get(i).vertices()) {
                        if (v != target) {
                            if (map.containsKey(v)) {
                                map.get(v).add(edges.get(i).getWeight());
                            } else {
                                List<Double> list = new ArrayList<>();
                                list.add(edges.get(i).getWeight());
                                map.put(v.newVertex(), list);
                            }
                        }
                    }
                }
            }
            // 无向loop
            else if (edges.get(i).vertices().size() == 1) {
                if (edges.get(i).containVertex(target)) {
                    if (map.containsKey(target)) {
                        map.get(target).add(edges.get(i).getWeight());
                    } else {
                        List<Double> list = new ArrayList<>();
                        list.add(edges.get(i).getWeight());
                        map.put(target.newVertex(), list);
                    }
                }
            }
        }
        return map;
    }

    @Override
    public Map<Vertex, List<Double>> targets(Vertex source) {
        // 通过Edge.targetVertices()和Edge.sourceVertices()的返回情况，来判断边的情况，有向-无向 超边-非超边 loop
        Map<Vertex, List<Double>> map = new HashMap<>();
        int size = edges.size();
        for (int i = 0; i < size; i++) {
            // 如果边有向
            if (edges.get(i).sourceVertices() != null) {
                if (edges.get(i).sourceVertices().contains(source)) {
                    for (Vertex v : edges.get(i).targetVertices()) {
                        //
                        if (map.containsKey(v)) {
                            map.get(v).add(edges.get(i).getWeight());
                        } else {
                            List<Double> list = new ArrayList<>();
                            list.add(edges.get(i).getWeight());
                            map.put(v.newVertex(), list);

                        }
                    }
                }

            }
            // 无向且非loop
            else if (edges.get(i).vertices().size() == 2) {
                if (edges.get(i).containVertex(source)) {
                    for (Vertex v : edges.get(i).vertices()) {
                        if (v != source) {
                            if (map.containsKey(v)) {
                                map.get(v).add(edges.get(i).getWeight());
                            } else {
                                List<Double> list = new ArrayList<>();
                                list.add(edges.get(i).getWeight());
                                map.put(v.newVertex(), list);
                            }
                        }
                    }
                }
            }
            // 无向loop
            else if (edges.get(i).vertices().size() == 1) {
                if (edges.get(i).containVertex(source)) {
                    if (map.containsKey(source)) {
                        map.get(source).add(edges.get(i).getWeight());
                    } else {
                        List<Double> list = new ArrayList<>();
                        list.add(edges.get(i).getWeight());
                        map.put(source.newVertex(), list);
                    }
                }
            }
        }
        return map;
    }

    @Override
    public boolean addEdge(Edge edge) {
        if (edges.contains(edge)) {
            return false;
        }
      
        // 确保边有的点，点集里都有
       if( vertices.containsAll(edge.vertices())) {
           edges.add(edge);
           return true;
       }
        return false;
    }

    @Override
    public boolean removeEdge(Edge edge) {
        if (edges.remove(edge))
            return true;
        return false;
    }

    @Override
    public Set<Edge> edges() {

        Set<Edge> set = new HashSet<>();
        for (Edge e : edges) {
            set.add(e.newEdge());
        }

        return set;
    }

}
