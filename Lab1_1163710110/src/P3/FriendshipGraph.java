package src.P3;

import src.P3.Person;
import java.util.ArrayList;
import java.util.List;

public class FriendshipGraph {
    public List<Person> persons = new ArrayList<Person>();
    public List<Integer> juzhen = new ArrayList<Integer>();
    public int size0 = 0;

    public void addVertex(Person na) {
        if (persons.indexOf(na) == -1) {
            persons.add(na);
            size0 = persons.size();
            for (int i = 0; i < 2 * size0 - 1; i++) {
                juzhen.add(100000);// 初始化矩阵
            }
        } else {
            System.out.println("error:demand unique name ");
            System.exit(-1);
        }

    }

    public void addEdge(Person a, Person b) {
        juzhen.set(persons.indexOf(a) * size0 + persons.indexOf(b), 1);
    }

    public int getDistance(Person a, Person b) {
        if (a == b)
            return 0;
        else {
            for (int k = 0; k < size0; k++) {
                for (int i = 0; i < size0; i++) {
                    for (int j = 0; j < size0; j++) {
                        if (juzhen.get(i * size0 + j) > juzhen.get(i * size0 + k) + juzhen.get(k * size0 + j)) {
                            juzhen.set(i * size0 + j, juzhen.get(i * size0 + k) + juzhen.get(k * size0 + j));
                        }
                    }
                }
            }
        }
        int l = juzhen.get(size0 * persons.indexOf(a) + persons.indexOf(b));
        if (l < 100000)
            return l;
        return -1;
    }

    public static void main(String args[]) {
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
        System.out.println(graph.getDistance(rachel, ross));
        // should print 1
        System.out.println(graph.getDistance(rachel, ben));
        // should print 2
        System.out.println(graph.getDistance(rachel, rachel));
        // should print 0
        System.out.println(graph.getDistance(rachel, kramer));
        // should print ‐1
    }

}
