package tmp.accepted;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestJFrame extends JFrame{
	private static final long serialVersionUID = 7L;
	private SecondJFrame second = new SecondJFrame();
	public TestJFrame() {
		this.setLayout(new BorderLayout());
		final JTextField text = new JTextField("first field");
		this.add(text, BorderLayout.CENTER);
		JButton button = new JButton("改变另一个JFrame的值");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second.changeTextValue(text.getText());
				}
			});
		this.add(button, BorderLayout.SOUTH); 
		this.setSize(300, 100); this.setLocation(200, 200); this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0); }}); }
	}

class SecondJFrame extends JFrame {
	private static final long serialVersionUID = 8L;
	JTextField text = new JTextField("second field");
	public SecondJFrame() {
		this.setLayout(new BorderLayout());
		this.add(text, BorderLayout.CENTER); this.setSize(200, 100); this.setVisible(true);
		}
	public void changeTextValue(String newValue) {text.setText(newValue); }
	}