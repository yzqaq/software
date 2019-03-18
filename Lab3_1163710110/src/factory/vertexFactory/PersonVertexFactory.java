/**
 * 
 */
package factory.vertexFactory;
import vertex.Person;
import vertex.Vertex;
/**
 * @author yz
 *
 */
public class PersonVertexFactory extends VertexFactory{

    @Override
    public Vertex createVertex(String label, String[] args) {
        Person v=new Person(label);
        v.fillVertexInfo(args);
        return v;
    }
   

}
