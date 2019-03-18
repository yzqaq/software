package test.P2;


import static org.junit.Assert.*;

import org.junit.Test;

import P2.FriendshipGraph;
import P2.Person;

public class FriendshipGraphTest {
    /*
     * Testing strategy addVertex(Person na) 输入vertex,返回true若graph不含vertex，否则返回false
     * 添加一个新vertex，检测返回值是否为true，并检查是否添加成功，重新添加一个vertex，检查返回值，并检查是否添加成功
     * addEdge(Person a, Person b) 添加一条新边edge返回true，重复添加edge返回false
     * getDistance(Person a, Person b) 初始化并赋值FriendshipGraph，检查返回值分别1,0,-1,n
     */

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testAddVertex() {
        FriendshipGraph graph = new FriendshipGraph();
        Person p1 = new Person("Tom");
        assertEquals(true, graph.addVertex(p1));
        assertEquals(false, graph.addVertex(p1));

    }

    @Test
    public void testAddEdge() {
        FriendshipGraph graph = new FriendshipGraph();
        Person p1 = new Person("Tom");
        Person p2 = new Person("Jim");
        assertEquals(true, graph.addEdge(p1, p2));
        assertEquals(true, graph.addEdge(p2, p1));
        assertEquals(false, graph.addEdge(p1, p2));
    }

    @Test
    public void testGetDistannnce() {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        assertEquals(1, graph.getDistance(rachel, ross));
        assertEquals(2, graph.getDistance(rachel, ben));
        assertEquals(0, graph.getDistance(ben, ben));
        assertEquals(-1, graph.getDistance(rachel, kramer));
    }

}
