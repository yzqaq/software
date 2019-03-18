/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package test.P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import P1.graph.Graph;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test methods
 * to this class, or change the spec of {@link #emptyInstance()}. Your tests
 * MUST only obtain Graph instances by calling emptyInstance(). Your tests MUST
 * NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    // Testing strategy
    // add(L vertex):
    // 输入vertex,返回true若graph不含vertex，否则返回false
    // 添加一个新vertex，检测返回值是否为true，并检查是否添加成功，重新添加一个vertex，检查返回值，并检查是否添加成功
    // set(L source, L target, int weight):
    // 添加一条新边并设置weight=1，检测返回值是否为0,重新设置该边weight=2,检测返回值是否为1;再重新设置该边weight=0,测试返回值是否为2
    // remove(L vertex):
    // 删除已有的vertex，检查返回值是否为true，再次删除vertex，检查返回值是否为false;
    // Set<L> vertices():
    // 输入一系列vertex后，检查返回值是否与输入的一样
    // public Map<L, Integer> sources(L target)：
    // 建立一个graph，调用函数并检查返回值与已知一样
    // public Map<L, Integer> targets(L source):
    // 建立一个graph，调用函数并检查返回值与已知一样
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        // you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
    }

    // other tests for instance methods of Graph
    @Test
    public void testAdd() {
        Graph<String> graph = emptyInstance();
        String vertex = "v1";
        assertEquals(true, graph.add(vertex));
        assertEquals(false, graph.add(vertex));
    }

    @Test
    public void testSet() {
        Graph<String> graph = emptyInstance();
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        assertEquals(0, graph.set(v1, v2, 1));
        assertEquals(0, graph.set(v1, v3, 2));
        assertEquals(1, graph.set(v1, v2, 2));
        assertEquals(2, graph.set(v1, v2, 0));
        assertEquals(0, graph.set(v1, v2, 1));
    }

    @Test
    public void testRemove() {
        Graph<String> graph = emptyInstance();
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.set(v1, v2, 1);
        assertEquals(true, graph.remove(v3));
        assertEquals(false, graph.remove(v3));
    }

    @Test
    public void testVertices() {
        Graph<String> graph = emptyInstance();
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        graph.add(v1);
        graph.add(v2);
        graph.set(v1, v2, 1);
        graph.set(v2, v3, 1);
        Set<String> set = graph.vertices();
        assertEquals(true, set.contains(v1));
        assertEquals(true, set.contains(v2));
        assertEquals(false, set.contains(v3));

    }

    @Test
    public void testSources() {
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
        Map<String, Integer> map = graph.sources(v2);
        assertEquals(true, map.containsKey(v1));
        assertEquals(true, map.containsKey(v3));
        assertEquals(true, map.get(v1) == 1);
        assertEquals(true, map.get(v3) == 2);
    }

    @Test
    public void testTargets() {
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
        graph.set(v1, v3, 2);
        Map<String, Integer> map = graph.targets(v1);
        assertEquals(true, map.containsKey(v2));
        assertEquals(true, map.containsKey(v3));
        assertEquals(true, map.get(v2) == 1);
        assertEquals(true, map.get(v3) == 2);
    }

}
