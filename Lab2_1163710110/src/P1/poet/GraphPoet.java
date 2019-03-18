/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
//import java.nio.Buffer;
import java.util.Scanner;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>
 * GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph. Vertices in the graph are words. Words are defined as
 * non-empty case-insensitive strings of non-space non-newline characters. They
 * are delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     Hello, HELLO, hello, goodbye!
 * </pre>
 * <p>
 * the graph would contain two edges:
 * <ul>
 * <li>("hello,") -> ("hello,") with weight 2
 * <li>("hello,") -> ("goodbye!") with weight 1
 * </ul>
 * <p>
 * where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>
 * Given an input string, GraphPoet generates a poem by attempting to insert a
 * bridge word between every adjacent pair of words in the input. The bridge
 * word between input words "w1" and "w2" will be some "b" such that w1 -> b ->
 * w2 is a two-edge-long path with maximum-weight weight among all the
 * two-edge-long paths from w1 to w2 in the affinity graph. If there are no such
 * paths, no bridge word is inserted. In the output poem, input words retain
 * their original case, while bridge words are lower case. The whitespace
 * between every word in the poem is a single space.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     This is a test of the Mugar Omni Theater sound system.
 * </pre>
 * <p>
 * on this input:
 * 
 * <pre>
 *     Test the system.
 * </pre>
 * <p>
 * the output poem would be:
 * 
 * <pre>
 *     Test of the system.
 * </pre>
 * 
 * <p>
 * PS2 instructions: this is a required ADT class, and you MUST NOT weaken the
 * required specifications. However, you MAY strengthen the specifications and
 * you MAY add additional methods. You MUST use Graph in your rep, but otherwise
 * the implementation of this class is up to you.
 */
public class GraphPoet {

    private final Graph<String> graph = Graph.empty();

    // Abstraction function:
    // 通过有向图实现诗局的补全
    // Representation invariant:
    // graph!=null
    // Safety from rep exposure:
    // graph 是private final 的且String是immutable的，故graph也是immutable;

    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus
     *            text file from which to derive the poet's affinity graph
     * @throws IOException
     *             if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        // throw new RuntimeException("not implemented");
        BufferedReader reader = null;
        String temp = "";
        String tem1 = "";
        String tem2 = "";
        reader = new BufferedReader(new FileReader(corpus));
        while ((temp = reader.readLine()) != null) {
            Scanner in = new Scanner(temp);
            tem1 = in.next().toLowerCase();
            graph.add(tem1);
            while (in.hasNext()) {
                tem2 = in.next().toLowerCase();
                graph.add(tem2);
                graph.set(tem1, tem2, 1);
                tem1 = tem2;
            }
            in.close();
        }
        reader.close();
        checkRep();

    }

    // checkRep
    private void checkRep() {
        assert graph != null;
    }

    /**
     * Generate a poem.
     * 
     * @param input
     *            string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        // throw new RuntimeException("not implemented");
        ;
        String temp1 = null;
        String temp2 = null;
        Scanner in = new Scanner(input);
        temp1 = in.next();
        String output = temp1;
        while (in.hasNext()) {
            temp2 = in.next();
            for (String key : graph.targets(temp1.toLowerCase()).keySet()) {
                if (graph.targets(temp1.toLowerCase()).get(key) == 1
                        && graph.targets(key).containsKey(temp2.toLowerCase())
                        && graph.targets(key).get(temp2.toLowerCase()) == 1) {
                    output = output + " " + key;
                }
            }
            output = output + " " + temp2;
            temp1 = temp2;
        }
        in.close();

        return output;
    }

    // toString()
    /**
     * 
     * @return 该图的字符串行式 (点与边的顺序随机）: “点：... 边： ..."
     */
    @Override
    public String toString() {
        String d = "点：";
        String s = "边：\n";
        for (String e : graph.vertices()) {
            d = d + e + " ";
            for (Map.Entry<String, Integer> entry : graph.targets(e).entrySet()) {
                s = s + e + "->" + entry.getKey() + ":" + entry.getValue() + '\n';
            }
        }
        return d + '\n' + s;
    }

}
