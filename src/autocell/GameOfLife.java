package autocell;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GameOfLife extends JPanel {
	private static final long serialVersionUID = 1L;
	private cells[][] cgrid;
	private int height=60;private int width=80;

	class cells {
		boolean alive = false;
		Color c = Color.BLACK;

		cells() {/** who cares */}
		cells(Random rdm) {
			this.alive = true;
			this.c=new Color(rdm.nextInt(256), rdm.nextInt(256), rdm.nextInt(256));
		}cells(Color c){
			this.alive=true;
			this.c=c;
		}
	}

	private void initRandomCGrid(int width, int height, double percentAlive) {
		cells[][] initcgrid = new cells[width][height];
		Random rdm = new Random();
		for (int w = 0; w != width; ++w) {
			for (int h = 0; h != height; ++h) {
				cells acell=new cells();
				if (rdm.nextDouble() < percentAlive) {
					acell = new cells(rdm);
				}
				initcgrid[w][h] = acell;
			}
		}
		this.cgrid = initcgrid;
	}

	public GameOfLife() {
		initRandomCGrid(this.width, this.height, 0.5);
	}
	
	@Override // 重构标记
	protected void paintComponent(Graphics g) { // container
		g.setColor(Color.white);
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

	// To redraw the graph
	private ArrayList<Color> neighbors(int row, int col) {
		ArrayList<Color> al=new ArrayList<Color>();cells cl;
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
	
	/**
	 * This method is called every second.
	 *
	 * It apply the Rules of Life.
	 */
	public void tick() {
		cells[][] nextGen = new cells[this.width][this.height];
		ArrayList<Color> al;
		for (int row = 0; row < this.width; row++) {
			for (int col = 0; col < this.height; col++) {
				cells ncell = new cells();
				al=neighbors(row, col);
				if (cgrid[row][col].alive) { // if parent cell is alive, check life around it
					if (al.size() == 2 || al.size() == 3) {
						ncell=cgrid[row][col];
					}
				} else if (al.size() == 3) { // if parent cell is dead, check life around it
					ncell=new cells(al.get(new Random().nextInt(al.size())));
				}
				// role: (this.live && neighbor=(2 || 3)) || (this.die && neighbor=3) ==> next.live
				if (Math.random() < 0.0001) {
					ncell= new cells(new Random());
				}
				nextGen[row][col]=ncell;
			}
		}
		cgrid = nextGen;
	}
	// calculate nextGen
}