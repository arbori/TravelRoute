package br.com.travel.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import br.com.travel.model.Node;

public class DijkstraShortestPathAlgorithm implements ShortestPathAlgorithm {
	@Override
	public void search(Node origin) {
		origin.setDistance(0);

		List<Node> settledNodes = new ArrayList<>();
		List<Node> unsettledNodes = new ArrayList<>();

		unsettledNodes.add(origin);

		while (unsettledNodes.size() != 0) {
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);

			for (Entry<Node, Float> adjacencyPair : currentNode.getAdjacents().entrySet()) {
				Node adjacentNode = adjacencyPair.getKey();
				float edgeWeight = adjacencyPair.getValue();

				if (!settledNodes.contains(adjacentNode)) {
					boolean hasBeenChange = changeMinimumDistance(currentNode, adjacentNode, edgeWeight);

					changePredecessor(currentNode, adjacentNode, hasBeenChange);

					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
	}

	private Node getLowestDistanceNode(List<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		float lowestDistance = Integer.MAX_VALUE;

		for (Node node : unsettledNodes) {
			float nodeDistance = node.getDistance();

			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}

		return lowestDistanceNode;
	}

	private boolean changeMinimumDistance(Node sourceNode, Node evaluationNode, float edgeWeigh) {
		float sourceDistance = sourceNode.getDistance();

		if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeigh);

			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);

			evaluationNode.setShortestPath(shortestPath);

			return true;
		}

		return false;
	}

	private void changePredecessor(Node sourceNode, Node targetNode, boolean change) {
		if (change) {
			targetNode.setPredecessor(sourceNode);
		}
	}
}
