package autocell.rules;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import autocell.Cells;

public class Automata {
	public Cells[][] cgrid; public final int width, height;
	public Automata(Cells[][] cgrid) {
		this.cgrid = cgrid;
		width = cgrid.length; height = cgrid[0].length;
	}
	public void EachCircle() {
		Cells[][] nextGen = new Cells[this.width][this.height];
		ArrayList<Color> al;
		for (int row = 0; row < this.width; row++) {
			for (int col = 0; col < this.height; col++) {
				Cells ncell = new Cells();
				al=neighbors(row, col);
				if (cgrid[row][col].alive) {
					if (al.size() == 2 || al.size() == 3) {ncell=cgrid[row][col]; }
				} else if (al.size() == 3) {
					ncell=new Cells(al.get(new Random().nextInt(al.size())));
				}
				if (Math.random() < 0.0001) {
					if (Math.random() >= 0.0001) {ncell= new Cells(new Random()); }
					else ncell = new Cells();
				}
				nextGen[row][col]=ncell;
				}
			}
		cgrid = nextGen;
	}
	private ArrayList<Color> neighbors(int row, int col) {
		ArrayList<Color> al=new ArrayList<Color>();Cells cl;
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
}
