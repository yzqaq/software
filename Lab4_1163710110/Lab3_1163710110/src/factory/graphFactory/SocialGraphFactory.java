package factory.graphFactory;

import java.io.File;
import java.io.IOException;

import graph.SocialNetwork;

public class SocialGraphFactory extends GraphFactory {

    @Override
    public SocialNetwork createGraph(String filePath) {
        SocialNetwork g = new SocialNetwork();
        String[] vertexType = g.getVertexType();
        String[] edgeType = g.getEdgeType();
        String[] graphName = { "" };
        File corpus = null;
        corpus = new File(filePath);
        factory.graphFactory.GraphFromFile gff = new factory.graphFactory.GraphFromFile();
        try {
            if (gff.graphFromFile(corpus, g, "SocialNetwork", graphName, vertexType, edgeType)) {
                g.setGraphName(graphName[0]);
            } else {
                System.out.println("Fail to init and return an empty graph!");
                g = new SocialNetwork();
            }
        } catch (IOException e) {
            System.out.println("Fail to open file and return an empty graph!");
            g = new SocialNetwork();
        }
        return g;
    }

}
