package MyUtil;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.*;

public class OperateFrame{
	private static JFrame jf = new JFrame("Haor");
	JButton jb1 = new JButton("打开");
	JPanel jp= new JPanel(); String[] str = {"part 1", "part 2"};
	JComboBox<?> jcb = new JComboBox<Object>(str);
	JTextField jtf = new JTextField(); JButton jb2 = new JButton("清空");
	JList<?> jl = new JList<Object>(str);
	GridBagLayout gblayout = new GridBagLayout();
	GridBagConstraints gbcts= new GridBagConstraints();
	private void adaptor() {
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("this window is selected");
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("this window is unselected");
			}
		});
	}
	private void layout() {
		jf.add(jb1); jf.add(jp); jf.add(jcb); jf.add(jtf); jf.add(jb2); jf.add(jl);
		gbcts.fill = GridBagConstraints.BOTH;
		gbcts.gridwidth=1; gbcts.weightx = 0; gbcts.weighty=0; gblayout.setConstraints(jb1, gbcts);
		gbcts.gridwidth=0; gbcts.weightx = 0; gbcts.weighty=0; gblayout.setConstraints(jp, gbcts);
		gbcts.gridwidth=2; gbcts.weightx = 0; gbcts.weighty=0; gblayout.setConstraints(jcb, gbcts);
		gbcts.gridwidth=4; gbcts.weightx = 1; gbcts.weighty=0; gblayout.setConstraints(jtf, gbcts);
		gbcts.gridwidth=0; gbcts.weightx = 0; gbcts.weighty=0; gblayout.setConstraints(jb2, gbcts);
		gbcts.gridwidth=2; gbcts.weightx = 0; gbcts.weighty=1; gblayout.setConstraints(jl, gbcts);
		jf.pack();
	}
	public OperateFrame() {
		new OperateFrame(new MouseComponent());
	}
	public OperateFrame(JComponent jc) {
		EventQueue.invokeLater(()->{
			jf.setLayout(gblayout);
			adaptor();
			layout();
			jf.add(jc);
			gbcts.gridwidth=5; gbcts.weightx = 0; gbcts.weighty=1; gblayout.setConstraints(jc, gbcts);
			jf.setVisible(true);
			jf.setSize(200, 200);
		});
	}
}
class MouseComponent extends JComponent{
	private static final long serialVersionUID = 6L;
	private static final int SIDELENGTH = 10;
	private ArrayList<Rectangle2D> squares;
	private Rectangle2D current; // the square containing the mouse cursor
	public MouseComponent(){
		squares = new ArrayList<>(); current = null;
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
		current=new Rectangle2D.Double(500, 500, SIDELENGTH, SIDELENGTH);
		squares.add(current);
		}
	public Dimension getPreferredSize() { return new Dimension(getWidth(), getHeight()); }
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;			//change g into Graphics2D
		for (Rectangle2D r : squares) g2.draw(r);
		}
	public Rectangle2D find(Point2D p) {
		for (Rectangle2D r : squares) {if (r.contains(p)) return r; }
		return null;
		}
	public void add(Point2D p){
		double x = p.getX(); double y = p.getY();
		current = new Rectangle2D.Double(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
		squares.add(current);
		repaint();
		}
	public void remove(Rectangle2D s){
		if (s == null) return; if (s == current) current = null;
		squares.remove(s);
		repaint();
		}
	public class MouseHandler extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e){ //鼠标按下时调用
			if (find(e.getPoint()) == null) add(e.getPoint());
			}
		@Override
		public void mouseClicked(MouseEvent event){ //鼠标点击时调用，点击两次则移除矩形
			current = find(event.getPoint());
			if (current != null && event.getClickCount() >= 2) remove(current);
			}
		}
	public class MouseMotionHandler implements MouseMotionListener{
		@Override
		public void mouseMoved(MouseEvent event){ //鼠标移动时调用
			if (find(event.getPoint()) == null) setCursor(Cursor.getDefaultCursor());
			else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		@Override
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