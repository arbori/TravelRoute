package br.com.travel.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.com.travel.dao.cvs.RoutesCVSDAO;
import br.com.travel.model.Route;

@RunWith(MockitoJUnitRunner.class)
class RoutesCVSDAOTest {

	@Mock
	RoutesCVSDAO routesCVSDAO;
	@Mock
	List<Route> routes;
	
	@BeforeEach
	void setUp() throws Exception {
		when(routesCVSDAO.retrieveRoutes()).thenReturn(routes);
	}

	@Test
	void testRetrieveRoutes() {
		assertTrue(routesCVSDAO.retrieveRoutes() != null);
	}

	@Test
	void testSaveRoutes() throws IOException {
		when(routesCVSDAO.addRoutes(routes)).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
            	return true; 
            }
		});
	}
}
