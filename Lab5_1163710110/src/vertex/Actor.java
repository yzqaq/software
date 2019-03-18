package vertex;

public class Actor extends Vertex{
 
private String sex;
    private int age;
    public Actor(String label) {
        super(label);
        sex="";
        age=0;
        //  Auto-generated constructor stub
    }
    @Override
    public void fillVertexInfo(String[] args) {
        if (args.length == 2) {
           
            age = Integer.parseInt(args[0]);
            sex = args[1];
            
        }
        else {
            System.out.println("输入格式错误");
        }
        
    }
    public String getSex() {
        return sex;
    }
    public int getAge() {
        return age;
    }
    @Override
    public Vertex newVertex() {
      
        Actor a= new Actor(getLabel());
        a.setAge(getAge());
        a.setSex(getSex());
        return a;
    }
    private void setSex(String sex) {
        this.sex = sex;
    }
    private void setAge(int age) {
        this.age = age;
    }

}
