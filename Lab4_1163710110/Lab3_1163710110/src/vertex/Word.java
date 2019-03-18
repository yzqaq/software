package vertex;

public class Word extends Vertex{
   

    public Word(String label) {
        super(label);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void fillVertexInfo(String[] args) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Vertex newVertex() {
        Word w=new Word(getLabel());
        return w;
    }

}
