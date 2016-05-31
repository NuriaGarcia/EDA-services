package eu.xlime;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * POJO Class: Dataset summarisation elements
 * 
 * @author Nuria Garcia
 * {@link}: ngarcia@expertsystem.com
 *
 */

//@JsonTypeName(value="xlime-summarise")
@XmlRootElement(name="xlime-summarise")
public class SingleEdaItem {
	
	private String triples;
	private String entities;
	private String subjects;
	private String predicates;
	private String objects;
	private String activities;
	private String microposts;
	private String microposts_filter;
	private String newsarticles;
	private String mediaresources;
	private List<HistogramItem> instancesPerClass;
	private List<HistogramItem> tripesPerProperty;
	
	public String getTriples() {
		return triples;
	}
	public void setTriples(String triples) {
		this.triples = triples;
	}
	
	public String getEntities() {
		return entities;
	}
	public void setEntities(String entities) {
		this.entities = entities;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public String getPredicates() {
		return predicates;
	}
	public void setPredicates(String predicates) {
		this.predicates = predicates;
	}
	public String getObjects() {
		return objects;
	}
	public void setObjects(String objects) {
		this.objects = objects;
	}	
	public String getActivities() {
		return activities;
	}
	public void setActivities(String activities) {
		this.activities = activities;
	}
	public String getMicroposts() {
		return microposts;
	}
	public void setMicroposts(String microposts) {
		this.microposts = microposts;
	}	
	public String getMicroposts_filter() {
		return microposts_filter;
	}
	public void setMicroposts_filter(String microposts_filter) {
		this.microposts_filter = microposts_filter;
	}
	public String getNewsarticles() {
		return newsarticles;
	}
	public void setNewsarticles(String newsarticles) {
		this.newsarticles = newsarticles;
	}
	public String getMediaresources() {
		return mediaresources;
	}
	public void setMediaresources(String mediaresources) {
		this.mediaresources = mediaresources;
	}
	public List<HistogramItem> getInstancesPerClass() {
		return instancesPerClass;
	}
	public void setInstancesPerClass(List<HistogramItem> instancesPerClass) {
		this.instancesPerClass = instancesPerClass;
	}
	public List<HistogramItem> getTripesPerProperty() {
		return tripesPerProperty;
	}
	public void setTripesPerProperty(List<HistogramItem> tripesPerProperty) {
		this.tripesPerProperty = tripesPerProperty;
	}
}
