package student.info.system;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;

//This class is used to access the database. Currently we are using dummy values,
// which are access by using "dummy" as the database connection URL.
public class DatabaseAccessor {

	private Connection _con = null;

	public DatabaseAccessor(String dbUrl, String username, String password) {

		if (dbUrl.equals("dummy")) {
			// when we use "dummy" Entries without DB connection
		} else {
			// the real deal, connecting to the database
			try {
				_con = DriverManager.getConnection(dbUrl, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Task 1.3
	public ArrayList<Student> getAllStudents() {
		// TODO implement SQL Query to get all students and
		ArrayList<Student> students = new ArrayList<>();
		String query = "select * from Student;";
		try {
			Statement statement = _con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()){
				int studentID = Integer.parseInt(resultSet.getString("studentID"));
				String firstName = resultSet.getString("firstName");
				String lastName =  resultSet.getString("lastName");
				String courseName = "empty";
				students.add(new Student(firstName, lastName, studentID, courseName));
				String spacer = " - ";
				System.out.println(studentID + spacer + firstName + spacer + lastName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// why in the fuck are we using static arrays?
		// store these Student in the variable result.
		// Student[] result = new Student[] { new Student("John", "Doe", 1, "Information Engineering") };
		return students;
	}

	// Task 1.4
	public ArrayList<Attempt> getAttemptsForStudent(Student student) {
		ArrayList<Attempt> results = new ArrayList<>();
		// TODO write a Query that gets all the attempts a student has in the database
		String query = "select * from attempts where studentID = " + String.valueOf(student._studentID) + ";";
		try {
			Statement statement = _con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()){
				int year = Integer.parseInt(resultSet.getString("attemptYear"));
				int term = Integer.parseInt(resultSet.getString("term"));
				String courseName  = resultSet.getString("attemptYear");
				int grade = Integer.parseInt(resultSet.getString("grade"));
				Attempt anAttempt =  new Attempt(year, term, courseName, grade);
				System.out.println("hi");
				anAttempt.printRaw();
				results.add(anAttempt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// and
		// store these Attempts int the variable result
		//Attempt[] result = new Attempt[] { new Attempt(2022, 0, "SC 1", 15) };
		return results;
	}
}
