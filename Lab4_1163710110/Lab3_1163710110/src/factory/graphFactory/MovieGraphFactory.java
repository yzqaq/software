package factory.graphFactory;

import java.io.File;
import java.io.IOException;

import graph.MovieGraph;

public class MovieGraphFactory extends GraphFactory {

    @Override
    public MovieGraph createGraph(String filePath) {
        MovieGraph g = new MovieGraph();
        String[] vertexType = g.getVertexType();
        String[] edgeType = g.getEdgeType();
        String[] graphName = { "" };
        File corpus = null;
        corpus = new File(filePath);
        factory.graphFactory.GraphFromFile gff = new factory.graphFactory.GraphFromFile();
        try {
            if (gff.graphFromFile(corpus, g, "MovieGraph", graphName, vertexType, edgeType)) {
                g.setGraphName(graphName[0]);
            } else {
                System.out.println("Fail to init and return an empty graph!");
                g = new MovieGraph();
            }
        } catch (IOException e) {
            System.out.println("Fail to open file and return an empty graph!");
            g = new MovieGraph();
        }
        return g;
    }

}
