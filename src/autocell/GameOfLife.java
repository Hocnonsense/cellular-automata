package autocell;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import autocell.rules.*;

public class GameOfLife extends JPanel {
	private static final long serialVersionUID = 1L;
	final int height=70, width=132;
	int gwidth, gheight;
	public String filename = "data.txt";
	
	/** the environment of the game */
	Automata atm;	//the Automata can be changed, but better to built an abstract class
	public GameOfLife() {
		atm = new Automata(new Cells().initRandomCGrid(this.width, this.height, 0.1));
		addMouseListener(new MouseHandler()); addMouseMotionListener(new MouseMotionHandler());
	}
	public GameOfLife(Object atm) {
		this.atm = (Automata) atm;
		addMouseListener(new MouseHandler()); addMouseMotionListener(new MouseMotionHandler());
	}
	
	/** drow the graph */
	@SuppressWarnings("static-access")@Override protected void paintComponent(Graphics g) {
		g.fillRect(0, 0, getWidth(), getHeight());
		gwidth = getWidth() / this.width; gheight = getHeight() / this.height;
		for (int row = 0; row < this.width; ++row) {
			for (int col = 0; col < this.height; ++col) {
				g.setColor(atm.cgrid[row][col].c); g.fillRect(row * gwidth, col * gheight, gwidth, gheight);
			}
		}
	}
	
	/** make Cells reactive with mouse */
	@SuppressWarnings("static-access") private Cells getCell(int x, int y) throws Exception{
		x=x/gwidth; y=y/gheight;
		if (atm.cgrid[x][y].alive) {atm.cgrid[x][y] = new Cells(); }
		else {atm.cgrid[x][y] = new Cells(new Random()); }
		return atm.cgrid[x][y];
	}
	class MouseHandler extends MouseAdapter{
		@Override public void mouseClicked(MouseEvent e){
			if (e.getClickCount() >= 2) {try {getCell(e.getX(),e.getY()); } catch (Exception e1) {; }}
		}
		boolean played;
		public void mousePressed(MouseEvent e) {played = play; play = false; }
		public void mouseReleased(MouseEvent e) {play = played; }
	}
	public class MouseMotionHandler implements MouseMotionListener{
		int x,y;
		@Override public void mouseDragged(MouseEvent e) {
			int tmpx = e.getX()*width/getWidth(),tmpy = e.getY()*height/getHeight();
			if ((x != tmpx || y != tmpy)) {x=tmpx; y=tmpy; atm.EachCircle(); }
		}
		@Override public void mouseMoved(MouseEvent e) { 
			x = e.getX()*width/getWidth(); y = e.getY()*height/getHeight();
		}
	}


	/** the control settings */
	private boolean play =true;
	public boolean play(boolean reverse) {if (reverse) {this.play=!this.play; } return this.play; }
	
	@SuppressWarnings("static-access") public void restart() {
		atm.cgrid = new Cells().initRandomCGrid(width, height, 0.1);
	}

	@SuppressWarnings("static-access") public Cells[][] cGrid() {return atm.cgrid; }
	@SuppressWarnings("static-access") public Cells[][] cGrid(Cells[][] cgrid) {return (atm.cgrid = cgrid); }
	@SuppressWarnings("static-access") public void newCGrid() {
		atm.cgrid = new Cells().initRandomCGrid(this.width, this.height, 0.1);
	}
	public void tick() {if(play) {atm.EachCircle(); }}// else {System.out.print("/*hold on*/"); } }
}