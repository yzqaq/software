package graph;

import edge.Edge;

public class GraphPoet extends ConcreteGraph {
    /*
     * 诗歌图边类型有 Word 点类型 WordNeighborhood
     */
    private String graphName = "";
    private final String[] vertexType = { "Word" };
    private final String[] edgeType = { "WordNeighborhood" };

    public String[] getVertexType() {
        return vertexType;
    }

    public String[] getEdgeType() {
        return edgeType;
    }

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }

    @Override
    public boolean addEdge(Edge edge) {
        if (vertices().containsAll(edge.vertices())) {
            double weight = 0;
            for (Edge e : edges()) {
                if (e.equals(edge)) {
                    weight = e.getWeight();
                    edge.setWeight(weight + 1);
                    removeEdge(e);
                    break;
                }
                super.addEdge(edge);
                return true;
            }

        }

        return false;

    }
}
