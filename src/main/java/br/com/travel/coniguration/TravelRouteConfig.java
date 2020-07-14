package br.com.travel.coniguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.travel.dao.RoutesDAO;
import br.com.travel.dao.cvs.RoutesCVSDAO;
import br.com.travel.facade.RoutesFacade;

@Configuration
public class TravelRouteConfig {
	@Value("${dataset.file}")
    private String datasetFile;

	@Bean
	public RoutesDAO getDAO() {
		return new RoutesCVSDAO(datasetFile);
	}
	
	@Bean
	public RoutesFacade getFacade() {
		RoutesFacade facade = new RoutesFacade();
		
		facade.setDatasetFileName(datasetFile);
		
		return facade;
	}
}
