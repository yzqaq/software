package factory.edgeFactory;

import java.util.List;

import edge.CommentConnenction;
import edge.Edge;
import vertex.Vertex;

public class CommentEdgeFactory extends EdgeFactory {

    @Override
    public Edge createEdge(String label, List<Vertex> vertices) {
        CommentConnenction e = new CommentConnenction(label, -1);
        e.addVertices(vertices);
        return e;
    }

}
