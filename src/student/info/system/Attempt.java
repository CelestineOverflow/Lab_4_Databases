package student.info.system;

//This class is a simple Data Structure to hold the Values of a single Attempt.
public class Attempt {

	public int _year;
	public int _term;
	public String _courseName;
	public int _grade;

	public Attempt(int year, int term, String courseName, int grade) {
		_year = year;
		_term = term;
		_courseName = courseName;
		_grade = grade;
	}

	public void printRaw(){
		String spacer = " - ";
		System.out.println(_year + spacer + _term + spacer + _courseName + _grade);
	}
}
