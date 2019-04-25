package autocell.old;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GameOfLife3 extends JPanel {
	private static final long serialVersionUID = 1L;
	private cells[][] cgrid;
	private int height=70;private int width=132;
	private String filename = "data.txt";
	
	public void load() {
		try {
			cgrid = new DataBase().loadCells(filename);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void save() {
		try {
			this.filename = new DataBase().saveCells(cgrid);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(filename);
	}

	public GameOfLife3() {
		this.cgrid = new DataBase().initRandomCGrid(this.width, this.height, 0.025);
	}public void tick() {
		while(palse) {System.out.print("nothing");}
		cells[][] nextGen = new cells[this.width][this.height];
		ArrayList<Color> al;
		for (int row = 0; row < this.width; row++) {
			for (int col = 0; col < this.height; col++) {
				cells ncell = new cells();
				al=neighbors(row, col);
				if (cgrid[row][col].alive) { // if parent cell is alive, check life around it
					if (al.size() == 2 || al.size() == 3) {ncell=cgrid[row][col]; }
					}
				else if (al.size() == 3) { // if parent cell is dead, check life around it
					ncell=new cells(al.get(new Random().nextInt(al.size())));
					}
				// role: (this.live && neighbor=(2 || 3)) || (this.die && neighbor=3) ==> next.live
				if (Math.random() < 0.0001) {
					if (Math.random() >= 0.0001) {ncell= new cells(new Random()); }
					else ncell = new cells();
					}
				nextGen[row][col]=ncell;
				}
			}
		cgrid = nextGen;
		}
	// calculate nextGen

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
	public void palse() {this.palse=!this.palse; }
}

class DataBase{
	public DataBase() {
		
	}

	public cells[][] initRandomCGrid(int width, int height, double percentAlive) {
		cells[][] initcgrid = new cells[width][height];
		Random rdm = new Random();
		for (int row = 0; row != width; ++row) {
			for (int col = 0; col != height; ++col) {
				cells acell=new cells();
				if (rdm.nextDouble() < percentAlive) {
					acell = new cells(rdm);
				}
				initcgrid[row][col] = acell;
			}
		}
		return initcgrid;
	}
	public cells[][] loadCells() throws Exception{
		return loadCells("data.txt");
	}
	public cells[][] loadCells(String name) throws Exception {
		@SuppressWarnings("resource")
		BufferedReader ir = new BufferedReader(new FileReader(name));
		String[] tempS = ir.readLine().split("\\s+"); //the first line contains messages
		//it shoud start with ">	_row_	_cal_"
		if(!tempS[0].startsWith(">"))throw new Exception("data illigial");
		int width = Integer.valueOf(tempS[1]); int height = Integer.valueOf(tempS[2]);
		cells[][] lcgrid = new cells[width][height];
		for (int w = 0; w != width; ++w) {for (int h = 0; h != height; ++h) {lcgrid[w][h]=new cells(); } }
		String tmpreadline;
		while ((tmpreadline= ir.readLine()) != null) {
			tempS = tmpreadline.replace("[","").replace("]", "").split("\\s+");
			//System.out.println(tempS[0]+", "+tempS[1]);
			String[] loci = tempS[0].split(",");
			//System.out.println(loci[0]+", "+loci[1]);
			int x = Integer.valueOf(loci[0]); int y = Integer.valueOf(loci[1]);
			String[] color = tempS[1].split(",");
			int r= Integer.valueOf(color[0]); int g= Integer.valueOf(color[1]); int b= Integer.valueOf(color[2]);
			lcgrid[x][y] = new cells(new Color(r, g, b));
			System.out.println(r+", "+g+", "+b);
		}
		return lcgrid;
	}
	public String saveCells(cells[][] scgrid) throws Exception {
		String name = "data.txt"; int tmpnumber = 0;
		File f = new File(name);
		while (f.exists()) {name = "data(" + (++tmpnumber) + ").txt"; f = new File(name); }
		f.createNewFile();
		FileWriter fos = new FileWriter(f);
		int width = scgrid.length, height = scgrid[0].length;
		fos.write(">\t"+width+"\t"+height);
		for (int row = 0; row < width; ++row) {
			for (int col = 0; col < height; ++col) {
				cells scell = scgrid[row][col];
				if (scell.alive) {
					fos.write("\r\n["+row+","+col+"]\t");
					fos.write("["+scell.c.getRed()+","+scell.c.getGreen()+","+scell.c.getBlue()+"]");
				}
			}
		}
		fos.close();
		return name;
	}
}
class cells {
	boolean alive = false;
	Color c = Color.BLACK;
	
	cells() {/** default sets */}
	cells(Random rdm) {
		this.alive = true;
		this.c=new Color(rdm.nextInt(256), rdm.nextInt(256), rdm.nextInt(256));
	}cells(Color c){
		this.alive=true;
		this.c=c;
	}
}