package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import edge.SameMovieHyperEdge;
import vertex.Vertex;

public class SameMovieHyperEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        SameMovieHyperEdge e = new SameMovieHyperEdge(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
