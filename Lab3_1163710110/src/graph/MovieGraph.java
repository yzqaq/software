package graph;

public class MovieGraph extends ConcreteGraph {
    /*
     * 边类型：MovieActorRelation ,MovieDirectorRelation SameMovieHyperEdge
     * 点类型："Movie","Actor","Director"
     */
    private final String[] vertexType = { "Movie", "Actor", "Director" };
    private final String[] edgeType = { "MovieActorRelation", "MovieDirectorRelation", "SameMovieHyperEdge" };
    private String graphName;
    
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
    
}
