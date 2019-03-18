package P2;


/**
 * 
 * @author yz
 *生成一个Person对象
 */
public class Person {
    private  final String name;
    // Abstraction function:
    // 表示一个person
    // Representation invariant:
    // name！=null
    // Safety from rep exposure:
    // 所有变量都使用private final类型;因为 String类型是immutable的，所以可以直接return name。
private void checkRep() {
    assert name!=null;
}
    // constructor
    public Person(String name) {
        this.name = name;
        checkRep();
    }
/**
 * 
 * @return person 的名字 
 */
    public String pGet() {
        return name;
    }
 

}


