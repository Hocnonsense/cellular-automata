package autocell.old;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

import javax.swing.*;

public class Utils4 {
	private JFrame ret = new JFrame("My frame");
	private JLayeredPane lydp = new JLayeredPane();
	private JPanel wrapper = new JPanel(new GridLayout(1, 1));
	private JButton close = new JButton("close");
	private static JComponent content;
	private static Utils4 u;
	public Utils4(final JComponent content) {
		close.setBounds(100,100,70,40);
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == close) {
					System.exit(0);
				}
			}
		});
		wrapper.add(content);
		ret.setLayeredPane(lydp);
		ret.setContentPane(wrapper);
		if(undecorated) ret.setSize(1320, 700);else ret.setSize(280, 179);
		ret.setLocationRelativeTo(content);
		if(showClose) {
			if (alwaysShowClose) {lydp.add(close, JLayeredPane.MODAL_LAYER); }
			else {lydp.add(close); }
		}
		ret.setAlwaysOnTop(alwaysOnTop);
		ret.setUndecorated(undecorated);				//don't show the edge
		try {ret.setOpacity(0.4f);}	catch(Exception e){/* if undecorated == false, do nothing*/}
		ret.setVisible(true);
	}
	public static Utils4 launchFrame(final JComponent content) {
		Utils4.content=content;
		Utils4.u = new Utils4(content);
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				while(true){
					content.repaint();
					try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		t.setDaemon(true);
		t.start();
		return u;
	}
	
	private static boolean undecorated = false;
	private static boolean alwaysOnTop = false;
	private static boolean showClose = true;
	private static boolean alwaysShowClose = false;
	@SuppressWarnings("deprecation")
	public static void testFef(String methodname) {
		Class<? extends Utils4> uc = u.getClass();
		try{ Field c = uc.getDeclaredField(methodname); c.set(uc, !c.getBoolean(uc)); }
		catch(Exception e){ System.out.println("wrong");/*who cares*/
			}
		Utils4.u.ret.hide(); Utils4.u = new Utils4(content); 
	}
}