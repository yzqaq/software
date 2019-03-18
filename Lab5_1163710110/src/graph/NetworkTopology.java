package graph;
 
public class NetworkTopology extends ConcreteGraph {
    /*
     * 
     */
    private final String[] vertexType = { "Computer", "Server", "Router" };
    private final String[] edgeType = { "NetworkConnection" };
    private String graphName = "";

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }

    public String[] getVertexType() {
        return vertexType;
    }

    public String[] getEdgeType() {
        return edgeType;
    }

}
