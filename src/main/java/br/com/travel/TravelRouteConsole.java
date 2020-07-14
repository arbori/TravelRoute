package br.com.travel;

import java.util.Scanner;

import br.com.travel.facade.RoutesFacade;

public class TravelRouteConsole {
	private RoutesFacade routesFacede;

	private void start(String datasetFileName) {
		routesFacede = new RoutesFacade();
		routesFacede.setDatasetFileName(datasetFileName);
		
		query();
	}

	private void query() {
		String route = null;
		
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.print("Please enter the route: ");
			
			route = s.nextLine();
			
			String shortestPath = routesFacede.getResultAsString(route);
			
			if(shortestPath != null && shortestPath.length() > 0) {
				System.out.println(shortestPath);
			}
			else if((route != null && route.length() > 0) && (shortestPath == null || shortestPath.length() == 0)) {
				System.out.println(String.format("There is no path for %s", route));
			}
		} while (route != null && route.length() > 0);
		
		s.close();
	}
	
	public static void main(String[] args) {
		checkParameters(args);
		
		TravelRouteConsole travelRoute = new TravelRouteConsole();
		
		travelRoute.start(args[0]);
	}

	private static void checkParameters(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage:");
			System.out.println("java -jar TravelRoute-0.0.1-SNAPSHOT.jar <dataset-file.csv>");
			System.exit(0);
		}
	}
}
