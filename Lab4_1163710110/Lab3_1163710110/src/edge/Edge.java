package edge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vertex.Vertex;

public abstract class Edge {
    // 该边中包含的所有节点,其内部元素的类型应该为 Vertex
    private Set<Vertex> vertices;
    // 外部可观察的标签信息,字符串
    private String label;
    // 权值,若为带权边,则为非负数;若为无权边则该属性为-1
    private double weight;
   // private Vertex source=null;

    /**
     * 构造函数
     */
    public Edge(String label, double weight) {
        this.label = label;
        this.weight = weight;
        this.vertices = new HashSet<Vertex>();
    }

    /**
     * 如果是超边,vertices.size()>=2,该函数添加 vertices 中的所有 节 点 到 该 超 边 ; 如 果 是 有 向 边 ,
     * vertices.size()=2 , 该 操 作 将 vertices 中的第一个元素作为 source,将第二个元素作为 target;如果 是 无
     * 向 边 , vertices.size()=2 , 无 需 考 虑 次 序 ; 如 果 是 loop , vertices.size()=1。
     */
    abstract public boolean addVertices(List<Vertex> vertices);

    /**
     * 该边中是否包含指定的点 v
     * 
     * @return true如果该边包含指定点，否则false
     */
    public boolean containVertex(Vertex v) {
        if (vertices.contains(v))
            return true;
        return false;
    }

    /**
     * 边包含的点集
     * 
     * @return 该边包含的点集(复制集）
     */
    public Set<Vertex> vertices() {
        Set<Vertex> set = new HashSet<Vertex>();
        for(Vertex v:vertices) {
            set.add(v.newVertex());   
        }
       
        return set;

    }

    /**
     * 
     * @return null若边无向,返回该边的源节点集合若边有向
     */
    public abstract Set<Vertex> sourceVertices();

    /**
     * @return null若边无向,返回该边的目标节点集合若边有向
     */
    public abstract Set<Vertex> targetVertices();

    /**
     * @return label
     */
    public String getLabel() {
        return label;
    }
    /**
     * @return weight
     */
public double getWeight() {
    return weight;
}
    /**
     * 更改label的值
     * 
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 更改weight的值
     * 
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * 更改vertices的值
     *
     * @return 返回成员变量vertices供更改
     */
    public Set<Vertex> setVertices() {
        return vertices;
    }
    @Override public boolean equals(Object other) {
        if(this == other)                                      //先检查是否其自反性，后比较other是否为空。这样效率高
            return true;
           if(other == null)        
            return false;
           if( !(other instanceof Edge))
            return false;
          
           final Edge cat = (Edge)other;
          
           if( !getLabel().equals(cat.getLabel()))
            return false;
           return true;

}
    @Override public int hashCode() {
        int result=getLabel().hashCode();
        return result;
    }
    /**
     * 
     * @return 返回一条相同的新边
     */
    abstract public Edge newEdge();
}
