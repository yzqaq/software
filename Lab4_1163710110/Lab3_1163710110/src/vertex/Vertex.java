package vertex;

public abstract class Vertex {
    // 外部可观察的标签信息
  private  String label;

    /**
     * 构造函数
     */
  public  Vertex(String label) {
        this.label = label;
    }

    /**
     * 为特定应用中 构造函数 的具体节点添加详细属性信息,所需的信息参见表 3 的最后一列,参数 的次序与表 3 保持一致。因为不同的 Vertex
     * 子类型的属性不同,故该操作可设计为 abstract,在具体子类里实现之。
     */
    abstract public void fillVertexInfo(String[] args);

    /**
     * 
     * @return 节点label的取值
     */
    public String getLabel() {
        return label;
    }
    /**
     * 更改label的值
     * @param label
     */
    public void setLabel(String label) {
        this.label=label;
    }
    /**
     * 生成一个新的相同Vertex
     * @return 一个新的相同Vertex
     */
    abstract public Vertex newVertex() ;
    
    @Override public boolean equals(Object other) {
        if(this == other)                                      //先检查是否其自反性，后比较other是否为空。这样效率高
            return true;
           if(other == null)        
            return false;
           if( !(other instanceof Vertex))
            return false;
          
           final Vertex cat = (Vertex)other;
          
           if( !getLabel().equals(cat.getLabel()))
            return false;
           return true;

}
    @Override public int hashCode() {
        int result=getLabel().hashCode();
        return result;
    }
}
