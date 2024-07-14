package logic;

public class NodeWrapperForTreeSet implements Comparable<NodeWrapperForTreeSet> {
    private final String node;
    private int totalDistance;
    private NodeWrapperForTreeSet predecessor;

    public NodeWrapperForTreeSet(String node, int totalDistance, NodeWrapperForTreeSet predecessor) {
        this.node = node;
        this.totalDistance = totalDistance;
        this.predecessor = predecessor;
    }

    public String getNode() {
        return node;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public NodeWrapperForTreeSet getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(NodeWrapperForTreeSet predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public int compareTo(NodeWrapperForTreeSet other) {
        int distanceComparison = Integer.compare(this.totalDistance, other.totalDistance);
        if (distanceComparison != 0) {
            return distanceComparison;
        }
        return this.node.compareTo(other.node);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodeWrapperForTreeSet other = (NodeWrapperForTreeSet) obj;
        return node.equals(other.node);
    }

    @Override
    public int hashCode() {
        return node.hashCode();
    }
}
