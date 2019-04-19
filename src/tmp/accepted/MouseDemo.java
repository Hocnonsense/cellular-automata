package tmp.accepted;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.*;
 
public class MouseDemo {
	JFrame frame = new JFrame("draw rectangle");
	JButton j8= new JButton("clear");
	public MouseDemo(){
		EventQueue.invokeLater(()->{//new Runnable() {public void run(){//			单线程, 可以防止死锁
			MouseComponent mc = new MouseComponent();
			j8.add(mc);
			j8.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					mc.removeAll();
				}
			});
			frame.add(j8);
			frame.pack();
			frame.setVisible(true);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			});//}});//				same @haor
		}
	}
class MouseComponent extends JComponent{
	private static final long serialVersionUID = 4L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private static final int SIDELENGTH = 10;
	private ArrayList<Rectangle2D> squares;
	private Rectangle2D current; // the square containing the mouse cursor
	public MouseComponent(){
		squares = new ArrayList<>(); current = null;
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
		}
	public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;			//change g into Graphics2D
		for (Rectangle2D r : squares) g2.draw(r);
		}
	public Rectangle2D find(Point2D p) {
		for (Rectangle2D r : squares) {
			if (r.contains(p)) return r;
			}
		return null;
		}
	public void add(Point2D p) {
		double x = p.getX(); double y = p.getY();
		current = new Rectangle2D.Double(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
		squares.add(current);
		repaint();
		}
	public void remove(Rectangle2D s) {
		if (s == null) return; if (s == current) current = null;
		squares.remove(s);
		repaint();
		}
	public void removeAll() {
		squares.removeAll(squares);
	}
	private class MouseHandler extends MouseAdapter{
		public void mousePressed(MouseEvent e){ //鼠标按下时调用
			if (find(e.getPoint()) == null) add(e.getPoint());
			}
		public void mouseClicked(MouseEvent event){ //鼠标点击时调用，点击两次则移除矩形
			current = find(event.getPoint());
			if (current != null && event.getClickCount() >= 2) remove(current);
			}
		}
	private class MouseMotionHandler implements MouseMotionListener{
		public void mouseMoved(MouseEvent event){ //鼠标移动时调用
			if (find(event.getPoint()) == null) setCursor(Cursor.getDefaultCursor());
			else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		public void mouseDragged(MouseEvent event){ //鼠标按住拖动时调用
			if (current != null){
				int x = event.getX();
				int y = event.getY();
				current.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
				repaint();
				}
			}
		}
	}