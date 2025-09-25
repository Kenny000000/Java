
public class Employee extends Person {
	
	private String jobTitle;
	private String department;
	private double salary;
	
	public Employee() {
		
	}
	
	public Employee(int id, String firstName,String lastName,String jobTitle,
			String department, double salary, String personType) {
		
		super(id,firstName,lastName,personType);
		this.jobTitle = jobTitle;
		this.department = department;
		this.salary = salary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	
	
}

