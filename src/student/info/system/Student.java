package student.info.system;

//This class is a simple Data Structure to hold the Values of a single Student.
public class Student {

	public String _firstName;
	public String _lastName;
	public int _studentID;
	public String _courseName;

	public Student(String firstname, String lastname, int studentID, String courseName) {
		_firstName = firstname;
		_lastName = lastname;
		_studentID = studentID;
		_courseName = courseName;
	}

	public void printRaw(){
		String spacer = " - ";
		System.out.println(_studentID + spacer + _firstName + spacer + _lastName);
	}

}
