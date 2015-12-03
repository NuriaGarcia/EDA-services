package eu.xlime;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * Listener to manage cache configuration 
 * 
 * @author Nuria Garcia
 * {@link}: ngarcia@expertsystem.com
 *
 */

public class EdaServicesInitializer implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent ctx) {
		CacheManager.getInstance().shutdown();
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		CacheManager cmanager = CacheManager.create();
		Cache memoryOnlyCache = new Cache("sparqlCache", 1000, false, false, 43200, 43200);
		cmanager.addCache(memoryOnlyCache);
		ctx.setAttribute("sparqlCache", cmanager);
	}
}

//Example
//http://localhost:8080/EDAServices/xlime-data-summary/counters?action=getCounts&object=triples
//http://localhost:8080/EDAServices/xlime-data-summary/histograms?action=getHistogram&object=instancesPerClass&format=json

