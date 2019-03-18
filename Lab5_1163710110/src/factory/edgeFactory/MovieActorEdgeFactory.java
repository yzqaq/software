package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import edge.MovieActorRelation;
import vertex.Vertex;

public class MovieActorEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        MovieActorRelation e = new MovieActorRelation(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
