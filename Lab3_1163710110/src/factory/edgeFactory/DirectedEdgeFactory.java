package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import vertex.Vertex;
import edge.DirectedEdge;

public class DirectedEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        DirectedEdge e = new DirectedEdge(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
