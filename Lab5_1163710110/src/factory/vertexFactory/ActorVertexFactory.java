package factory.vertexFactory;

import vertex.Vertex;
import vertex.Actor;

public class ActorVertexFactory extends VertexFactory {

    @Override
    public Vertex createVertex(String label, String[] args) {
        Actor v = new Actor(label);
        v.fillVertexInfo(args);
        return v;
    }

}
