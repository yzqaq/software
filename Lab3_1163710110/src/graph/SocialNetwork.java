package graph;

import edge.Edge;
import vertex.Vertex;

public class SocialNetwork extends ConcreteGraph{
    private final String [] vertexType = { "Person" };
    private final String[] edgeType = { "FriendTie","CommentTie","ForwardTie" };
    private String graphName="";
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
        if (!vertices().containsAll(edge.vertices())) {
            return false;
        }
        if (edges().contains(edge)) {
            return false;
        }
        if (edges().isEmpty()) {
            edge.setWeight(1);
            super.addEdge(edge);
            return true;
        } else {
            adjustWeight(edge.getWeight());
            super.addEdge(edge);
            return true;
        }

    }

    @Override
    public boolean removeVertex(Vertex v) {
        if (vertices().contains(v)) {
            for (Edge e : edges()) {
                if (e.containVertex(v)) {
                    removeEdge(e);

                }
                super.removeVertex(v);
                return true;
            }

            return true;
        }
        return false;

    }

    @Override
    public boolean removeEdge(Edge edge) {
        if (super.removeEdge(edge)) {
            adjsustReWeight(edge.getWeight());
            return true;
        }
        return false;

    }

    private void adjsustReWeight(double weight) {
        double preweight = 0;
        for (Edge e : edges()) {
            preweight = e.getWeight();
            super.removeEdge(e);
            e.setWeight(preweight / (1 - weight));
            super.addEdge(e);
        }
    }

    private void adjustWeight(double weight) {
        double getweight = 0;
        for (Edge e : edges()) {
            super.removeEdge(e);
            getweight = e.getWeight();
            e.setWeight(getweight * (1 - weight));
            super.addEdge(e);
        }
    }
}
