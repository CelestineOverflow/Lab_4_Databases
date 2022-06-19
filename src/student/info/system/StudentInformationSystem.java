package student.info.system;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

//This is the main class of the Student Information System.
//It provides the main GUI and the main Method  for startup.
public class StudentInformationSystem implements ActionListener {

	public JFrame _window;
	public JPanel _panel;
	public JButton _showStudents;
	public ActionListener _showStudentsAction;
	public JButton _showAttempts;
	public JTable _studentData;
	public DatabaseAccessor _dbAccess;

	public StudentInformationSystem(DatabaseAccessor db) {
		_dbAccess = db;
		startGui();
	}

	public void setupPanel() {
		_panel = new JPanel();
		_showStudents = new JButton("Show all Students");
		_showStudents.addActionListener(this);
		_showAttempts = new JButton("Show Attempts");
		_showAttempts.addActionListener(this);
		_studentData = new JTable(12, 4);
		_studentData.setRowSelectionAllowed(true);
		_panel.add(_showStudents, BorderLayout.NORTH);
		_panel.add(_studentData, BorderLayout.CENTER);
		_panel.add(_showAttempts, BorderLayout.SOUTH);
	}

	public void startGui() {
		_window = new JFrame("Student Information System");
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupPanel();
		_window.setLayout(new BorderLayout());
		_window.setSize(640, 480);
		_window.getContentPane().add(_panel);
		_window.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == this._showStudents) {
			// display students from Database
			int row = 0;
			ArrayList<Student> students = _dbAccess.getAllStudents();
			for (Student s : students) {
				if (row > 11) {
					break;
				}
				_studentData.setValueAt(s._firstName, row, 0);
				_studentData.setValueAt(s._lastName, row, 1);
				_studentData.setValueAt(Integer.toString(s._studentID), row, 2);
				_studentData.setValueAt(s._courseName, row, 3);
				row++;
			}
		} else if (ae.getSource() == this._showAttempts) {
			// open up a new window with the attempts for the selected student
			int selRow = _studentData.getSelectedRow();
			if (selRow == -1) {
				// no row selected, do nothing
			} else {
				Student selected = new Student((String) _studentData.getValueAt(selRow, 0),
						(String) _studentData.getValueAt(selRow, 1),
						Integer.parseInt((String) _studentData.getValueAt(selRow, 2)),
						(String) _studentData.getValueAt(selRow, 3));
				ArrayList<Attempt> attempts = _dbAccess.getAttemptsForStudent(selected);
				AttemptsFrame f = new AttemptsFrame(attempts);
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Task 1.2
		Class.forName("com.mysql.jdbc.Driver");
		String dbUrl = "jdbc:mysql://localhost:3306/stisys";
		String username = "root";
		String password = "1234";
		DatabaseAccessor db = new DatabaseAccessor(dbUrl, username, password);
		StudentInformationSystem sis = new StudentInformationSystem(db);
	}

}
