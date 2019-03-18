package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import factory.graphFactory.MovieGraphFactory;
import graph.MovieGraph;
import vertex.Vertex;

public class MovieGraphApp {
    MovieGraph graph;

    public MovieGraphApp(String corpus) {
        graph = new MovieGraphFactory().createGraph(corpus);
    }

    public MovieGraphApp() {
        graph = new MovieGraph();
    }

    /**
     * 计算两节点间的最短路径
     * 
     * @param g
     * @param a
     * @param b
     * @return
     */
    public double getDistance(Vertex a, Vertex b) {
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
