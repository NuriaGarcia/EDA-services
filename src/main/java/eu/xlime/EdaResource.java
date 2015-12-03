package eu.xlime;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Class to manage the input parameters from the REST web-services
 * 
 * @author Nuria Garcia
 * {@link}: ngarcia@expertsystem.com
 *
 */

@Path("/xlime-data-summary")
public class EdaResource {

	private SingleEdaItem xlime;
	private EdaItemDao eid;

	@Context
	private ServletContext context;

	@Path("/counters")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response service(@QueryParam("action") String action, @QueryParam("object") String object, @QueryParam("format") String format) {

		if (action.equals("getCounts")){
			String object_string = "";
			if (object != null){
				object_string = object;
			}			
			this.executeCallCounters(object_string);

			return Response
					// Set the status, entity and media type of the response.
					.ok(xlime, "json".equals(format) ? MediaType.APPLICATION_JSON : MediaType.APPLICATION_XML)
					.build();
		}

		return Response.status(Response.Status.BAD_REQUEST)
				.entity("Only actions available: getCounts")
				.type(MediaType.TEXT_PLAIN).build();		
	}

	@Path("/histograms")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response service2(@QueryParam("action") String action, @QueryParam("object") String object, @QueryParam("format") String format) {

		if (action.equals("getHistogram")){
			String object_string = "";
			if (object != null){
				object_string = object;
			}			
			this.executeCallHistograms(object_string);

			return Response
					// Set the status, entity and media type of the response.
					.ok(xlime, "json".equals(format) ? MediaType.APPLICATION_JSON : MediaType.APPLICATION_XML)
					.build();
		}

		return Response.status(Response.Status.BAD_REQUEST)
				.entity("Only actions available: getHistogram")
				.type(MediaType.TEXT_PLAIN).build();		
	}

	private void executeCallCounters(String object){
		xlime = new SingleEdaItem();
		eid = new EdaItemDao(context);
		CacheManager cm = (CacheManager) context.getAttribute("sparqlCache");
		Cache cache = cm.getCache("sparqlCache");
		String[] params = object.split(",");
		for (String p : params) {
			Element e = cache.get(p);			
			if (p.equals("triples"))
				if (e != null){ 
					xlime.setTriples((String) e.getObjectValue()); // get object from cache
				}
				else{
					xlime.setTriples(eid.getNumTriples());
					cache.put(new Element(p, xlime.getTriples()));
				}
			/*if (p.equals("entities")){
			 	if (e != null){
			 		xlime.setEntities((String) e.getObjectValue());
			 	}
			 	else{
					xlime.setEntities(eid.getNumEntities());
					cache.put(new Element(p, xlime.getEntities()));
				}
			}
			if (p.equals("subjects")){
				if (e != null){
			 		xlime.setSubjects((String) e.getObjectValue());
			 	}
				else{
					xlime.setSubjects(eid.getNumSubjects());
					cache.put(new Element(p, xlime.getSubjects()));
				}
			}
			if (p.equals("predicates")){
				if (e != null){
			 		xlime.setPredicates((String) e.getObjectValue());
			 	}
				else{
					xlime.setPredicates(eid.getNumPredicates());
					cache.put(new Element(p, xlime.getPredicates()));
				}
			}
			if (p.equals("objects")){
				if (e != null){
			 		xlime.setObjects((String) e.getObjectValue());
			 	}
				else{
					xlime.setObjects(eid.getNumObjects());
					cache.put(new Element(p, xlime.getObjects()));
				}
			}*/			

			if (p.equals("activities")){
				if (e != null){ 
					xlime.setActivities((String) e.getObjectValue()); 
				}
				else{
					xlime.setActivities(eid.getNumActivities());
					cache.put(new Element(p, xlime.getActivities()));
				}
			}
			if (p.equals("microposts")){
				if (e != null){ 
					xlime.setMicroposts((String) e.getObjectValue()); 
				}
				else{
					xlime.setMicroposts(eid.getNumMicroposts());
					cache.put(new Element(p, xlime.getMicroposts()));
				}
			}
			if (p.equals("newsarticles")){
				if (e != null){ 
					xlime.setNewsarticles((String) e.getObjectValue()); 
				}
				else{
					xlime.setNewsarticles(eid.getNumNewsarticles());
					cache.put(new Element(p, xlime.getNewsarticles()));
				}
			}
			if (p.equals("mediaresources")){
				if (e != null){ 
					xlime.setMediaresources((String) e.getObjectValue()); 
				}
				else{
					xlime.setMediaresources(eid.getNumMediaresources());	 
					cache.put(new Element(p, xlime.getMediaresources()));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void executeCallHistograms(String object){
		xlime = new SingleEdaItem();
		eid = new EdaItemDao(context);
		CacheManager cm = (CacheManager) context.getAttribute("sparqlCache");
		Cache cache = cm.getCache("sparqlCache");
		String[] params = object.split(",");
		for (String p : params) {
			Element e = cache.get(p);			
			if (p.equals("instancesPerClass")){
				if (e != null){ 
					xlime.setInstancesPerClass((List<HistogramItem>) e.getObjectValue()); 
				}
				else{
					xlime.setInstancesPerClass(eid.getInstancesPerClass());
					cache.put(new Element(p, xlime.getInstancesPerClass()));
				}
			}
			/*if (p.equals("triplesPerProperty")){
				if (e != null){ 
					xlime.setTripesPerProperty((List<HistogramItem>) e.getObjectValue()); 
				}
				else{
					xlime.setTripesPerProperty(eid.getTriplesPerProperty());
					cache.put(new Element(p, xlime.getTripesPerProperty()));
				}
			}*/
		}
	}
}
