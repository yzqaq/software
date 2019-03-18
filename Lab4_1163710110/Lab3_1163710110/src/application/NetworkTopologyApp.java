package application;

import factory.graphFactory.NetworkGraphFactory;
import graph.NetworkTopology;

public class NetworkTopologyApp {
    NetworkTopology graph;
/**
 * 从文件中生成一个图
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

    }

}
