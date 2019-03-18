package application;

import factory.graphFactory.SocialGraphFactory;
import graph.SocialNetwork;
import java.io.IOException;



public class SocialNetWorkApp {
  SocialNetwork graph;

    public SocialNetWorkApp(String corpus) {
        graph = new SocialGraphFactory().createGraph(corpus);
    }

    public SocialNetWorkApp() {
        graph = new SocialNetwork();
    }

    public static void main(String[] args) throws IOException {
        SocialNetWorkApp g = new SocialNetWorkApp("src/application/graphpoet.txt");
        SocialNetwork graphPoet = g.graph;
        System.out.println(graphPoet.getGraphName());
        System.out.println(graphPoet.getVertexType());
    }
}
