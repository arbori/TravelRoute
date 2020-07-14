package br.com.travel.model;

public class Route {
	private String origin;
	private String destination; 
	private Float cost;
	
	public Route(String origin, String destination, Float cost) {
		this.origin = origin;
		this.destination = destination;
		this.cost = cost;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origen) {
		this.origin = origen;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}
}
