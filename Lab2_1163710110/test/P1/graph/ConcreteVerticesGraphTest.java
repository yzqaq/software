/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package test.P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import P1.graph.ConcreteVerticesGraph;
import P1.graph.Graph;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }

    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    // Testing strategy for ConcreteEdgesGraph.toString()
    // 先生成一个graph，再将graph.toString的子符串拆分存入list中，再依次检测list的元素

    // tests for ConcreteVerticesGraph.toString()
    @Test
    public void testToString() {

        Graph<String> graph = emptyInstance();
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";
        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.add(v4);
        graph.set(v1, v2, 1);
        graph.set(v2, v3, 1);
        graph.set(v3, v4, 1);
        graph.set(v3, v2, 2);
        int a1 = 0, a2 = 0, a3 = 0, a4 = 0, t = 0;
        String[] s = graph.toString().split(" |\n|点:");
        for (int i = 0; i < s.length; i++) {

            if (s[i].equals(v1))
                a1++;
            if (s[i].equals(v2))
                a2++;
            if (s[i].equals(v3))
                a3++;
            if (s[i].equals(v4))
                a4++;
            if (s[i].equals("v1->v2:1") || s[i].equals("v2->v3:1") || s[i].equals("v3->v4:1")
                    || s[i].equals("v3->v2:2"))
                t++;
        }
        assertEquals(1, a1);
        assertEquals(1, a2);
        assertEquals(1, a3);
        assertEquals(1, a4);
        assertEquals(4, t);

    }
    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex
    //

    // tests for operations of Vertex

}
