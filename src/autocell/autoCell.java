package autocell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;
import javax.swing.*;

public class autoCell {
	GameOfLife life;
	Utils u;
	public autoCell() {
		life = new GameOfLife();
		u=Utils.launchFrame(life); // parent generation
		Executors.newScheduledThreadPool(10).scheduleAtFixedRate(life::tick, 0, 100, TimeUnit.MILLISECONDS);
	// scheduleAtFixedRate do the job for given relay
	}
	public JPanel controlGOC() {//failed to set the 
		JPanel jp = new JPanel();
		JButton jb1=new JButton("undecorated");
		JButton jb2=new JButton("alwaysOnTop");
		JButton jb3=new JButton("showClose");
		JButton jb4=new JButton("alwaysShowClose");
		JButton jb5=new JButton("stop");
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {u.undecorated(); }
		});
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {u.alwaysOnTop(); }
		});
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {u.showClose(); }
		});
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {u.alwaysShowClose(); }
		});
		jb5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {life.stop(); }
		});
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		jp.add(jb4);
		jp.add(jb5);
		return jp;
	}
}
