package br.com.travel.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.travel.algorithm.DijkstraShortestPathAlgorithm;
import br.com.travel.algorithm.ShortestPathAlgorithm;
import br.com.travel.model.Graph;
import br.com.travel.model.Node;
import br.com.travel.model.Route;

public class DijkstraShortestPathAlgorithmTest {
	private List<Route> routes = new ArrayList<>();
	private Graph graph;

	private ShortestPathAlgorithm algorithm = new DijkstraShortestPathAlgorithm();

	private float bestPath = 40.0f;
	
	@Before
	public void setUp() throws Exception {
		routes.add(new Route("GRU","BRC", 10.0f));
		routes.add(new Route("BRC","SCL",  5.0f));
		routes.add(new Route("GRU","CDG", 75.0f));
		routes.add(new Route("GRU","SCL", 20.0f));
		routes.add(new Route("GRU","ORL", 56.0f));
		routes.add(new Route("ORL","CDG",  5.0f));
		routes.add(new Route("SCL","ORL", 20.0f));
		
		graph = new Graph(routes);
	}
	
	@Test
	public void testSearchPath() {
		Node origin = graph.getNode("GRU");
		Node destination = graph.getNode("CDG");
		
		graph.initializeShoertestPath();
		
		algorithm.search(origin);
		
		assertTrue("The best path was not found", destination.getDistance() == bestPath);
	}

}
