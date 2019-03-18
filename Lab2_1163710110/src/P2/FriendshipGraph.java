package P2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import P1.graph.*;

/**
 * 通过调用Graph生成可变的有向图模拟社交网络图
 * 
 * @author yz
 *
 */
public class FriendshipGraph {
    private final Graph<Person> fgraph = Graph.empty();

    // Abstraction function:
    // 生成一个社交网络图，并提供查找两个person之间的最短距离
    // Representation invariant:
    // fgraph!=null
    // Safety from rep exposure:
    // graph 是private final 的且String是immutable的，故graph也是immutable;
    /**
     * 检查fgraph不为null
     */
    private void checkRep() {
        assert fgraph != null;
    }

    public FriendshipGraph() {
        // Auto-generated constructor stub
        checkRep();
    }

    /**
     * 添加一个person
     * 
     * @param na
     *            一个Person对象
     * @return 返回true如果该网络图不含这个Person，否则返回false
     */
    public boolean addVertex(Person na) {
        return fgraph.add(na);

    }

    /**
     * 
     * @param a
     *            起点Person
     * @param b
     *            终点Person
     * @return true如果该网络图不含该Edge，否则返回false
     */
    public boolean addEdge(Person a, Person b) {
        int i = fgraph.set(a, b, 1);
        if (i == 0)
            return true;
        return false;

    }

    /**
     * 
     * @param a
     *            Person a
     * @param b
     *            Person b
     * @return a与b之间的距离，返回 0 如果a=b，返回 -1 如果a与b距离为无穷大，
     */
    public int getDistance(Person a, Person b) {
        if (a.equals(b)) {
            return 0;
        } else {
            int d = 1;
            Set<Person> set = new HashSet<Person>();// 存已遍历过的person
            List<Person> list1 = new ArrayList<>();// 存下一次的person
            list1.add(a);
            set.add(a);
            //按层遍历无向图
            while (true) {
                List<Person> list2 = list1;// 将list1赋给list2
                list1 = new ArrayList<Person>();// 初始化list1
                // 退出while循环条件
                if (list2.isEmpty())
                    break;
                for (int i = 0; i < list2.size(); i++) {
                    Person temp = list2.get(i);
                    for (Person key : fgraph.targets(temp).keySet()) {
                        if (!set.contains(key)) {

                            if (key.equals(b)) {
                                return d;
                            } else {
                                set.add(key);
                                list1.add(key);
                            }
                        }
                    }
                }
                d++;
            }
            return -1;

        }
    }
}
