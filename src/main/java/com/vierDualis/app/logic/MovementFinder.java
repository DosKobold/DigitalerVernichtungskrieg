package logic;

import java.util.*;

public class MovementFinder {

    public static ArrayList<ArrayList<Character>> findMovementRange(ArrayList<ArrayList<Character>> map, Troop current) {
        Map<N, NodeWrapperForTreeSet<N>> nodeWrappers = new HashMap<>();
        TreeSet<NodeWrapperForTreeSet<N>> queue = new TreeSet<>();
        ArrayList<ArrayList<Character>> reachableNodes = new ArrayList<>();

// Add source to queue
        NodeWrapperForTreeSet<N> sourceWrapper = new NodeWrapperForTreeSet<>(source, 0, null);
        nodeWrappers.put(source, sourceWrapper);
        queue.add(sourceWrapper);

        while (!queue.isEmpty()) {
            NodeWrapperForTreeSet<N> nodeWrapper = queue.pollFirst();
            N node = nodeWrapper.getNode();
            reachableNodes.add(node);

            // Iterate over all neighbors
            Set<N> neighbors = graph.adjacentNodes(node);
            for (N neighbor : neighbors) {
                // Calculate total distance from start to neighbor via current node
                int distance = graph.edgeValue(node, neighbor).orElseThrow(IllegalStateException::new);
                int totalDistance = nodeWrapper.getTotalDistance() + distance;

                // Ignore neighbor if total distance exceeds movement points
                if (totalDistance > movementPoints) {
                    continue;
                }

                // Neighbor not yet discovered?
                NodeWrapperForTreeSet<N> neighborWrapper = nodeWrappers.get(neighbor);
                if (neighborWrapper == null) {
                    neighborWrapper = new NodeWrapperForTreeSet<>(neighbor, totalDistance, nodeWrapper);
                    nodeWrappers.put(neighbor, neighborWrapper);
                    queue.add(neighborWrapper);
                }

                // Neighbor discovered, but total distance via current node is shorter?
                // --> Update total distance and predecessor
                else if (totalDistance < neighborWrapper.getTotalDistance()) {
                    queue.remove(neighborWrapper);

                    neighborWrapper.setTotalDistance(totalDistance);
                    neighborWrapper.setPredecessor(nodeWrapper);

                    queue.add(neighborWrapper);
                }
            }
        }

        return reachableNodes;
    }
}
