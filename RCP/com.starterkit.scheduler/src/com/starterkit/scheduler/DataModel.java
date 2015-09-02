package com.starterkit.scheduler;

import java.util.ArrayList;

import org.eclipse.core.databinding.observable.list.WritableList;

public class DataModel {
	private static DataModel instance = null;
	private WritableList todayTasks = new WritableList(new ArrayList<Task>(),
			Task.class);
	
	private WritableList tomorrowTasks = new WritableList(new ArrayList<Task>(),
			Task.class);
	private WritableList archivedTasks = new WritableList(new ArrayList<Task>(),
			Task.class);

	protected DataModel() {
		todayTasks.add(new Task(1L, "Wrocław", "Header", "Description"));
		todayTasks.add(new Task(2L, "Wrocław", "Header", "Description"));
		todayTasks.add(new Task(3L, "Wrocław", "Header", "Description"));
		todayTasks.add(new Task(4L, "Wrocław", "Header", "Description"));
		todayTasks.add(new Task(5L, "Wrocław", "Header", "Description"));
		todayTasks.add(new Task(6L, "Wrocław", "Header", "Description"));
		todayTasks.add(new Task(7L, "Wrocław", "Header", "Description"));
		todayTasks.add(new Task(8L, "Wrocław", "Header", "Description"));
		todayTasks.add(new Task(9L, "Wrocław", "Header", "Description"));
	}

	public static DataModel getInstance() {
		if (instance == null) {
			instance = new DataModel();
		}
		return instance;
	}

	public WritableList getTodayTasks() {
		return todayTasks;
	}
	
	public WritableList getTomorrowTasks() {
		return tomorrowTasks;
	}
	
	public WritableList getArchivedTasks() {
		return archivedTasks;
	}
}
