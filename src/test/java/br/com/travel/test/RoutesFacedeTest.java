package br.com.travel.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.travel.facade.RoutesFacade;

public class RoutesFacedeTest {
	private RoutesFacade routesFacade;
	
	@Before
	public void setUp() throws Exception {
		routesFacade = new RoutesFacade();
		
		String datasetFileName = "/home/arbori/eclipse-workspace/TravelRoute/src/test/resources/input-file.csv";
		
		routesFacade.setDatasetFileName(datasetFileName);
	}

	@Test
	public void testRouteWithWrongFormat() {
		String shortestPath = routesFacade.getResultAsString("wrong format query");
		
		assertTrue("Wrong format with no divisor pass", shortestPath != null && shortestPath.length() == 0);
	}

	@Test
	public void testRouteDontExist() {
		String shortestPath = routesFacade.getResultAsString("BLA-XXX");
		
		assertTrue("Wrong format with no divisor pass", shortestPath != null && shortestPath.length() == 0);
	}

	@Test
	public void testRouteWithoutConection() {
		String shortestPath = routesFacade.getResultAsString("BRC-UOL");
		
		assertTrue("No path as result was gived", shortestPath != null && shortestPath.length() == 0);
	
		String[] resultParts = shortestPath.split(" > ");

		assertTrue("A unconected terminal was found", resultParts.length == 0 || !resultParts[0].endsWith("UOL")); 
	}

	@Test
	public void testShortestPathRoute() {
		String shortestPath = routesFacade.getResultAsString("GRU-CDG");
		
		assertTrue("No path as result was gived", shortestPath != null && shortestPath.length() > 0);

		assertTrue("The path from GRU to CDG is wrong", shortestPath.indexOf("GRU") < shortestPath.indexOf("CDG")); 
	}
}
