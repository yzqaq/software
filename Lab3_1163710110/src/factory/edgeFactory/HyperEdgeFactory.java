package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import edge.HyperEdge;
import vertex.Vertex;

public class HyperEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        HyperEdge e = new HyperEdge(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
