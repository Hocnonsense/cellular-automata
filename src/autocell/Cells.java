package autocell;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Cells {
	public boolean alive = false;
	public Color c = Color.BLACK;
	
	public Cells() {}
	public Cells(Random rdm) {
		this.alive = true;
		this.c=new Color(rdm.nextInt(256), rdm.nextInt(256), rdm.nextInt(256));
	}public Cells(Color c){
		this.alive=true;
		this.c=c;
	}
	public static Cells[][] loadCells(String name) throws Exception {
		@SuppressWarnings("resource")
		BufferedReader ir = new BufferedReader(new FileReader(name));
		String[] tempS = ir.readLine().split("\\s+");
		if(!tempS[0].startsWith(">"))throw new Exception("data illigial"); int width = Integer.valueOf(tempS[1]); int height = Integer.valueOf(tempS[2]);
		Cells[][] lcgrid = new Cells[width][height];
		for (int w = 0; w != width; ++w) {for (int h = 0; h != height; ++h) {lcgrid[w][h]=new Cells(); } }
		String tmpreadline;
		while ((tmpreadline= ir.readLine()) != null) {
			tempS = tmpreadline.replace("[","").replace("]", "").split("\\s+");
			String[] loci = tempS[0].split(","); int x = Integer.valueOf(loci[0]); int y = Integer.valueOf(loci[1]);
			String[] color = tempS[1].split(","); int r= Integer.valueOf(color[0]); int g= Integer.valueOf(color[1]); int b= Integer.valueOf(color[2]);
			lcgrid[x][y] = new Cells(new Color(r, g, b));
		}
		return lcgrid;
	}
	public static void saveCells(Cells[][] scgrid, File f) throws Exception {
		FileWriter fos = new FileWriter(f, false);
		int width = scgrid.length, height = scgrid[0].length; fos.write(">\t"+width+"\t"+height);
		for (int row = 0; row < width; ++row) {
			for (int col = 0; col < height; ++col) {
				Cells scell = scgrid[row][col];
				if (scell.alive) {
					fos.write("\r\n["+row+","+col+"]\t");
					fos.write("["+scell.c.getRed()+","+scell.c.getGreen()+","+scell.c.getBlue()+"]");
				}
			}
		}
		fos.close();
	}public static String saveNewCells(Cells[][] scgrid, String filename) throws Exception {
		int tmpnumber = 0; String newname = filename; File nf = new File(filename); 
		while (nf.exists()) {newname = filename.split("\\.")[0] + "(" + (++tmpnumber) + ").txt"; nf = new File(newname); }
		saveCells(scgrid, nf);
		return newname;
	}public static void saveAssigned(Cells[][] scgrid, String filename) throws Exception {
		File af = new File(filename); if(!af.exists()) {throw new Exception("file not exists"); }
		saveCells(scgrid, af);
	}public static void saveTmp(GameOfLife gol) throws Exception {
		File f = new File(gol.filename); if (!f.exists()) {f.createNewFile(); }
		saveCells(gol.cGrid(), f);
	}
	public Cells[][] initRandomCGrid(int width, int height, double percentAlive) {
		Cells[][] initcgrid = new Cells[width][height]; Random rdm = new Random();
		for (int row = 0; row != width; ++row) {
			for (int col = 0; col != height; ++col) {
				if (rdm.nextDouble() < percentAlive) {initcgrid[row][col] = new Cells(rdm); }
				else {initcgrid[row][col] = new Cells(); }
			}
		}
		return initcgrid;
	}
}