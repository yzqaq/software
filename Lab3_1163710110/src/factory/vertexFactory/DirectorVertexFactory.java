package factory.vertexFactory;

import vertex.Director;
import vertex.Vertex;

public class DirectorVertexFactory extends VertexFactory {

    @Override
    public Vertex createVertex(String label, String[] args) {
        Director v = new Director(label);
        v.fillVertexInfo(args);
        return v;
    }

}
