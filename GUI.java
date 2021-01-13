import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
	private int count = 0;
	private JFrame frame;
	private JLabel label;
	private JPanel panel;

	public GUI() {
		frame = new JFrame();

		JButton button = new JButton("Click me");

		button.addActionListener(this);
		label = new JLabel("Number of clicks: 0");

		panel = new JPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));

		panel.add(button);
		panel.add(label);

		frame.add(panel, BorderLayout.CENTER);
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
