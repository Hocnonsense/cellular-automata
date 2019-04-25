package autocell;

import java.awt.*;
import javax.swing.*;
import autocell.rules.*;

public class GameOfLife extends JPanel {
	private static final long serialVersionUID = 1L;
	private int height=70;private int width=132;
	public String filename = "data.txt";
	
	@Override
	protected void paintComponent(Graphics g) {
		g.fillRect(0, 0, getWidth(), getHeight());
		int gwidth = getWidth() / this.width; int gheight = getHeight() / this.height;
		for (int row = 0; row < this.width; ++row) {
			for (int col = 0; col < this.height; ++col) {
				g.setColor(atm.cgrid[row][col].c); g.fillRect(row * gwidth, col * gheight, gwidth, gheight);
			}
		}
	}
	
	public void restart() {
		this.atm.cgrid = new Cells().initRandomCGrid(width, height, 0.1);
	}
	
	/** the environment of the game */
	Automata atm;
	public GameOfLife() { atm = new Automata(new Cells().initRandomCGrid(this.width, this.height, 0.1)); }
	public Cells[][] cGrid() {return atm.cgrid; }
	public Cells[][] cGrid(Cells[][] cgrid) {return (atm.cgrid = cgrid); }
	public void newCGrid() {atm.cgrid = new Cells().initRandomCGrid(this.width, this.height, 0.1); }
	public void tick() {if(play) {atm.EachCircle(); } else {System.out.print("/*hold on*/"); } }

	/** the control settings */
	private boolean play =true;
	public boolean play(boolean reverse) {if (reverse) {this.play=!this.play; } return this.play; }
}