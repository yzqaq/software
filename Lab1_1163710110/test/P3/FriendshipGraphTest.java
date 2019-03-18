package test.P3;

import src.P3.FriendshipGraph;
import src.P3.Person;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FriendshipGraphTest {

    @Test
    void testAddVertex() {
        FriendshipGraph fg = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        fg.addVertex(rachel);
        assertEquals(rachel, fg.persons.get(0));
    }

    @Test
    void testAddEdge() {
        // fail("Not yet implemented");
        FriendshipGraph fk = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person rache = new Person("Rache");
        fk.addVertex(rache);
        fk.addVertex(rachel);
        fk.addEdge(rachel, rache);
        fk.addEdge(rache, rachel);
        assertEquals(1, fk.juzhen.get(fk.persons.indexOf(rachel) * fk.size0 + fk.persons.indexOf(rache)), 0.001);
    }

    @Test
    void testGetDistance() {
        // fail("Not yet implemented");
        FriendshipGraph fk = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person rache = new Person("Rache");
        fk.addVertex(rache);
        fk.addVertex(rachel);
        fk.addEdge(rachel, rache);
        fk.addEdge(rache, rachel);
        assertEquals(1, fk.getDistance(rachel, rache));
    }

}
