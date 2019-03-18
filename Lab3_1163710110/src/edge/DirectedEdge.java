package edge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vertex.Vertex;

public class DirectedEdge extends Edge {
    private Vertex target;
    private Vertex source;

    /*
     * AF:
     * 
     * 有向图的实现 
     * 
     * RI:
     * 
     * Safety from rep exposure:
     * 
     */
    /**
     * 构造函数
     * 
     * @param label
     * @param weight
     */
    public DirectedEdge(String label, double weight) {
        super(label, weight);
        target = null;
        source = null;

    }

    @Override
    public boolean addVertices(List<Vertex> vertices) {
        if (vertices.size() == 2) {
            setVertices().clear();
            source=vertices.get(0);
            setVertices().add(source);
            target = vertices.get(1);
            setVertices().add(target);

            return true;
        }
        if(vertices.size()==1) {
            setVertices().clear();
            setVertices().add(vertices.get(0));
            target=vertices.get(0);
            source=target;
            return true;
        }

        return false;
    }

    @Override
    public Set<Vertex> sourceVertices() {
   
        Set<Vertex> set = new HashSet<>();
        set.add(source.newVertex());
        return set;
    }

    @Override
    public Set<Vertex> targetVertices() {
        Set<Vertex> set = new HashSet<>();
        set.add(target.newVertex());
        return set;
    }
/**
 * 
 * @return null若target为null，否则返回target
 */
    public Vertex getTarget() {
        if (target == null)
            return null;
        return target;
    }
/**
 * 
 * @return null若source为null，否则返回source
 */
    public Vertex getSource() {
        if (source == null)
            return null;
        return source;
    }

@Override
public Edge newEdge() {
    DirectedEdge d=new DirectedEdge(getLabel(), getWeight());
    d.setVertices().addAll(vertices());
    d.setSource(getSource());
    d.setTarget(getTarget());
    return d;
}

private void setTarget(Vertex target) {
    this.target = target;
}

private void setSource(Vertex source) {
    this.source = source;
}
    

}
