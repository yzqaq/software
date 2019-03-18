package factory.graphFactory;

import java.io.File;
import java.io.IOException;

import graph.GraphPoet;

public class GraphPoetFactory extends GraphFactory {

    @Override
    public GraphPoet createGraph(String filePath) {
        GraphPoet poetGraph = new GraphPoet();
        String[] vertexType = poetGraph.getVertexType();
        String[] edgeType = poetGraph.getEdgeType();
        String[] graphName = { "" };
        File corpus = null;
        corpus = new File(filePath);
        factory.graphFactory.GraphFromFile gff = new factory.graphFactory.GraphFromFile();
        try {
            if (gff.graphFromFile(corpus, poetGraph, "GraphPoet", graphName, vertexType, edgeType)) {
                poetGraph.setGraphName(graphName[0]);
            } else {
                System.out.println("Fail to init and return an empty graph!");
                poetGraph = new GraphPoet();
            }
        } catch (IOException e) {
            System.out.println("Fail to find file and reurn an empty graph");
            poetGraph = new GraphPoet();
        }
        return poetGraph;

    }

}
