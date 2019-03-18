package factory.vertexFactory;

import vertex.Movie;
import vertex.Vertex;

public class MovieVertexFactory extends VertexFactory {

    @Override
    public Vertex createVertex(String label, String[] args) {
        Movie v = new Movie(label);
        v.fillVertexInfo(args);
        return v;
    }

}
