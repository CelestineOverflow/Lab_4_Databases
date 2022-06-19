package student.info.system;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.util.ArrayList;

//This class displays the attempts as a Table.
public class AttemptsFrame {

	private ArrayList<Attempt> _data;
	private JFrame _frame;
	private JTable _attemptsData;
	
	// The constructor takes the Array of Attempts it is supposed to display.
	public AttemptsFrame(ArrayList<Attempt> data) {
		_data = data;
		displayGui();
	}

	public void displayGui() {
		_frame = new JFrame("Attempts");
		//The table has 12 rows and 4 columns.
		_attemptsData = new JTable(12, 4);
		int row = 0;
		for (Attempt a : _data) {
			if (row > 11) {
				//we have reached our display limit of 12 rows (starting row counter at 0).
				break;
			}
			_attemptsData.setValueAt(a._courseName, row, 0);
			_attemptsData.setValueAt(Integer.toString(a._year), row, 1);
			if (a._term == 0) {
				_attemptsData.setValueAt(new String("Winter"), row, 2);
			} else {
				_attemptsData.setValueAt(new String("Summer"), row, 2);
			}
			_attemptsData.setValueAt(Integer.toString(a._grade), row, 3);
			//more columns could be added here
			row++;
		}
		_frame.add(_attemptsData);
		_frame.setSize(640, 480);
		_frame.setVisible(true);
	}
}
