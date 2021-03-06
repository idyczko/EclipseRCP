package com.starterkit.scheduler;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddTaskDialog extends TitleAreaDialog {
	private WritableList list;

	private Text priorityText;
	private Text placeText;
	private Text descriptionText;
	private Text headerText;

	private Long priority;
	private String place;
	private String description;
	private String header;

	protected AddTaskDialog(Shell parentShell, WritableList list) {
		super(parentShell);
		this.list = list;
	}

	@Override
	public void create() {
		super.create();
		setTitle("TO DO");
		setMessage("Add new task", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		createPriority(container);
		createPlace(container);
		createHeader(container);
		createDescription(container);

		return area;
	}

	private void createPriority(Composite container) {
		Label lbtPriority = new Label(container, SWT.NONE);
		lbtPriority.setText("Priority");

		GridData dataPriority = new GridData();
		dataPriority.grabExcessHorizontalSpace = true;
		dataPriority.horizontalAlignment = GridData.FILL;

		priorityText = new Text(container, SWT.BORDER);
		priorityText.setLayoutData(dataPriority);
	}

	private void createPlace(Composite container) {
		Label lbtPlace = new Label(container, SWT.NONE);
		lbtPlace.setText("Place");

		GridData dataPlace = new GridData();
		dataPlace.grabExcessHorizontalSpace = true;
		dataPlace.horizontalAlignment = GridData.FILL;
		placeText = new Text(container, SWT.BORDER);
		placeText.setLayoutData(dataPlace);
	}

	private void createHeader(Composite container) {
		Label lbtHeader = new Label(container, SWT.NONE);
		lbtHeader.setText("Header");

		GridData dataHeader = new GridData();
		dataHeader.grabExcessHorizontalSpace = true;
		dataHeader.horizontalAlignment = GridData.FILL;
		headerText = new Text(container, SWT.BORDER);
		headerText.setLayoutData(dataHeader);
	}

	private void createDescription(Composite container) {
		Label lbtDescription = new Label(container, SWT.NONE);
		lbtDescription.setText("Description");

		GridData dataDescription = new GridData();
		dataDescription.grabExcessHorizontalSpace = true;
		dataDescription.horizontalAlignment = GridData.FILL;
		descriptionText = new Text(container, SWT.BORDER);
		descriptionText.setLayoutData(dataDescription);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {
		priority = Long.parseLong(priorityText.getText());
		header = headerText.getText();
		place = placeText.getText();
		description = descriptionText.getText();

	}

	@Override
	protected void okPressed() {
		saveInput();
		list.add(new Task(priority, place, header, description));
		super.okPressed();
	}

}
