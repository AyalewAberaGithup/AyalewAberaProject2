
package MySpringBootRestAPI_Project2.MySpringBootRestAPI_Project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	/**
	 * Working URL Lists 
	 * URL :  http://localhost:8080 
	 * URL :  http://localhost:8080/students
	 * URL :  http://localhost:8080/students/?name=Caleb
	 */
	//========== Option ONE Default  URL :  http://localhost:8080  ================
	/*
	 * Option ONE  : Default 
	 * URL :  http://localhost:8080 				
	 * return all student records with json format
	 */
	@GetMapping  							
	public List <Student> student() throws IOException {
		return readFile();
	}
	//====== Option TWO with URL "students" or URL :http://localhost:8080/students ============
	/*
	 * Option TWO : 
	 * URL :  http://localhost:8080/students 		
	 * return all student records with json format
	 */
	@GetMapping ("/students") 				
	public List <Student> students() throws IOException {
		return readFile();
	}
	//====Option three @PathVariable URL :  http://localhost:8080/students/Caleb=========
	/*
	 * Option three : 
	 * URL :  http://localhost:8080/students/Caleb
	 * http://localhost:8080/students/?name=Caleb
	 * return all student records with json format
	 */
	@GetMapping ("/students{first_name}") 							
	public Student studentByFname(@PathVariable ("first_name") String first_name) throws IOException {

		System.out.println("search Student by Name : " + first_name);
		List <Student> students =  readFile();

		for (Student studentFname: students) {

			if(studentFname.getFirst_name().equalsIgnoreCase(first_name)) {
				System.out.println("found student :" + studentFname);
				return studentFname;
			}
		}
		System.out.println("No  student name found" + first_name);
		return null;
	}

	//========================== Option four @PathVariable for two parameter====================
	/*
	 * Option three : 
	 * URL :  http://localhost:8080/student/Caleb/male
	 * return all student records with json format
	 */

	//@GetMapping ("/students{firstName}") 		
	@GetMapping("/student/{firstName}/{gender}")
	public Student studentByfullName(
			@PathVariable ("firstName") String firstName,
			@PathVariable("gender") String gender) throws IOException {

		System.out.println("search Student by Name : " + firstName + " by gender :" + gender);
		List <Student> students =  readFile();

		for (Student studentInfo: students) {

			if(studentInfo.getFirst_name().equalsIgnoreCase(firstName) && studentInfo.getGender().equals(gender)) {
				System.out.println("found student :" + studentInfo);
				return studentInfo;
			}
		}
		System.out.println("No  student name found : " + firstName + " with :" + gender);
		return null;
	}
	//========================== Option five  @RequestParam ====================
	/* Option five  @RequestParam
	 * build rest API to handle query parameter 
	 * URI ====> http://localhost:8080/student/query?gps=3.4&gender=male
	 * @RequestParam annotation for query set up
	 */
	@GetMapping("/student/query")
	public Student StudentQueryParam(
			@RequestParam(name = "gps")String gpa, 
			@RequestParam(name = "gender")String gender) throws IOException {
		System.out.println("search Student by Name : " + gpa + " by gender :" + gender);
		List <Student> students =  readFile();

		for (Student studentInfo: students) {

			if(studentInfo.getGender().equalsIgnoreCase(gender)) {
				System.out.println("found student :" + gpa + " with gender : " + gender);
				return studentInfo;
			}
		}
		System.out.println("No  student  found :" + gpa + " and :" + gender);
		return null;
	}

	public List <Student> readFile() throws IOException {
		//read file 
		String fileName = "C:\\Users\\Ayalew Abera\\Desktop\\MySpringBootRestAPI_Project2"
				+ "\\MySpringBootRestAPI_Project2\\src\\main\\resources\\student.txt";
		FileReader fReader = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fReader);
		List <Student> students = new ArrayList();
		// read the first for Header 
		String Headline = br.readLine();
		// read the first for line 
		String line = br.readLine();
		// read each line from student
		while (line != null) {
			//split the line that is read from the file , for Example David ,3.5 to "David" and "3.5"
			String [] tokens = line.split( ",");
			int id = Integer.parseInt(tokens[0]);		//parse the first token to int and assign to id
			String  first_name = tokens[1];             //assign the second value to first name 
			//String  name = tokens[1];             //assign the second value to first name 
			double gpa = Double.parseDouble(tokens[2]); //parse the third token to double and assign to GPA
			String  email = tokens[3]; 					//assign the fourth token or value to email
			String  gender = tokens[4]; 				//assign the fifth token or value to gender
			//create an object of a class or  Student
			Student studentOne = new Student(id, first_name,gpa, email, gender); // object creation 
			students.add(studentOne); 	//add each Student object to the list array
			//System.out.println("Here is the content : " + studentOne);
			line =br.readLine(); 		//read the next line
		}
		fReader.close();
		br.close();
		return students;
	}

	/** ========= Option five  Average Grade URL ====>http://localhost:8080/grade ====================
	 * Option Six  : Average Grade URL ====>http://localhost:8080/grade
	 * 
	 */
	@GetMapping ("/grade")  					
	public List<Student> getGPA() throws IOException {
		return readAverageGPA();
	}

	public List<Student> readAverageGPA() throws IOException {
		//read file 
		String fileName = "C:\\Users\\Ayalew Abera\\Desktop\\MySpringBootRestAPI_Project2\\"
				+ "MySpringBootRestAPI_Project2\\src\\main\\resources\\student.txt";
		FileReader fReader = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fReader);
		List <Student> studentsGpa = new ArrayList();
		String headline = br.readLine(); 		// read the first line for Header and assigned to headline
		String line = br.readLine(); 			// read the second line and assign to line 
		List<Student> gradeAverage = null;
	
		while (line != null) {					  // read each line from student file as long as there is a line
			String [] tokens = line.split( ","); 		//split each line with "," for separator and assign to token of a string array type
			double	gpa = Double.parseDouble(tokens[2]); //parsing the gpa and changing to double 
			Student student = new Student(gpa);
			studentsGpa.add(student);
			line =br.readLine(); 						//read the next line
			gradeAverage = displayGPA(studentsGpa);
		}
			System.out.println("gradeAverage for element for all:"  + gradeAverage);
		return gradeAverage;
	}

	public List<Student> displayGPA(List<Student> studentsGpa)  {
		double result []  = new double [4];
		//double [] result = new (studentsGpa.size()); 
		for(Student gpas:studentsGpa) {
			//System.out.println("Members or element  :"  + gpas);
		}
		return studentsGpa;

	}


}
