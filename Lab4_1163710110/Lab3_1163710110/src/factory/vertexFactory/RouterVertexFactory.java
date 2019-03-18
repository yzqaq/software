package factory.vertexFactory;

import vertex.Router;
import vertex.Vertex;

public class RouterVertexFactory extends VertexFactory {

    @Override
    public Vertex createVertex(String label, String[] args) {
        Router v = new Router(label);
        v.fillVertexInfo(args);
        return v;
    }

}
