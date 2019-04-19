package autocell.old;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Utils2 {
	private static JFrame ret = new JFrame("My frame");
	private JLayeredPane lydp = new JLayeredPane();
	private JPanel wrapper = new JPanel(new GridLayout(1, 1));
	private JButton close = new JButton("close");
	public Utils2(final JComponent content, boolean undecorated) {
		close.setBounds(100,100,70,40);
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == close) {System.exit(0);}
			}
		});/**/
		//ret.setDefaultCloseOperation(3);//JFrame.EXIT_ON_CLOSE);//		 how to close, the same
		lydp.add(close);//,JLayeredPane.MODAL_LAYER);//				or to show the button
		wrapper.add(content);
		ret.setLayeredPane(lydp);
		ret.setContentPane(wrapper);
		ret.setAlwaysOnTop(false);
		ret.setSize(1316, 739);//262,146);//
		ret.setLocationRelativeTo(content);
		ret.setUndecorated(undecorated);				//don't show the edge
		try {ret.setOpacity(0.3f);}	catch(Exception e){/* if undecorated == false, do nothing*/}
		ret.setVisible(true);
	}
	public static void launchFrame(final JComponent content) {
		launchFrame(content, true);				// transparent if true
	}
	public static void launchFrame(final JComponent content, boolean undecorated) {
		new Utils2(content, undecorated);
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				while(true){
					content.repaint();
					try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
					//Refresh the JFrame
					//System.out.println(content.getWidth()+" = width, height = "+content.getHeight());
				}
			}
		});
		//建立一个repaint()的线程
		t.setDaemon(true);				//将当前进程变成后台进程
		t.start();
	}
}