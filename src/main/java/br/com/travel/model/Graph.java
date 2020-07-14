package br.com.travel.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.travel.algorithm.ShortestPathAlgorithm;

public class Graph {
	private Map<String, Node> nodes = new HashMap<>();

	private ShortestPathAlgorithm shortestPathAlgorithm;
	
	public Graph(List<Route> routes) {
		Node node;
		
		for(Route route: routes) {
			node = nodes.get(route.getOrigin());
			
			if(node == null) {
				node = new Node();
				node.setId(route.getOrigin());
				
				nodes.put(node.getId(), node);
			}

			Node adjacentNode = nodes.get(route.getDestination());
			
			if(adjacentNode == null) {
				adjacentNode = new Node();
				adjacentNode.setId(route.getDestination());
				
				nodes.put(adjacentNode.getId(), adjacentNode);
			}

			Float distance = node.getAdjacents().get(adjacentNode);
			
			if(distance == null) {
				node.getAdjacents().put(adjacentNode, route.getCost());
			}
			else if(!distance.equals(route.getCost())) {
				node.getAdjacents().replace(adjacentNode, distance);
				node.getAdjacents().put(adjacentNode, route.getCost());
			}
		}
	}

	public Node getNode(String key) {
		return this.nodes.get(key);
	}

	public void setShortestPathAlgorithm(ShortestPathAlgorithm algorithm) {
		this.shortestPathAlgorithm = algorithm;
	}
	
	public List<String> retrieveShoertestPath(Node origin, Node destination) {
		List<String> result = new ArrayList<String>();
		
		initializeShoertestPath();
		shortestPathAlgorithm.search(origin);
		 
		if(destination == null || destination.getPredecessor() == null || destination.getDistance() <= 0.0f) {
			return result;
		}
		
		for(Node current = destination; current != null; current = current.getPredecessor()) {
			result.add(current.getId());
		}
		
		Collections.reverse(result);
		
		return result;
	}
	
	public void initializeShoertestPath() {
		Iterator<Node> values = this.nodes.values().iterator();
		
		while(values.hasNext()) {
			Node node = values.next();
			
			node.setDistance(Float.MAX_VALUE);
		}
	}
}
