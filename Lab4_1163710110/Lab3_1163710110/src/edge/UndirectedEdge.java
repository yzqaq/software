package edge;

import java.util.List;
import java.util.Set;

import vertex.Vertex;

public class UndirectedEdge extends Edge{

    public UndirectedEdge(String label, double weight) {
        super(label, weight);
      
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) {
        if(vertices.size()==2) {
            setVertices().clear();
            setVertices().add(vertices.get(0));
            setVertices().add(vertices.get(1));
            return true;
        }
        if(vertices.size()==1) {
            setVertices().clear();
            setVertices().add(vertices.get(0));
            
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
        UndirectedEdge u=new UndirectedEdge(getLabel(), getWeight());
        u.setVertices().addAll(vertices());
        return u;
    }

}
