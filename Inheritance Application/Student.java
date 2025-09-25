
public class Student extends Person{

	private String course;
	
	public Student(int id, String first, String last, String course,String personType) {
		super(id,first,last,personType); //pass parent variables along using super
		this.course = course; //assign child variables normally
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
}

