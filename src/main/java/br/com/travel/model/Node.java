package br.com.travel.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
	private String id;
	private Node predecessor;
	private float distance;
	
	private Map<Node, Float> adjacents = new HashMap<>();
	
	private List<Node> shortestPath = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Node getPredecessor() {
		return predecessor;
	}
	public void setPredecessor(Node predecessor) {
		this.predecessor = predecessor;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public Map<Node, Float> getAdjacents() {
		return adjacents;
	}
	public void setAdjacents(Map<Node, Float> adjacents) {
		this.adjacents.clear();
		this.adjacents.putAll(adjacents);
	}
	public List<Node> getShortestPath() {
		return shortestPath;
	}
	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath.clear();
		this.shortestPath.addAll(shortestPath);
	}
}
