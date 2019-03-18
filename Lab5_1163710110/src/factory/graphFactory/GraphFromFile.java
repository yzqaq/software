package factory.graphFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edge.*;
import vertex.*;
import graph.*;

/**
 * 
 * @author yz 实现从文件生成一个graph
 */
class GraphFromFile {
    private boolean contain(String[] source, String target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i].equals(target))
                return true;
        }
        return false;
    }

    /*
     * 
     * @param list
     * 
     * @param setV
     * 
     * @return true如果操作成功，否则false。
     */
    private boolean vertexFromFile(List<String> list, String[] vertexType, ConcreteGraph graph) {
        if (list.size() < 2) {
            return false;
        }
        String label = list.get(0);
        String typeFlag = list.get(1);
        Vertex V = null;
        if (contain(vertexType, typeFlag)) {
            // 删除list前3个元素以得到agrs
            list.remove(0);
            list.remove(0);
            String[] args = list.toArray(new String[0]);
            if (typeFlag.equals("Word")) {
                Word vertex = new Word(label);
                vertex.fillVertexInfo(args);
                V = vertex;
            } else if (typeFlag.equals("Actor")) {
                Actor vertex = new Actor(label);
                vertex.fillVertexInfo(args);
                V = vertex;
            } else if (typeFlag.equals("Computer")) {
                Computer vertex = new Computer(label);
                vertex.fillVertexInfo(args);
                V = vertex;
            } else if (typeFlag.equals("Director")) {
                Director vertex = new Director(label);
                vertex.fillVertexInfo(args);
                V = vertex;
            } else if (typeFlag.equals("Movie")) {
                Movie vertex = new Movie(label);
                vertex.fillVertexInfo(args);
                V = vertex;
            } else if (typeFlag.equals("Person")) {
                Person vertex = new Person(label);
                vertex.fillVertexInfo(args);
                V = vertex;
            } else if (typeFlag.equals("Router")) {
                Router vertex = new Router(label);
                vertex.fillVertexInfo(args);
                V = vertex;
            } else if (typeFlag.equals("Server")) {
                Server vertex = new Server(label);
                vertex.fillVertexInfo(args);
                V = vertex;
            } else {
                return false;
            }
            graph.addVertex(V);
            return true;
        }
        return false;
    }

    /*
     * 
     * @param list
     * 
     * @param setE
     * 
     * @return true如果操作无错，否则false
     */
    private boolean edgeFromFile(List<String> list, String[] edgeType, ConcreteGraph graph) {
        if (list.size() < 5) {
            return false;
        }
        String label = list.get(0);
        String typeFlag = list.get(1);
        String start = list.get(3);
        String end = list.get(4);
        Double weight = Double.parseDouble(list.get(2));
        Edge edge = null;
        if (contain(edgeType, typeFlag)) {
            List<Vertex> vertices = new ArrayList<>();
            Set<Vertex> set = graph.vertices();
            for (Vertex v : set) {
                if (v.getLabel().equals(start)) {
                    vertices.add(v);
                }
            }
            for (Vertex v : set) {
                if (v.getLabel().equals(end)) {
                    vertices.add(v);
                }
            }

            if (typeFlag.equals("WordNeighborhood")) {
                WordEdge e = new WordEdge(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("FriendTie")) {
                FriendConnection e = new FriendConnection(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("CommentTie")) {
                CommentConnenction e = new CommentConnenction(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("ForwardTie")) {
                ForwardConnection e = new ForwardConnection(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("NetworkConnection")) {
                NetworkConnection e = new NetworkConnection(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("MovieActorRelation")) {
                MovieActorRelation e = new MovieActorRelation(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("MovieDirectorRelation")) {
                MovieDirectorRelation e = new MovieDirectorRelation(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("SameMovieHyperEdge")) {
                SameMovieHyperEdge e = new SameMovieHyperEdge(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("DirectedEdge")) {
                DirectedEdge e = new DirectedEdge(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("UndirectedEdge")) {
                UndirectedEdge e = new UndirectedEdge(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else if (typeFlag.equals("HyperEdge")) {
                HyperEdge e = new HyperEdge(label, weight);
                e.addVertices(vertices);
                edge = e;
            } else {
                return false;
            }
            graph.addEdge(edge);
            return true;
        }
        return false;

    }

    /**
     * 从文件中以特定格式读取图并存入目标图中
     * 
     * @param corpus文件目录
     * @param graph
     *            目标图
     * @param graphType
     *            图的类型
     * @param graphName
     *            图名
     * @param vertexType
     *            点的类型集
     * @param edgeType
     *            边的类型集
     * @return true如果成功生成图，否则false
     * @throws IOException
     */
    public boolean graphFromFile(File corpus, ConcreteGraph graph, String graphType, String[] graphName,
            String[] vertexType, String[] edgeType) throws IOException {
        String regexString = "([\\w]+)\\s*=(.*)";
        String regexString1 = "[“]([^”\\t\\n\\f\\r]+)[”]";
        BufferedReader reader = null;
        String temp = "";
        String temp1 = "";
        reader = new BufferedReader(new FileReader(corpus));
        Pattern pattern = Pattern.compile(regexString);
        Pattern pattern1 = Pattern.compile(regexString1);
        Matcher matcher;
        String flag = "";
        List<String> list = new ArrayList<>();
        while ((temp = reader.readLine()) != null) {
            matcher = pattern.matcher(temp);
            if (matcher.find()) {
                flag = matcher.group(1);
                temp1 = matcher.group(2);
                matcher = pattern1.matcher(temp1);
                while (matcher.find()) {
                    list.add(matcher.group(1));
                }
            }
            if (list.size() < 1) {
                continue;
            }
            if (flag.equals("GraphType")) {
                if (!list.get(0).equals(graphType)) {
                    reader.close();
                    return false;
                }

            } else if (flag.equals("GraphName")) {
                graphName[0] = list.get(0);

            } else if (flag.equals("VertexType")) {
                for (int i = 0; i < list.size(); i++) {
                    if (!contain(vertexType, list.get(i))) {
                        reader.close();
                        return false;
                    }
                }
            } else if (flag.equals("Vertex")) {
                if (!this.vertexFromFile(list, vertexType, graph)) {
                    reader.close();
                    return false;
                }
            } else if (flag.equals("EdgeType")) {
                for (int i = 0; i < list.size(); i++) {
                    if (!contain(edgeType, list.get(i))) {
                        reader.close();
                        return false;
                    }
                }
            } else if (flag.equals("Edge")) {
                if (!this.edgeFromFile(list, edgeType, graph)) {
                    reader.close();
                    return false;
                }
            }
            list.clear();
        }
        reader.close();
        return true;
    }

}
