
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
	
	static ArrayList<Person> everyone = new ArrayList<Person>();
	static BufferedReader reader = 
			new BufferedReader(new InputStreamReader(System.in));
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		prePopulate();
	}
	public static void prePopulate() {
		Student sam = new Student(101, "Sam", "Adams", "Computer Science", "Student");
		Student mary = new Student(102, "Mary", "Barker", "Game Design", "Student");
		Employee ed = new Employee(103, "Ed", "Carter", "Manager","Sales",25, "Employee");
		Employee bob = new Employee(104, "Bob", "Doyle", "Supervisor", "HR", 25, "Employee");
		everyone.add(sam);
		everyone.add(mary);
		everyone.add(ed);
		everyone.add(bob);
	}
	public static void printAll() {
		for(Person p: everyone) {
			System.out.println("ID: " + p.getId());
			System.out.println("First Name: " + p.getFirstName());
			System.out.println("Last Name: " + p.getLastName());
			if(p.getPersonType().equals("Student")) {
				Student s = (Student) p; //Cast object back to be a Student
				System.out.println("Course: " + s.getCourse());
			}
			else {
				Employee e = (Employee) p; //Cast object back to be an Employee
				System.out.println("Job Title: " + e.getJobTitle());
				System.out.println("Department: " + e.getDepartment());
				System.out.println("Salary: " +e.getSalary());
			}
		}
	}

}