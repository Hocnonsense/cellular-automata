package autocell.old;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.*;
import javax.swing.*;

public class AutoCell4 {
	GameOfLife4 life;
	Utils4 u;
	public AutoCell4() {
		life = new GameOfLife4();
		u=Utils4.launchFrame(life); // parent generation
		Executors.newScheduledThreadPool(10).scheduleAtFixedRate(life::tick, 0, 100, TimeUnit.MILLISECONDS);
	// scheduleAtFixedRate do the job for given relay
	}
	public JPanel controlGOC() {//failed to set the 
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(2,4,1,1));				//first fill the rows
		String[] str={"undecorated", "alwaysOnTop", "showClose", "alwaysShowClose"};
		JButton[] jb = new JButton[str.length];int i=0;
		for (String s : str) {
			jb[i] = new JButton(s);jb[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {Utils4.testFef(s); }
			});
			jp.add(jb[i]);
			++i;
		}
		JButton jb5=new JButton("palse");
		jb5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {life.palse(); }
		});
		JButton jb6=new JButton("save as");
		JComboBox<?> jcb = new JComboBox<Object>(getFiles());
		jb6.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })   
			@Override
			public void actionPerformed(ActionEvent e) {
				try {Cells4.saveNewCells(life.cgrid, life.filename); } catch (Exception ex) {ex.printStackTrace(); }
				jcb.setModel(new DefaultComboBoxModel(getFiles()));// = new JComboBox<Object>(getFiles());
			}
		});
		jcb.addActionListener(new ActionListener() {
			Object sitem = jcb.getSelectedItem();
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Cells4.saveCells(life.cgrid, sitem.toString());
					sitem = jcb.getSelectedItem();
					life.cgrid = Cells4.loadCells(sitem.toString());
				} catch (Exception e1) {e1.printStackTrace(); }
			}
		});
		JButton jb7=new JButton("delete");
		jb7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object ditem = jcb.getSelectedItem();
				try {
					life.cgrid = Cells4.loadCells(life.filename);
					File df = new File(ditem.toString()); df.delete();
				} catch (Exception e1) {e1.printStackTrace(); }
			}
		});
		jp.add(jb5); jp.add(jb6);
		jp.add(jcb); jp.add(jb7);
		return jp;
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
