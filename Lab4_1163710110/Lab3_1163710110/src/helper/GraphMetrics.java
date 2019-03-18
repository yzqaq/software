package helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graph.ConcreteGraph;
import graph.Graph;
import vertex.Vertex;

public class GraphMetrics {

    /**
     * 计算图 g 中节点 v 的 degree centrality
     * 
     * @param <L>
     * @param g
     * @param v
     * @return
     * @return
     */
    static <L, E> double degreeCentrality(Graph<L, E> g, L v) {
        int num = g.vertices().size() - 1;
        int num0 = g.sources(v).size();

        return num0 * 1.00 / num;
    }

    /**
     * 计算图 g 的总体 degree centrality ((i-v)(max-C(vi)))/v*v-3v+2
     * 
     * @param g
     * @return
     */
    static <L, E> double degreeCentrality(Graph<L, E> g) {
        int size = g.vertices().size();
        double maxNum = 0;
        double temp = 0;

        for (L v : g.vertices()) {
            if (maxNum < degreeCentrality(g, v)) {
                maxNum = degreeCentrality(g, v);
            }

        }
        for (L v : g.vertices()) {
            temp = temp + (maxNum - degreeCentrality(g, v));
        }
        temp = temp / (size * size - 3 * size + 2);
        return temp;
    }

    /**
     * 计算图 g 中节点 v 的 closeness centrality
     * 
     * @param g
     * @param v
     * @return
     */

    static <L, E> double closenessCentrality(Graph<L, E> g, L v) {
        int size = g.vertices().size();
        double temp = 0;
        for (List<Double> l : g.sources(v).values()) {
            for (int i = 0; i < l.size(); i++) {
                temp += l.get(i);
            }
        }
        if(temp==0) {
            System.out.println("Error:return 0;");
            return 0;
        }
        return (size-1)/temp;
    }

    /**
     * 计算图 g 中节点 v 的 betweenness centrality 
     * 
     * @param g
     * @param v
     * @return
     */
    static <L, E> double betweennessCentrality(Graph<L, E> g, L v) {
       
        return 0;
    }

    /**
     * 计算图 g 中节点 v 的 indegree centrality
     * 
     * @param g
     * @param v
     * @return
     */
    static <L, E> double inDegreeCentrality(Graph<L, E> g, L v) {
        int num = g.vertices().size() - 1;
        int num0 = g.sources(v).size();

        return num0 * 1.00 / num;
    }

    /**
     * 计算图 g 中节点 v 的 outdegree centrality
     * 
     * @param g
     * @param v
     * @return
     */
    static <L, E> double outDegreeCentrality(Graph<L, E> g, L v) {
        int num = g.vertices().size() - 1;
        int num0 = g.targets(v).size();

        return num0 * 1.00 / num;
    }
    
    private double getDistance(Vertex a, Vertex b,ConcreteGraph graph) {
        if (a.equals(b)) {
            return 0;
        } else {
            double d = 1;
            Set<Vertex> set = new HashSet<Vertex>();// 存已遍历过的person
            List<Vertex> list1 = new ArrayList<>();// 存下一次的person
            list1.add(a);
            set.add(a);
            // 按层遍历无向图
            while (true) {
                List<Vertex> list2 = list1;// 将list1赋给list2
                list1 = new ArrayList<Vertex>();// 初始化list1
                // 退出while循环条件
                if (list2.isEmpty())
                    break;
                for (int i = 0; i < list2.size(); i++) {
                    Vertex temp = list2.get(i);
                    for (Vertex key : graph.targets(temp).keySet()) {
                        if (!set.contains(key)) {

                            if (key.equals(b)) {
                                return d;
                            } else {
                                set.add(key);
                                list1.add(key);
                            }
                        }
                    }
                }
                d++;
            }
            return -1;

        }
    }


}
