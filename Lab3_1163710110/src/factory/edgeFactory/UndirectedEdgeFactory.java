package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import edge.UndirectedEdge;
import vertex.Vertex;

public class UndirectedEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        UndirectedEdge e = new UndirectedEdge(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
