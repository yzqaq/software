package factory.graphFactory;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public abstract class  GraphFactory {
    /**
     * 从文件中读入一个图
     * @param filePath 文件路径
     * @return 返回文件所确定的图如果读入成功，否则返回一个空图
     */
    public  abstract  Graph<Vertex,Edge> createGraph(String filePath);
}
