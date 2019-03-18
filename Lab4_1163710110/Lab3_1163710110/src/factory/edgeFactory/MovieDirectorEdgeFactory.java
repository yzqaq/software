package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import edge.MovieDirectorRelation;
import vertex.Vertex;

public class MovieDirectorEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        MovieDirectorRelation e = new MovieDirectorRelation(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
