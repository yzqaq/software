package factory.vertexFactory;

import vertex.Vertex;
import vertex.Word;

public class WordVertexFactory extends VertexFactory{

    @Override
    public Vertex createVertex(String label, String[] args) {
        Word v=new Word(label);
        v.fillVertexInfo(args);
        return v;
    }

}
