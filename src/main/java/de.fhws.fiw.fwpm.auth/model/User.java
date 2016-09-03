package de.fhws.fiw.fwpm.auth.model;

/**
 * Created by marcelgross on 03.09.16.
 */
public class User {

	private String city;
	private String cn;
	private String degreeProgram;
	private String emailAddress;
	private String facultyName;
	private String firstName;
	private String lastName;
	private String officeName;
	private int postalCode;
	private String role;
	private int semester;
	private String streetAddress;
	private String telephoneNumber;
	private String thumbnailPhoto;
	private String userPrincipalName;
	private int creditPoints;

	public User() {
		this.creditPoints = (int) (Math.random() * 120);
	}

	public User(String city, String cn, String degreeProgram, String emailAddress, String facultyName, String firstName, String lastName,
			String officeName, int postalCode, String role, int semester, String streetAddress, String telephoneNumber,
			String thumbnailPhoto, String userPrincipalName) {
		this.city = city;
		this.cn = cn;
		this.degreeProgram = degreeProgram;
		this.emailAddress = emailAddress;
		this.facultyName = facultyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.officeName = officeName;
		this.postalCode = postalCode;
		this.role = role;
		this.semester = semester;
		this.streetAddress = streetAddress;
		this.telephoneNumber = telephoneNumber;
		this.thumbnailPhoto = thumbnailPhoto;
		this.userPrincipalName = userPrincipalName;
		this.creditPoints = (int) (Math.random() * 120);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDegreeProgram() {
		return degreeProgram;
	}

	public void setDegreeProgram(String degreeProgram) {
		this.degreeProgram = degreeProgram;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
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

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getThumbnailPhoto() {
		return thumbnailPhoto;
	}

	public void setThumbnailPhoto(String thumbnailPhoto) {
		this.thumbnailPhoto = thumbnailPhoto;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public int getCreditPoints() {
		return creditPoints;
	}

	public void setCreditPoints(int creditPoints) {
		this.creditPoints = creditPoints;
	}
}