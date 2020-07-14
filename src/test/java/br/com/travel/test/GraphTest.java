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

public class GraphTest {
	private List<Route> routes = new ArrayList<>();
	private Graph graph;
	
	@Before
	public void setUp() throws Exception {
		routes.add(new Route("GRU","BRC",10.0f));
		routes.add(new Route("BRC","SCL", 5.0f));
		routes.add(new Route("GRU","CDG",75.0f));
		routes.add(new Route("GRU","SCL",20.0f));
		routes.add(new Route("GRU","ORL",56.0f));
		routes.add(new Route("ORL","CDG", 5.0f));
		routes.add(new Route("SCL","ORL",20.0f));
		
		graph = new Graph(routes);
		
		ShortestPathAlgorithm algorithm = new DijkstraShortestPathAlgorithm();
		
		graph.setShortestPathAlgorithm(algorithm);
	}
	
	@Test
	public void testCreateGraph() {
		Route route = routes.get(2);
		Node node = graph.getNode(route.getOrigin());
		
		assertTrue(String.format("Node %s has been not retrieved from Graph", route.getOrigin()), 
				node != null);
	}
	
	@Test
	public void testPath() {
		Node origin = graph.getNode("GRU");
		Node destination = graph.getNode("CDG");

		String solution[] = {"GRU","BRC", "SCL", "ORL", "CDG"};
		
		List<String> shortestPath = graph.retrieveShoertestPath(origin, destination);
		
		assertTrue("There is no solution", shortestPath.size() > 0);
		
		assertTrue("Number of terminals is diferent from spected solution", shortestPath.size() == solution.length);
		
		for(int solutionId = 0; solutionId < solution.length; solutionId++) {
			assertTrue(String.format("Terminals %d is diferent in spected solution", solutionId), 
					shortestPath.get(solutionId).equals(solution[solutionId]));
		}
		
		assertTrue("The value cost is diferent than spect", graph.getNode("CDG").getDistance() == 40.0f);
	}
}
