package autocell.old;

import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Utils1 {
	public static JFrame launchFrame(final JComponent content) {
		return launchFrame(content, true);
	}
	public static JFrame launchFrame(final JComponent content, boolean undercorated) {
		JFrame ret = new JFrame("My frame");
		ret.setAlwaysOnTop(true);
		JPanel wrapper = new JPanel(new GridLayout(1, 1));
		wrapper.add(content);//wrapper.setBackground(Color.pink);				useless @haor
		ret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 //how to close
		ret.setContentPane(wrapper);
		ret.setSize(800, 600);
		ret.setLocationRelativeTo(null);
		try {
			ret.setUndecorated(undercorated);				//don't show the edge
			ret.setOpacity(0.3f);//ret.setBackground(new Color(0,0,0,0));				black
		}catch(Exception e){
			ret.setUndecorated(false);
		}
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