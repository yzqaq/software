package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import edge.NetworkConnection;
import vertex.Vertex;

public class NetworkEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        NetworkConnection e = new NetworkConnection(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
