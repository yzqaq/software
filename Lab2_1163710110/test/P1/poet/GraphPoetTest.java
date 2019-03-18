/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package test.P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import P1.poet.GraphPoet;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

    // Testing strategy
    // poem()
    // test.txt
    // toString()
    // test.txt
    // 先生成一个poet，再将poet.toString的子符串拆分存入list中，再依次检测list的元素

    // tests for ConcreteEdgesGraph.toString()

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // tests
    @Test
    public void testPoem() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/P1/poet/test.txt"));
        assertEquals("I loved a nice girl who had advantages but one disadvantage that she did not liked me",
                poet.poem("I loved a girl had advantages one disadvantage she did liked me"));
    }

    @Test
    public void testToString() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/P1/poet/test.txt"));
        String v1 = "i";
        String v2 = "loved";
        String v3 = "liked";
        String v4 = "me";
        int a1 = 0, a2 = 0, a3 = 0, a4 = 0, t = 0;
        String[] s = poet.toString().split(" |\n|点:");
        for (int i = 0; i < s.length; i++) {

            if (s[i].equals(v1))
                a1++;
            if (s[i].equals(v2))
                a2++;
            if (s[i].equals(v3))
                a3++;
            if (s[i].equals(v4))
                a4++;
            if (s[i].equals("i->loved:1") || s[i].equals("loved->a:1") || s[i].equals("liked->me:1")
                    || s[i].equals("a->nice:1"))
                t++;
        }
        assertEquals(1, a1);
        assertEquals(1, a2);
        assertEquals(1, a3);
        assertEquals(1, a4);
        assertEquals(4, t);

    }
}
