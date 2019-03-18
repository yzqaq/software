package edge;

import java.util.List;
import java.util.Set;

import vertex.Vertex;

public class HyperEdge extends Edge {

    public HyperEdge(String label, double weight) {
        super(label, weight);
       
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) {
        int size = vertices.size();
        if (size >= 2) {
            setVertices().clear();
            for (int i = 0; i < size; i++) {
                setVertices().add(vertices.get(i));
            }
        
            return true;
        }
        return false;
    }

    @Override
    public Set<Vertex> sourceVertices() {
        
        return null;
    }

    @Override
    public Set<Vertex> targetVertices() {
     
        return null;
    }

    @Override
    public Edge newEdge() {
      HyperEdge h=new HyperEdge(getLabel(), getWeight());
      h.setVertices().addAll(vertices());
        return h;
    }

}
