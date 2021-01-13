import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.*;

public class GUI implements ActionListener {
	private int count = 0;
	private JFrame frame;
	private JLabel label;
	private JPanel panel;

	public GUI() {
		frame = new JFrame();
		frame.setLayout(new GridLayout(0, 1));

		JButton button = new JButton("Click me");

		button.addActionListener(this);
		label = new JLabel("Number of clicks: 0");

		panel = new JPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(9, 9));



		frame.add(new SudokuPanel());
		frame.add(button);
		//frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sudoku");
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new GUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		count++;
		label.setText("Number of clicks: " + count);
	}
}

class SudokuPanel extends JPanel {
	private JTextField[] fields;

	public SudokuPanel() {
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setLayout(new GridLayout(9, 9, 2, 2));
		fields = new JTextField[81];
		for(JTextField j : fields) {
			j = new JTextField();
			this.add(j);
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
}
