package autocell.old;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Cells4 {
	boolean alive = false;
	Color c = Color.BLACK;
	
	Cells4() {/** default sets */}
	Cells4(Random rdm) {
		this.alive = true;
		this.c=new Color(rdm.nextInt(256), rdm.nextInt(256), rdm.nextInt(256));
	}Cells4(Color c){
		this.alive=true;
		this.c=c;
	}
	public static Cells4[][] loadCells(String name) throws Exception {
		@SuppressWarnings("resource")
		BufferedReader ir = new BufferedReader(new FileReader(name));
		String[] tempS = ir.readLine().split("\\s+"); //the first line contains messages
		//it shoud start with ">	_row_	_cal_"
		if(!tempS[0].startsWith(">"))throw new Exception("data illigial"); int width = Integer.valueOf(tempS[1]); int height = Integer.valueOf(tempS[2]);
		Cells4[][] lcgrid = new Cells4[width][height];
		for (int w = 0; w != width; ++w) {for (int h = 0; h != height; ++h) {lcgrid[w][h]=new Cells4(); } }
		String tmpreadline;
		while ((tmpreadline= ir.readLine()) != null) {
			tempS = tmpreadline.replace("[","").replace("]", "").split("\\s+");
			String[] loci = tempS[0].split(","); int x = Integer.valueOf(loci[0]); int y = Integer.valueOf(loci[1]);
			String[] color = tempS[1].split(","); int r= Integer.valueOf(color[0]); int g= Integer.valueOf(color[1]); int b= Integer.valueOf(color[2]);
			lcgrid[x][y] = new Cells4(new Color(r, g, b));
		}
		return lcgrid;
	}
	public static void saveCells(Cells4[][] scgrid, String filename) throws Exception {
		File f = new File(filename); if (!f.exists()) {f.createNewFile(); } FileWriter fos = new FileWriter(f, false);
		int width = scgrid.length, height = scgrid[0].length; fos.write(">\t"+width+"\t"+height);
		for (int row = 0; row < width; ++row) {
			for (int col = 0; col < height; ++col) {
				Cells4 scell = scgrid[row][col];
				if (scell.alive) {
					fos.write("\r\n["+row+","+col+"]\t");
					fos.write("["+scell.c.getRed()+","+scell.c.getGreen()+","+scell.c.getBlue()+"]");
				}
			}
		}
		fos.close();
	}public static String saveNewCells(Cells4[][] scgrid, String filename) throws Exception {
		int tmpnumber = 0; String newname = filename; File nf = new File(filename); 
		while (nf.exists()) {newname = filename.split("\\.")[0] + "(" + (++tmpnumber) + ").txt"; nf = new File(newname); }
		saveCells(scgrid, newname);
		return newname;
	}public static void saveAssigned(Cells4[][] scgrid, String filename) throws Exception {
		saveCells(scgrid, filename);
	}public static void saveTmp(GameOfLife4 gol) throws Exception {
		saveCells(gol.cgrid, gol.filename);
	}
	public Cells4[][] initRandomCGrid(int width, int height, double percentAlive) {
		Cells4[][] initcgrid = new Cells4[width][height]; Random rdm = new Random();
		for (int row = 0; row != width; ++row) {
			for (int col = 0; col != height; ++col) {
				if (rdm.nextDouble() < percentAlive) {initcgrid[row][col] = new Cells4(rdm); }
				else {initcgrid[row][col] = new Cells4(); }
			}
		}
		return initcgrid;
	}
}