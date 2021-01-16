import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
	private JFrame frame;
	private JPanel panel;
	private JPanel sudoku;

	public GUI() {
		frame = new JFrame();
		frame.setLayout(new GridLayout(0, 1));

		JButton button = new JButton("Solve!");

		button.addActionListener(this);

		panel = new JPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(9, 9));

		sudoku = new SudokuPanel();
		sudoku.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		sudoku.setLayout(new GridLayout(9, 9, 2, 2));

		frame.add(sudoku);
		frame.add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sudoku");
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new GUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		//transfer the JTextFields into a int[][]
		int[][] values = new int[9][9];
		JTextField[][] strs = ((SudokuPanel) sudoku).fields();

		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				values[i][j] = strs[i][j].getText().equals("") ? 0 : Integer.parseInt(strs[i][j].getText());
			}
		}

		Board solved = (new Board(values)).solve();
		int[][] solvedValues = solved.vals();
		//pass into board constructor
		//solve it
		//throw the solved numbers back onto the board
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				strs[i][j].setText("" + solvedValues[i][j]);
			}
		}

		((SudokuPanel) sudoku).setFields(strs);
	}
}

class SudokuPanel extends JPanel {
	private JTextField[][] fields;

	public SudokuPanel() {
		fields = new JTextField[9][9];
		for(int i = 0; i < fields.length; i++) {
			for(int j = 0; j < fields[0].length; j++) {
				fields[i][j] = new JTextField();
				this.add(fields[i][j]);
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(450,450);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int size = Math.min(getHeight() - 4, getWidth() - 4) / 9;
		int y = (getHeight() - (size * 9)) / 2;
		for(int h = 0; h < 9; h++) {
			int x = (getWidth() - (size * 9)) / 2;
			for(int v = 0; v < 9; v++) {
				g.drawRect(x, y, size, size);
				x += size;
			}
			y += size;
		}
		g2.setStroke(new BasicStroke(3));
		int y2 = (getHeight() - (size * 9)) / 2;
		for(int h = 0; h < 3; h++) {
			int x2 = (getWidth() - (size * 9)) / 2;
			for(int v = 0; v < 3; v++) {
				g2.drawRect(x2, y2, size*3, size*3);
				x2 += size*3;
			}
			y2 += size*3;
		}

		g2.dispose();
	}

	public JTextField[][] fields(){
		return fields;
	}

	public void setFields(JTextField[][] newfields) {
		this.fields = newfields;
	}
}
