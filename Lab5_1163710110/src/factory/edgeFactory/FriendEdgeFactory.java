package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import edge.FriendConnection;
import vertex.Vertex;

public class FriendEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        FriendConnection e = new FriendConnection(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
