package de.fhws.fiw.fwpm.auth.model;

public class UserFactory {

	public static User getUser(String input) {
		if (input.equalsIgnoreCase("bond")) {
			return getEmployee();
		} else {
			return getStudent(input.toLowerCase());
		}
	}

	public static User getStudent(String kNr) {
		return new User("Würzburg", kNr, "BIN", "email@web.de", "FIW", "Nicolas", "Cage", "", 123456, "student", 42, "SHL", "+0600", "", kNr);
	}

	public static User getStudent() {
		return getStudent("k314884");
	}

	public static User getEmployee() {
		return new User("Würzburg", "bond", "BIN", "email@web.de", "FIW", "James", "Bond", "", 123456, "mitarbeiter", 42, "SHL", "+0600", "", "Bond");
	}
}
