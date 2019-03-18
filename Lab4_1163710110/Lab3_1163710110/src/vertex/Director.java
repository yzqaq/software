package vertex;

public class Director extends Vertex{
 
private String sex;
    private int age;
    public Director(String label) {
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
    private void setSex(String sex) {
        this.sex = sex;
    }
    private void setAge(int age) {
        this.age = age;
    }
    @Override
    public Vertex newVertex() {
       Director d=new Director(getLabel());
       d.setSex(getSex());
       d.setAge(getAge());
        return d;
    }

}
