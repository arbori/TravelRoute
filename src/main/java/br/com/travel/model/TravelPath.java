package br.com.travel.model;

import java.util.ArrayList;
import java.util.List;

public class TravelPath {
	private List<String> path = new ArrayList<>();
	private Float cost;

	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	
	public List<String> getPath() {
		return path;
	}
	public void setPath(List<String> path) {
		this.path.clear();
		this.path.addAll(path);
	}
	
}
