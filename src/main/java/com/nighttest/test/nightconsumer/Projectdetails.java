package com.nighttest.test.nightconsumer;

public class Projectdetails {
	
	private String projectname;
	private String Softwarename;
	private String Username;
	
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		this.Username = username;
	}
	public String getSoftwarename() {
		return Softwarename;
	}
	public void setSoftwarename(String softwarename) {
		this.Softwarename = softwarename;
	}
	
	public Projectdetails(String projectname,String softwarename,String username ) {
		super();
		this.projectname=projectname;
		this.Username=username;
		this.Softwarename=softwarename;
				
	}
	public Projectdetails() {
		super();
	}
	
	@Override
	public String toString() {
		return "Details [projectname=" + projectname + ", Softwarename="
				+ Softwarename + ", username=" + Username + "]";
	}
	
	

}
