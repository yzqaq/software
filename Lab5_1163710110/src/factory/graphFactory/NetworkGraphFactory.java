package factory.graphFactory;

import java.io.File;
import java.io.IOException;

import graph.NetworkTopology;

public class NetworkGraphFactory extends GraphFactory {

    @Override
    public NetworkTopology createGraph(String filePath) {
        NetworkTopology g = new NetworkTopology();
        String[] vertexType = g.getVertexType();
        String[] edgeType = g.getEdgeType();
        String[] graphName = { "" };
        File corpus = null;
        corpus = new File(filePath);
        factory.graphFactory.GraphFromFile gff = new factory.graphFactory.GraphFromFile();

        try {
            if (gff.graphFromFile(corpus, g, "NetworkTopology", graphName, vertexType, edgeType)) {
                g.setGraphName(graphName[0]);
            } else {
                System.out.println("Fail to init and return an empty graph!");
                g = new NetworkTopology();
            }
        } catch (IOException e) {
            System.out.println("Fail to open file and return an empty graph!");
            g = new NetworkTopology();
        }
        return g;
    }

}
