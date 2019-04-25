package autocell;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.*;
import javax.swing.*;

public class AutoCell {
	static GameOfLife life; Utils u;
	public AutoCell() {
		life = new GameOfLife(); u=Utils.launchFrame(life);
		Executors.newScheduledThreadPool(10).scheduleAtFixedRate(life::tick, 0, 100, TimeUnit.MILLISECONDS);
	}
	public JPanel controlGOL() {
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(2,4,1,1));
		String[] str={"undecorated", "alwaysOnTop", "showClose", "alwaysShowClose"};
		JButton[] jb = new JButton[str.length];
		for (int i : new int[]{0,1,2,3}) {	//those charactors described by str[]
			jb[i] = new JButton(str[i]);jb[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {setJButtonColor(jb[i], Utils.testFef(str[i], true)); }
			}); setJButtonColor(jb[i], Utils.testFef(str[i], false));
			jp.add(jb[i]);
		}
		JButton jb5=new JButton("play");
		jb5.addActionListener(new ActionListener() {	//the game play or palse
			@Override
			public void actionPerformed(ActionEvent e) {setJButtonColor(jb5, life.play(true)); }
		}); setJButtonColor(jb5, life.play(false));
		JButton jb6=new JButton("save as");
		JComboBox<?> jcb = new JComboBox<Object>(getFiles());
		jb6.addActionListener(new ActionListener() {	//create a new file to save current cgrid
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent e) {
				try {Cells.saveNewCells(life.cGrid(), life.filename); } catch (Exception ex) {ex.printStackTrace(); }
				jcb.setModel(new DefaultComboBoxModel(getFiles()));
			}
		});
		jcb.addActionListener(new ActionListener() {	//chose a file to loae
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (sitem.toString().equals(life.filename)) { Cells.saveTmp(life);}
					else {Cells.saveAssigned(life.cGrid(), sitem.toString()); }
				} catch (Exception e1) {e1.printStackTrace(); }
				sitem = jcb.getSelectedItem(); 
				try {life.cGrid(Cells.loadCells(sitem.toString())); } catch (Exception e1) {e1.printStackTrace(); }
			} Object sitem = jcb.getSelectedItem();
		});
		JButton jb7=new JButton("delete");	//delete current file
		jb7.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent e) {
				String currentfile = jcb.getSelectedItem().toString();
				if (currentfile.equals(life.filename)) {life.newCGrid(); }
				else {
					try {
						File df = new File(currentfile); df.delete(); life.cGrid(Cells.loadCells(life.filename));
						jcb.setModel(new DefaultComboBoxModel(getFiles()));
					} catch (Exception e1) {e1.printStackTrace(); }
				}
			}
		});
		jp.add(jb5); jp.add(jb6); jp.add(jcb); jp.add(jb7);
		return jp;
	}
	private void setJButtonColor(JButton jb, boolean isTrue) {
		if(isTrue) {jb.setBackground(Color.GREEN); } else {jb.setBackground(Color.PINK); }
	}
	private String[] getFiles() {
		String[] namelist = {}; String [] tmplist; String filename;
		File[] filelist = new File("C:\\Users\\Administrator\\Desktop\\Debug\\Eclipse\\autocells").listFiles();
		for (int i=0; i != filelist.length; ++i) {
			if ((filename = filelist[i].getName()).endsWith(".txt")) {
				tmplist = new String[namelist.length+1];
				for (int j=0; j != namelist.length; ++j) {tmplist[j] = namelist[j]; }
				tmplist[namelist.length]=filename; namelist = tmplist;
			}
		}
		return namelist;
	}
}