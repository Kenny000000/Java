
public class Person {
	
	private int id;
	private String firstName;
	private String lastName;
	private String personType;
	
	public Person() {
		
	}

	public Person(int id, String firstName, String lastName, String personType) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.setPersonType(personType);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}
	
	
	
	

}
