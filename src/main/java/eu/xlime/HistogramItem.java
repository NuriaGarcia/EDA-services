package eu.xlime;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJO Class: Histogram items
 * 
 * @author Nuria Garcia
 * {@link}: ngarcia@expertsystem.com
 *
 */

@XmlRootElement
public class HistogramItem {
	
	private String uri;
	private String count;	
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}
