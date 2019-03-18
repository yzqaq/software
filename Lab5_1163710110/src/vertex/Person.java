package vertex;

public class Person extends Vertex {
    private String sex;
    private int age;

    public Person(String label) {
        super(label);
        sex="";
        age=-1;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void fillVertexInfo(String[] args) {
        // TODO Auto-generated method stub
        if (args.length == 2) {
            sex = args[0];
            age = Integer.parseInt(args[1]);
        }
        else {
            System.out.println("输入格式错误");
        }

    }
    /**
     * 
     * @return
     */
    public int getAge() {
        return age;
    }
    public String getSex() {
        return sex;
    }

    private void setSex(String sex) {
        this.sex = sex;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public Vertex newVertex() {
        Person p=new Person(getLabel());
        p.setAge(getAge());
        p.setSex(getSex());
        return p;
    }

}
