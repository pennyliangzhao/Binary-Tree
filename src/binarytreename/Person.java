package binarytreename;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Person extends Label {

	String firstName;
	String lastName;
	Person before;
	Person after;
	private boolean isFullNameDisplay = false;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		// Show full name when the user hover over mouse
		setText(this.getShortName());
		setTooltip(new Tooltip(this.getFullName()));
	}

	public Person(String firstName, String lastName, Person before, Person after) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.before = before;
		this.after = after;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Person getBefore() {
		return before;
	}

	public void setBefore(Person before) {
		this.before = before;
	}

	public Person getAfter() {
		return after;
	}

	public void setAfter(Person after) {
		this.after = after;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public String getShortName() {

		return String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0));
	}

	public void toggleText() {
		if (!isFullNameDisplay) {
			isFullNameDisplay = true;
			setText(this.getFullName());
		} else {
			isFullNameDisplay = false;
			setText(getShortName());
		}
	}
}