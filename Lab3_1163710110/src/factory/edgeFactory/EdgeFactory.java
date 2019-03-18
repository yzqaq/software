package factory.edgeFactory;

import java.util.List;

import edge.Edge;
import vertex.Vertex;

public abstract class EdgeFactory {
    abstract public Edge createEdge(String label, List<Vertex> vertices);

}
