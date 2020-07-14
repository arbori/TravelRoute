package br.com.travel.dao.cvs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.travel.dao.RoutesDAO;
import br.com.travel.model.Route;

public class RoutesCVSDAO implements RoutesDAO {
	private String datasetFileName;

	private List<Route> data;

	public RoutesCVSDAO(String datasetFileName) {
		this.datasetFileName = datasetFileName;

		data = readData();
	}

	private List<Route> readData() {
		List<Route> routes = new ArrayList<>();
		Path pathToFile = Paths.get(this.datasetFileName);

		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");

				String origin = attributes[0];
				String destination = attributes[1];
				Float cost = Float.valueOf(attributes[2]);

				Route route = new Route(origin, destination, cost);

				// adding book into ArrayList
				routes.add(route);

				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return routes;
	}

	@Override
	public List<Route> retrieveRoutes() {
		List<Route> result = new ArrayList<>();
		result.addAll(data);

		return result;
	}

	@Override
	public boolean addRoutes(List<Route> routes) throws IOException {
		FileWriter fw = new FileWriter(this.datasetFileName, true);
		BufferedWriter bw = new BufferedWriter(fw);

		for (Route route : routes) {
			data.add(route);

			StringBuilder sb = new StringBuilder().append(route.getOrigin()).append(",").append(route.getDestination())
					.append(",").append(route.getCost()).append("\n");

			bw.write(sb.toString());
		}

		bw.close();

		return true;
	}
}
