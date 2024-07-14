package logic;

import java.util.*;

public class MovementFinder {

	/*
	 * Alternative implementation of the troop-marking-algorithm.
	 * It doesn't suffice for the given requirements, but at least does _something_.
	 */
	public static ArrayList<ArrayLists<Character>> stupidMovementRange(ArrayList<ArrayList<Character>> map, Troop current) {
		ArrayList<ArrayList<Character>> marked = new ArrayList<>();
		
		int line, field;
		for (line=0; line<map.size(); line++) {
			for (field=0; field<map.get(line).size(); field++) {
				// Found the position of the current troop on the map
				if (map.get(line).get(field).getX() == current.getX() && map.get(line).get(field).getY() == current.getY()) {
					
				}
			}
		}
	}






    public static ArrayList<ArrayList<Character>> findMovementRange(ArrayList<ArrayList<Character>> map, Troop current) {
        Map<N, NodeWrapperForTreeSet<N>> nodeWrappers = new HashMap<>();
        TreeSet<NodeWrapperForTreeSet<N>> queue = new TreeSet<>();
        ArrayList<ArrayList<Character>> reachableNodes = new ArrayList<>();

        String source = troop.getX() + "," + troop.getY();
        int movementRange = troop.getMovementRange();
        NodeWrapperForTreeSet sourceWrapper = new NodeWrapperForTreeSet(source, 0, null);
        nodeWrappers.put(source, sourceWrapper);
        queue.add(sourceWrapper);

        while (!queue.isEmpty()) {
            NodeWrapperForTreeSet nodeWrapper = queue.pollFirst();
            String node = nodeWrapper.getNode();
            int currentDistance = nodeWrapper.getTotalDistance();

            if (currentDistance <= movementRange) {
                reachablePositions.add(node);
            } else {
                continue;
            }

            // Iterate over all neighbors
            Set<String> neighbors = graph.adjacentNodes(node);
            for (String neighbor : neighbors) {
                if (reachablePositions.contains(neighbor)) {
                    continue;
                }
		int distance = graph.edgeValue(node, neighbor).orElseThrow(IllegalStateException::new);
                int totalDistance = currentDistance + distance;

                NodeWrapperForTreeSet neighborWrapper = nodeWrappers.get(neighbor);
                if (neighborWrapper == null) {
                    neighborWrapper = new NodeWrapperForTreeSet(neighbor, totalDistance, nodeWrapper);
                    nodeWrappers.put(neighbor, neighborWrapper);
                    queue.add(neighborWrapper);
                } else if (totalDistance < neighborWrapper.getTotalDistance()) {
                    queue.remove(neighborWrapper);
                    neighborWrapper.setTotalDistance(totalDistance);
                    neighborWrapper.setPredecessor(nodeWrapper);
                    queue.add(neighborWrapper);
                }
            }
        }

        Map<String, Integer> result = new HashMap<>();
        for (NodeWrapperForTreeSet wrapper : nodeWrappers.values()) {
            if (wrapper.getTotalDistance() <= movementRange) {
                result.put(wrapper.getNode(), wrapper.getTotalDistance());
            }
        }
        return result;
    }
}
