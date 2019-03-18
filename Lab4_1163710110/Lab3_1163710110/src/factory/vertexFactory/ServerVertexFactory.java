package factory.vertexFactory;

import vertex.Server;
import vertex.Vertex;

public class ServerVertexFactory extends VertexFactory{


    @Override
    public Vertex createVertex(String label, String[] args) {
        Server v=new Server(label);
        v.fillVertexInfo(args);
        return v;
    }

}
