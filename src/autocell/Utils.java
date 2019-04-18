package autocell;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Utils {
  public static JFrame launchFrame(final JComponent content) {
	 JFrame ret = new JFrame("My frame");
	 ret.setAlwaysOnTop(true);
	 content.setOpaque(false);		 // what ever is OK
	 JPanel wrapper = new JPanel(new GridLayout(1, 1));
	 wrapper.add(content);wrapper.setBackground(Color.PINK);
	 ret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 //how to close
	 ret.setContentPane(wrapper);
	 ret.setSize(800, 600);
	 ret.setLocationRelativeTo(null);
	 ret.setVisible(true);
	 Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
		  while(true){
			 content.repaint();
			 try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
				//Refresh the JFrame
		  }
		}
	 });
	 //建立一个repaint()的线程
	 t.setDaemon(true);
	 t.start();
	 return ret;
  }
}