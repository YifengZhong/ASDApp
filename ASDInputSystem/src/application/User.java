package application;

import java.io.Serializable;

public class User  implements Serializable {
	private String id;
	private String lastName;
	private String firstName;
	private String address;
	private String gender;
	public User(String id, String lastName, String firstName, String address,String gender) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.gender = gender;
	}
	public String getId() {
		return id;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getAddress() {
		return address;
	}
	public String getGender() {return gender;}
	
	
}
