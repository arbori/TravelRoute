package br.com.travel.dao;

import java.io.IOException;
import java.util.List;

import br.com.travel.model.Route;

public interface RoutesDAO {

	public List<Route> retrieveRoutes();

	public boolean addRoutes(List<Route> routes) throws IOException;
}
