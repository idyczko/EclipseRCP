package com.starterkit.scheduler;


public class Task extends ModelObject{
	private Long priority;


	private String place;
	private String header;
	private String description;

	public Task(Long priority, String place, String header, String description) {
		this.priority = priority;
		this.header = header;
		this.description = description;
		this.place = place;
	}

	public Long getPriority() {
		return priority;
	}

	public String getHeader() {
		return header;
	}

	public String getDescription() {
		return description;
	}

	public String getPlace() {
		return place;
	}

	public void setPriority(Long priority) {
		firePropertyChange("priority", this.priority, this.priority = priority);
	}
	
	public void setPlace(String place) {
		firePropertyChange("place", this.place, this.place = place);
	}
	
	public void setHeader(String header) {
		firePropertyChange("header", this.header, this.header = header);
	}
	
	public void setDescription(String description) {
		firePropertyChange("description", this.description, this.description = description);
	}

}
