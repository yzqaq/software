package factory.vertexFactory;

import vertex.Computer;
import vertex.Vertex;

public class ComputerVertexFactory extends VertexFactory {

    @Override
    public Vertex createVertex(String label, String[] args) {
        Computer v = new Computer(label);
        v.fillVertexInfo(args);
        return v;
    }

}
