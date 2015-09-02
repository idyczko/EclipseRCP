package com.starterkit.scheduler;

import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class Tomorrow extends ViewPart {
	public static final String ID = "com.starterkit.scheduler.tomorrowview";

	private Task selectedTask;
	private DataModel model = DataModel.getInstance();
	private TableViewer viewer;
	private Label lblNewLabel;

	public Tomorrow() {

	}

	public void createPartControl(Composite parent) {
		parent.setLayout(null);
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		Table table = viewer.getTable();
		table.setBounds(0, 0, 297, 469);

		createColumns();

		lblNewLabel = new Label(parent, SWT.WRAP);
		lblNewLabel.setBounds(303, 10, 156, 270);
		lblNewLabel.setText("No selected task");

		viewer.setContentProvider(new ObservableListContentProvider());

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				Object firstElement = selection.getFirstElement();
				selectedTask = (Task) firstElement;
				if(selectedTask!=null){					
					lblNewLabel.setText(selectedTask.getDescription());
				}
			}
		});

		viewer.setInput(model.getTomorrowTasks());
		viewer.setComparator(new ViewerComparator() {
			  public int compare(Viewer viewer, Object e1, Object e2) {
			    Task t1 = (Task) e1;
			    Task t2 = (Task) e2;
			    return t1.getPriority().compareTo(t2.getPriority());
			  };
			}); 

		initializeButtons(parent);
	}

	private void createColumns() {
		TableViewerColumn headerColumn = new TableViewerColumn(viewer, SWT.NONE);
		headerColumn.getColumn().setWidth(150);
		headerColumn.getColumn().setText("New Column");
		headerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Task task = (Task) element;
				return task.getHeader();
			}

			public Image getImage(Object obj) {
				return PlatformUI.getWorkbench().getSharedImages()
						.getImage(ISharedImages.IMG_ETOOL_HOME_NAV);
			}
		});

		TableViewerColumn placeColumn = new TableViewerColumn(viewer, SWT.NONE);
		placeColumn.getColumn().setWidth(100);
		placeColumn.getColumn().setText("New Column");
		placeColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Task task = (Task) element;
				return task.getPlace();
			}
		});

		TableViewerColumn priorityColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		priorityColumn.getColumn().setWidth(47);
		priorityColumn.getColumn().setText("New Column");
		priorityColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Task task = (Task) element;
				return task.getPriority().toString();
			}
		});
	}

	private void initializeButtons(Composite parent) {
		Button archive = new Button(parent, SWT.PUSH);
		archive.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				archiveTask(selectedTask);
				lblNewLabel.setText("No selected task");
			}
		});
		archive.setBounds(303, 412, 156, 57);
		archive.setText("Archive");
		
		Button addTask = new Button(parent, SWT.NONE);
		addTask.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewTask();
			}

		});
		addTask.setBounds(303, 349, 156, 57);
		addTask.setText("Add new task");
	}
	
	private void addNewTask() {
		TitleAreaDialog dialog = new AddTaskDialog(getSite().getShell(), model.getTomorrowTasks());
		dialog.open();
	}


	private void archiveTask(Task task) {
		if (task != null) {
			model.getTomorrowTasks().remove(task);
			model.getArchivedTasks().add(task);
		}
	}


	public void setFocus() {

	}
}