package fi.aalto.mcc_group_27_android.model;

public class Contact implements Comparable<Contact> {

	private String _id;
	private String name;
	private String email;
	private String phoneNumber;

	public Contact() {

	}

	public Contact(String name, String email, String phoneNumber) {
		setName(name);
		setEmail(email);
		setPhoneNumber(phoneNumber);
	}

	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		if(name != null && !name.equals("")) {
			return name;			
		} else {
			return email;
		}
	}

	@Override
	public int compareTo(Contact another) {
		return toString().compareTo(another.toString());
	}

}
