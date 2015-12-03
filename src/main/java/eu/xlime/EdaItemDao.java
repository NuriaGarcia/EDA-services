package eu.xlime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.google.common.collect.Multiset;
import com.isoco.kontology.access.OntologyAccessException;
import com.isoco.kontology.access.OntologyManager;
import com.isoco.kontology.access.VoiDManager;
import com.isoco.kontology.ontologies.dao.OntologyManagerImpl.UserPassword;
import com.isoco.kontology.ontologies.dao.SesameDAOFactory;

/**
 * Class to get access to the endpoint and retrieve dataset summarisation information
 * 
 * @author Nuria Garcia
 * {@link}: ngarcia@expertsystem.com
 *
 */

public class EdaItemDao {

	private static OntologyManager ontoMan;
	private static VoiDManager ontoManager;

	public EdaItemDao(ServletContext context){
		this.getOntologyAccess(context);
		EdaItemDao.ontoManager = (VoiDManager) ontoMan;
	}	

	private OntologyManager getOntologyAccess(ServletContext context){
		if (ontoMan != null) return ontoMan;
		Properties access = new Properties();
		try {
			InputStream is = context.getResourceAsStream("/WEB-INF/resources/credentials.properties");
			access.load(is);
			is.close();
			
			String endpoint = access.getProperty("endpoint");
			String user = access.getProperty("user");
			String passwd = access.getProperty("passwd");
			ontoMan = new SesameDAOFactory().createRemoteDAO(endpoint, new UserPassword(user, passwd), 2.0);
			return ontoMan;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getNumTriples(){
		try {
			return String.valueOf(ontoManager.getNumberOfTriples());
		} catch (OntologyAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getNumEntities(){ //Time out
		try {
			return String.valueOf(ontoManager.getNumberOfEntities());			
		} catch (OntologyAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getNumSubjects(){ //Time out
		try {
			return String.valueOf(ontoManager.getDistinctSubjects());
		} catch (OntologyAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getNumPredicates(){ //Time out
		try {
			return String.valueOf(ontoManager.getNumberOfProperties());
		} catch (OntologyAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getNumObjects(){ //Time out
		try {
			return String.valueOf(ontoManager.getDistinctObjects());
		} catch (OntologyAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getNumActivities(){
		String data = null;
		String query = "PREFIX prov: <http://www.w3.org/ns/prov#> "
				+ "SELECT count(*) AS ?count WHERE {"
				+ " ?s a prov:Activity .}";
		Map<String, Map<String, String>> result = ontoMan.executeAdHocSPARQLQuery(query);
		if(!result.isEmpty()){
			data = result.get("1").get("count");			
		}
		return data;
	}

	public String getNumMicroposts(){
		String data = null;
		String query = "PREFIX sioc: <http://rdfs.org/sioc/ns#> "
				+ "SELECT count(*) AS ?count WHERE {"
				+ " ?s a sioc:MicroPost .}";
		Map<String, Map<String, String>> result = ontoMan.executeAdHocSPARQLQuery(query);
		if(!result.isEmpty()){
			data = result.get("1").get("count");			
		}	
		return data;
	}

	public String getNumNewsarticles(){
		String data = null;
		String query = "PREFIX kdo: <http://kdo.render-project.eu/kdo#> "
				+ "SELECT count(*) AS ?count WHERE { "
				+ "?s a kdo:NewsArticle .}";
		Map<String, Map<String, String>> result = ontoMan.executeAdHocSPARQLQuery(query);
		if(!result.isEmpty()){
			data = result.get("1").get("count");			
		}
		return data;
	}

	public String getNumMediaresources(){
		String data = null;
		String query = "PREFIX ma: <http://www.w3.org/ns/ma-ont#> "
				+ "SELECT count(*) AS ?count WHERE { "
				+ "?s a ma:MediaResource .}";
		Map<String, Map<String, String>> result = ontoMan.executeAdHocSPARQLQuery(query);
		if(!result.isEmpty()){
			data = result.get("1").get("count");			
		}
		return data;
	}

	public List<HistogramItem> getInstancesPerClass(){
		try {
			List<HistogramItem> items = new ArrayList<HistogramItem>();
			Multiset<URI> instances = ontoManager.getClassesByInstanceCount();	
			for (URI uri : instances.elementSet()) {
				HistogramItem item = new HistogramItem();
				item.setUri(uri.toString());
				item.setCount(String.valueOf(instances.count(uri)));
				items.add(item);
			}			
			return items; 
		} catch (OntologyAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<HistogramItem> getTriplesPerProperty(){ //Time out
		try {
			List<HistogramItem> items = new ArrayList<HistogramItem>();
			Multiset<URI> instances = ontoManager.getPropertiesByTripleCount();
			for (URI uri : instances.elementSet()) {
				HistogramItem item = new HistogramItem();
				item.setUri(uri.toString());
				item.setCount(String.valueOf(instances.count(uri)));
				items.add(item);
			}			
			return items;
		} catch (OntologyAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
