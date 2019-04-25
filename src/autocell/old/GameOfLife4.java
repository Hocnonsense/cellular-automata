package autocell.old;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GameOfLife4 extends JPanel {
	private static final long serialVersionUID = 1L;
	private int height=70;private int width=132;
	Cells4[][] cgrid;
	public String filename = "data.txt";
	public GameOfLife4() {
		this.cgrid = new Cells4().initRandomCGrid(this.width, this.height, 0.1);//025);
	}public void tick() {
		while(palse) {System.out.print("nothing");}
		Cells4[][] nextGen = new Cells4[this.width][this.height];
		ArrayList<Color> al;
		for (int row = 0; row < this.width; row++) {
			for (int col = 0; col < this.height; col++) {
				Cells4 ncell = new Cells4();
				al=neighbors(row, col);
				if (cgrid[row][col].alive) { // if parent cell is alive, check life around it
					if (al.size() == 2 || al.size() == 3) {ncell=cgrid[row][col]; }
					}
				else if (al.size() == 3) { // if parent cell is dead, check life around it
					ncell=new Cells4(al.get(new Random().nextInt(al.size())));
					}
				// role: (this.live && neighbor=(2 || 3)) || (this.die && neighbor=3) ==> next.live
				if (Math.random() < 0.0001) {
					if (Math.random() >= 0.0001) {ncell= new Cells4(new Random()); }
					else ncell = new Cells4();
					}
				nextGen[row][col]=ncell;
				}
			}
		cgrid = nextGen;
		}
	// calculate nextGen

	// To redraw the graph
	private ArrayList<Color> neighbors(int row, int col) {
		ArrayList<Color> al=new ArrayList<Color>();Cells4 cl;
		cl = cgrid[(row+this.width-1)%this.width][(col+this.height-1)%this.height]; if (cl.alive) al.add(cl.c); 
		cl = cgrid[(row+this.width-1)%this.width][(col+this.height)%this.height]; if (cl.alive) al.add(cl.c); 
		cl = cgrid[(row+this.width-1)%this.width][(col+this.height+1)%this.height]; if (cl.alive) al.add(cl.c); 
		cl = cgrid[(row+this.width)%this.width][(col+this.height-1)%this.height]; if (cl.alive) al.add(cl.c); 
		cl = cgrid[(row+this.width)%this.width][(col+this.height+1)%this.height]; if (cl.alive) al.add(cl.c); 
		cl = cgrid[(row+this.width+1)%this.width][(col+this.height-1)%this.height]; if (cl.alive) al.add(cl.c); 
		cl = cgrid[(row+this.width+1)%this.width][(col+this.height)%this.height]; if (cl.alive) al.add(cl.c); 
		cl = cgrid[(row+this.width+1)%this.width][(col+this.height+1)%this.height]; if (cl.alive) al.add(cl.c); 
		return al;
	}
	
	@Override // 重构标记
	protected void paintComponent(Graphics g) { // container
		//g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight()); // black background
		// *check tool*/System.out.println(getWidth()+"=weight,height="+getHeight());
		int gwidth = getWidth() / this.width; int gheight = getHeight() / this.height; // each grid
		for (int row = 0; row < this.width; ++row) {
			for (int col = 0; col < this.height; ++col) {
				g.setColor(cgrid[row][col].c);
				g.fillRect(row * gwidth, col * gheight, gwidth, gheight);			 //Draw each grid
				}
			}
		}
	private boolean palse =false;
	public boolean palse() {this.palse=!this.palse; return this.palse; }
	public void palse(boolean ifpalse) {this.palse = ifpalse; }
}