package autocell;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import javax.swing.*;

public class Utils {
	private JFrame ret = new JFrame("My frame");
	private JLayeredPane lydp = new JLayeredPane(); 
	private JPanel wrapper = new JPanel(new GridLayout(1, 1));
	private JButton close = new JButton("close");
	
	public Utils(final JComponent content) {
		close.setBounds(100,100,70,40);
		close.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if (e.getSource() == close) {
					try {Cells.saveTmp((GameOfLife) content); } catch (Exception e1) {e1.printStackTrace(); }
					System.exit(0);
				}
			}
		});
		wrapper.add(content);
		ret.setLayeredPane(lydp); ret.setContentPane(wrapper);
		
		/** remember the Component setting after TestFef() */
		ret.addComponentListener(new ComponentListener() {@Override public void componentShown(ComponentEvent e) {; } @Override public void componentHidden(ComponentEvent e) {; }
			@Override public void componentResized(ComponentEvent e) {
				Dimension ds = e.getComponent().getSize();
				width = ds.width;  height = ds.height;
			}
			@Override public void componentMoved(ComponentEvent e) {
				Point pl = e.getComponent().getLocation();
				x = pl.x; y = pl.y;
			} 
		});
		
		/** the control settings */
		if (showClose) {
			if (alwaysShowClose) {lydp.add(close, JLayeredPane.MODAL_LAYER); } else {lydp.add(close); }
		} ret.setAlwaysOnTop(alwaysOnTop);
		ret.setUndecorated(undecorated);
		
		if (showClose || !alwaysShowClose) {ret.setSize(width, height); ret.setLocation(x, y); }
		else {if (undecorated) ret.setSize(1320, 700); else ret.setSize(280, 179); ret.setLocationRelativeTo(content); }
		try {ret.setOpacity(0.3f); } catch (Exception e) {; }
		ret.setVisible(true);
	}
	
	public static Utils launchFrame(final JComponent content) {
		Utils.content=content; Utils.u = new Utils(content);
		Thread t = new Thread(new Runnable(){
			@Override public void run(){
				while(true){
					content.repaint(); try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		t.setDaemon(true); t.start();
		return u;
	}

	private static JComponent content; private static Utils u;
	public static int x = 0, y = 530, width = 280, height = 179;
	private static boolean undecorated = false;
	private static boolean alwaysOnTop = false;
	private static boolean showClose = true;
	private static boolean alwaysShowClose = false;
	@SuppressWarnings("deprecation")
	public static boolean testFef(String methodname, boolean reverse) {
		Class<? extends Utils> uc = u.getClass();
		try{
			Field c = uc.getDeclaredField(methodname); if (reverse) {c.set(uc, !c.getBoolean(uc)); }
			Utils.u.ret.hide(); Utils.u = new Utils(content);
			return c.getBoolean(uc);
		} catch(Exception e) { System.out.println("wrong"); return false;}
	}
}