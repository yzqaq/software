package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import edge.ForwardConnection;
import vertex.Vertex;

public class FordwardEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        ForwardConnection e = new ForwardConnection(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
