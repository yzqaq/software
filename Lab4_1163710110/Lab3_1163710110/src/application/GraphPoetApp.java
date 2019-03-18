package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import factory.graphFactory.GraphPoetFactory;
import graph.GraphPoet;
import vertex.Vertex;

public class GraphPoetApp {
    GraphPoet graph;

    public GraphPoetApp(String corpus) {
        graph = new GraphPoetFactory().createGraph(corpus);
    }

    public GraphPoetApp() {
        graph = new GraphPoet();
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

    public static void main(String[] args) throws IOException {
        GraphPoetApp gA = new GraphPoetApp("src/application/graphpoet.txt");
        GraphPoet graph = gA.graph;
        System.out.println(graph.getGraphName());
        Set<Vertex> set = graph.vertices();
        for (Vertex a : set) {
            for (Vertex b : set) {
                System.out.println(gA.getDistance(a, b));
            }
        }
        System.out.println("init:生成一个空图\ninitFrom corpus:从指定路径corpus读入一个图\ngraphName:输出图名\ndistace v1 v2:输出v1,v2的距离");
        while (true) {
            System.out.println(">>>");
            Scanner in = new Scanner(System.in);
            String order = in.next();
            if (order.equals("quit")) {
                break;
            }
            switch (order) {
            case "init":
                graph = new GraphPoet();
                break;

            case "initFrom":
                String corpus = in.next();
                gA = new GraphPoetApp(corpus);
                graph = gA.graph;
                break;
            case "graphName":
                System.out.println(graph.getGraphName());
                break;
            case "distance":
                String v1 = in.next();
                String v2 = in.next();
                Vertex vt1 = null;
                Vertex vt2 = null;
                int flag = 0;
                for (Vertex v : graph.vertices()) {
                    if (v.getLabel().equals(v1)) {
                        vt1 = v;
                        flag++;
                    }
                }
                for (Vertex v : graph.vertices()) {
                    if (v.getLabel().equals(v2)) {
                        vt2 = v;
                        flag = flag + 2;
                    }
                }
                if (flag == 3) {
                    System.out.println(gA.getDistance(vt1, vt2));
                } else {
                    System.out.println("can not find Vertex");
                }
            default:
                System.out.println("no such order");
                break;
            }

            in.close();
        }
    }
}
