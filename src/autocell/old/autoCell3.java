package autocell.old;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import javax.swing.*;

public class autoCell3 {
	GameOfLife3 life;
	Utils3 u;
	public autoCell3() {
		life = new GameOfLife3();
		u=Utils3.launchFrame(life); // parent generation
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
				public void actionPerformed(ActionEvent e) {Utils3.testFef(s); }
			});
			jp.add(jb[i]);
			++i;
		}
		JButton jb5=new JButton("palse");
		jb5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {life.palse(); }
		});
		JButton jb6=new JButton("load");
		jb6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {life.load(); }
		});
		JButton jb7=new JButton("save");
		jb7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {life.save(); }
		});
		jp.add(jb5);jp.add(jb6);jp.add(jb7);
		return jp;
	}
}
