package br.com.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.travel.facade.RoutesFacade;
import br.com.travel.model.Route;
import br.com.travel.model.TravelPath;

@RestController
public class TrevelRouteController {
	@Autowired
	private RoutesFacade facade;

	@GetMapping("/bestroute/{route}")
	public TravelPath BestRoute(@PathVariable String route) {
		TravelPath travelPath = facade.queryShortestPath(route);
		
		return travelPath;
	}

	@PostMapping("/bestroute/addroutes")
	public String addRoutes(@RequestBody List<Route> routes) {
		String response = facade.addRoutes(routes);
		
		return response;
	}
}
