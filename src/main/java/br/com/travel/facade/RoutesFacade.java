package br.com.travel.facade;

import java.io.IOException;
import java.util.List;

import br.com.travel.algorithm.DijkstraShortestPathAlgorithm;
import br.com.travel.algorithm.ShortestPathAlgorithm;
import br.com.travel.dao.RoutesDAO;
import br.com.travel.dao.cvs.RoutesCVSDAO;
import br.com.travel.model.Graph;
import br.com.travel.model.Node;
import br.com.travel.model.Route;
import br.com.travel.model.TravelPath;

public class RoutesFacade {
	private String datasetFileName;
	
	private RoutesDAO routesDAO;
	
	private Graph graph;
	
	private ShortestPathAlgorithm algorithm;
	
	public void setDatasetFileName(String datasetFileName) {
		this.datasetFileName = datasetFileName;
		
		routesDAO = new RoutesCVSDAO(this.datasetFileName);
		
		algorithm = new DijkstraShortestPathAlgorithm();
		
		graph = new Graph(routesDAO.retrieveRoutes());
		graph.setShortestPathAlgorithm(algorithm);
	}

	public TravelPath queryShortestPath(String route) {
		TravelPath result = new TravelPath();
		
		String[] terminals = route.split("-");
		
		if(terminals == null || terminals.length != 2) {
			return result;
		}
		
		Node origin = graph.getNode(terminals[0]);
		Node destination = graph.getNode(terminals[1]);
		
		if(origin == null || destination == null) {
			return result;
		}
		
		result.setPath(graph.retrieveShoertestPath(origin, destination));
		result.setCost(destination.getDistance());
		
		return result;
	}

	public String getResultAsString(String route) {
		TravelPath travelPath = queryShortestPath(route);
		
		if(travelPath.getPath().size() == 0) {
			return "";
		}
		
		StringBuilder result = new StringBuilder()
				.append("Best route: ");
		
		int size = travelPath.getPath().size();
		
		for(int terminalId = 0; terminalId < size; terminalId++) {
			String terminal = travelPath.getPath().get(terminalId);
			
			result.append(terminal);
			
			if(terminalId + 1 < size) {
				result.append(" - ");
			}
		}
		
		result.append(" > $").append(travelPath.getCost());
		
		return result.toString();
	}

	public String addRoutes(List<Route> routes) {
		try {
			routesDAO.addRoutes(routes);

			graph = new Graph(routesDAO.retrieveRoutes());
			graph.setShortestPathAlgorithm(algorithm);

			return "{\"msg\":\"sucess\"}";
		} catch (IOException e) {
			return new StringBuilder().append("{\"msg\":\"").append(e.getMessage()).append("\"}").toString();
		}
	}
}
