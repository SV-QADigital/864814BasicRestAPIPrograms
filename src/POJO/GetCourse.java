package POJO;

public class GetCourse {
	
	//shortcut getterand setter Alt+shift+s
	

	private String services;
	 private String expertise;
	 private courses courses;
	 private String instructor;
	 private String linkdIn;

	 private String url;
	 public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public POJO.courses getCourses() {
		return courses;
	}
	public void setCourses(POJO.courses courses) {
		this.courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkdIn() {
		return linkdIn;
	}
	public void setLinkdIn(String linkdIn) {
		this.linkdIn = linkdIn;
	}
	
}
