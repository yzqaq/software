package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edge.Edge;
import factory.edgeFactory.EdgeFactory;
import factory.edgeFactory.NetworkEdgeFactory;
import factory.graphFactory.NetworkGraphFactory;
import factory.vertexFactory.ComputerVertexFactory;
import factory.vertexFactory.RouterVertexFactory;
import factory.vertexFactory.ServerVertexFactory;
import factory.vertexFactory.VertexFactory;
import graph.NetworkTopology;
import vertex.Vertex;
/**
 * asdasdad.
 */
public class NetworkTopologyApp {
   private NetworkTopology graph;

    /**
     * 从文件中生成一个图
     * 
     * @param corpus
     */
    public NetworkTopologyApp(String corpus) {
        graph = new NetworkGraphFactory().createGraph(corpus);
    }

    /**
     * 生成一个新图
     */
    public NetworkTopologyApp() {
        graph = new NetworkTopology();
    }

    public static void main(String[] args) {
        NetworkTopologyApp app = new NetworkTopologyApp();
        String flag = "";
        Scanner in = new Scanner(System.in);
        System.out.println("请输入：\n1.从文件读入图\n0.生成一个空图");
        while (true) {
            flag = in.next();
            in.nextLine();
            if (flag.equals("0")) {
                break;
            }
            if (flag.equals("1")) {
                System.out.println("请输入文件路径：");
                String corpus = in.nextLine();
                app = new NetworkTopologyApp(corpus);
                System.out.println(app.graph.getGraphName());
                break;
            }
            System.out.println("输入错误!\n请输入：\n1.从文件读入图\n0.生成一个空图");
        }
        label1: while (true) {
            System.out.println(
                    "请输入：\n1.点操作:11增加点，12删除点，13修改点，14计算点中心度\n2.边操作：21增加边，22删除边，23修改边，24修改超边\n3.计算：31计算图的中心度，32计算两点间距离\n0.退出");
            while (true) {
                flag = in.next();
                in.nextLine();
                String[] temp;
                int flag1 = 0;
                VertexFactory f;
                Vertex v;
                EdgeFactory ef;
                Edge e;
                String[] a;
                String name;
                List<Vertex> vertices = new ArrayList<>();
                switch (flag) {
                case "0":
                    break label1;
                case "11":
                    System.out.println("请输入‘VertexType label args'");
                    temp = in.nextLine().split(" ");

                    if (temp.length < 2) {
                        System.out.println("格式错误！");
                        continue label1;
                    }
                    a = Arrays.copyOfRange(temp, 2, temp.length);
                    switch (temp[0]) {
                    case "Computer":
                    case "computer":
                        f = new ComputerVertexFactory();
                        v = f.createVertex(temp[1], a);
                        app.graph.addVertex(v);

                        break;
                    case "Server":
                    case "server":
                        f = new ServerVertexFactory();
                        v = f.createVertex(temp[1], a);
                        app.graph.addVertex(v);
                        break;
                    case "Router":
                    case "router":
                        f = new RouterVertexFactory();
                        v = f.createVertex(temp[1], a);
                        app.graph.addVertex(v);
                        break;
                    default:
                        System.out.println("添加失败！");
                    }
                    continue label1;
                case "12":
                    System.out.println("请输入待删除点：");
                    name = in.next();
                    in.nextLine();
                    for (Vertex v1 : app.graph.vertices()) {
                        if (v1.getLabel().equals(name)) {
                            app.graph.removeVertex(v1);
                            continue label1;
                        }
                    }
                    System.out.println("未找到该点");
                    continue label1;
                case "13":
                    System.out.println("请输入待修改点：");
                    name = in.next();
                    in.nextLine();
                    for (Vertex v1 : app.graph.vertices()) {
                        if (v1.getLabel().equals(name)) {
                            System.out.println("请输入’flag label args' 其中flag为：0修改label 1修改args 2修改label和args");
                            temp = in.nextLine().split(" ");
                            if (temp.length < 2) {
                                System.out.println("格式错误！");
                                continue label1;
                            }
                            app.graph.removeVertex(v1);
                            if (temp[0].equals("0")) {
                                v1.setLabel(temp[1]);
                                continue label1;
                            } else if (temp[0].equals("1")) {
                                a = Arrays.copyOfRange(temp, 1, temp.length);
                                v1.fillVertexInfo(a);
                                continue label1;
                            } else if (temp[0].equals("2")) {
                                v1.setLabel(temp[1]);
                                a = Arrays.copyOfRange(temp, 2, temp.length);
                                v1.fillVertexInfo(a);
                                continue label1;
                            } else {
                                System.out.println("修改未完成！");
                                continue label1;
                            }
                        }
                    }
                    System.out.println("未找到该点");
                    continue label1;
                case "14":
                    System.out.println("请输入待计算点：");
                    name = in.next();
                    in.nextLine();
                    for (Vertex v1 : app.graph.vertices()) {
                        if (v1.getLabel().equals(name)) {
                            // TODO
                            continue label1;
                        }
                    }
                    System.out.println("未找到该点");
                    continue label1;
                case "21":
                    System.out.println("请输入EdgeType label weight vertices");
                    temp = in.nextLine().split(" ");
                    if(temp.length<3) {
                        System.out.println("格式错误");
                        continue label1;
                    }
                    if (temp[0].equals("NetworkConnection")) {
                        ef = new NetworkEdgeFactory();

                        for (int i = 3; i < temp.length; i++) {
                            flag1 = 0;
                            for (Vertex v1 : app.graph.vertices()) {
                                if (v1.getLabel().equals(temp[i])) {
                                    vertices.add(v1);
                                    flag1 = 1;
                                    break;
                                }
                            }
                            if (flag1 == 0) {
                                System.out.println(temp[i] + "未找到");
                                continue label1;
                            }
                        }

                        e = ef.createEdge(temp[1], vertices);
                        try {
                            e.setWeight(Double.parseDouble(temp[2]));
                        } catch (NumberFormatException e1) {
                            System.out.println("weight错误");
                            continue label1;
                        }
                        app.graph.addEdge(e);
                        continue label1;
                    }
                case"22":
                    System.out.println("请输入待删除边");
                    name=in.next();
                    in.nextLine();
                    for(Edge e1:app.graph.edges()) {
                        if(e1.getLabel().equals(name)) {
                        app.graph.removeEdge(e1);
                        continue label1;
                        }
                        
                    }
                    System.out.println("未找到该边");
                    continue label1;
                case"23":
                    System.out.println("请输入待修改边");
                    name=in.next();
                    in.nextLine();
                    System.out.println("");
                    for(Edge e1:app.graph.edges()) {
                        if(e1.getLabel().equals(name)) {
                           app.graph.removeEdge(e1);
                        app.graph.removeEdge(e1);
                        continue label1;
                        }
                        
                    }
                    System.out.println("未找到该边");
                    continue label1;
                default:
                    System.out.println(
                            "输入错误！\n请输入：\n1.点操作:11增加点，12删除点，13修改点，14计算点中心度\n2.边操作：21增加边，22删除边，23修改边，24修改超边\n3.计算：31计算图的中心度，32计算两点间距离\n0.退出");

                }

            }
        }
        in.close();
    }
   

}
