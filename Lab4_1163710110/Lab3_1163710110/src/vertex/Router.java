package vertex;

public class Router extends Vertex{
    private String iPAdress;

    public Router(String label) {
        super(label);
       iPAdress="";
    }

    @Override
    public void fillVertexInfo(String[] args) {
        if(args.length==1) {
            boolean flag=true;
            String []take=args[0].split(".");
            int size=take.length;
            if(size!=4) {
               flag=false;
            }
            for(int i=0;i<size;i++) {
                if(Integer.parseInt(take[i])<0||Integer.parseInt(take[i])>255) {
                    flag=false;
                }
            }
            if(flag)
            iPAdress=args[0];
        }
        System.out.println("地址错误格式");
       
        
    }
    /**
     * 
     * @return ip地址
     */
    public String getIP() {
        return iPAdress;
    }
    @Override
    public Vertex newVertex() {
      Router c=new Router(getLabel());
      c.setiPAdress(getIP());
        return c;
    }

    private void setiPAdress(String iPAdress) {
        this.iPAdress = iPAdress;
    }

}
