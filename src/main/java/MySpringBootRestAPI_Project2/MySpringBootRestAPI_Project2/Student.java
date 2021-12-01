
/*Create a maven project that do the following:
 * Read the data stored at the following URL or file 
 * Create a class called Student that stores the information above.
 * Please As you see, there are multiple students information. 
 * You need to store them as array of students.
 * Provide a search functionality by name or by gender.
 */

package MySpringBootRestAPI_Project2.MySpringBootRestAPI_Project2;

public class Student {
	private int id;
	private String first_name;
	private double gpa;
	private String email;
	private String gender;

	public Student(int id, String first_name, double gpa, String email, String gender) {
		this.id = id;
		this.first_name = first_name;
		this.gpa = gpa;
		this.email= email;
		this.gender= gender;
	}

	public Student(double gpa) {
		super();
		this.gpa = gpa;
	}

	public Student(double gpa, String gender) {
		super();
		this.gpa = gpa;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public  String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ","
				+ " first_name=" + first_name + ", "
				+ "gpa=" + gpa + ","
				+ " email=" + email + ","
				+ " gender="+ gender + "]";
	}
}
