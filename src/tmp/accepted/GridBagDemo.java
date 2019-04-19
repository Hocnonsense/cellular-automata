package tmp.accepted;

import java.awt.*;
import javax.swing.*;

public class GridBagDemo extends JFrame {
	private static final long serialVersionUID = 2L;
 JButton j1 = new JButton("打开");
 JPanel j4= new JPanel(); String[] str = {"java笔记", "C#笔记"};
 JComboBox<?> j5 = new JComboBox<Object>(str);
 JTextField j6 = new JTextField(); JButton j7 = new JButton("清空");
 JList<?> j8 = new JList<Object>(str); JTextArea j9 = new JTextArea();
 GridBagLayout layout = new GridBagLayout();
 GridBagConstraints s= new GridBagConstraints();
 public void init() {
 j9.setBackground(Color.PINK);//为了看出效果，设置了颜色
 this.setLayout(layout);
 this.add(j1);this.add(j4);this.add(j5);this.add(j6);this.add(j7);this.add(j8);this.add(j9);
 //Must in order!
 //是用来控制添加进的组件的显示位置
 s.fill = GridBagConstraints.BOTH;//使组件完全填满其显示区域。
 s.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
 s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
 s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
 layout.setConstraints(j1, s);//设置组件
 s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
 s.weightx = 0;//不能为1，j4是占了4个格，并且可以横向拉伸/但是如果为1，后面行的列的格也会跟着拉伸,导致j7所在的列也可以拉伸/所以应该是跟着j6进行拉伸
 s.weighty=0;layout.setConstraints(j4, s);
 s.gridwidth=2;s.weightx = 0;s.weighty=0;layout.setConstraints(j5, s);
 s.gridwidth=4;s.weightx = 1;s.weighty=0;layout.setConstraints(j6, s);
 s.gridwidth=0;s.weightx = 0;s.weighty=0;layout.setConstraints(j7, s);
 s.gridwidth=2;s.weightx = 0;s.weighty=1;layout.setConstraints(j8, s);
 s.gridwidth=5;s.weightx = 0;s.weighty=1;layout.setConstraints(j9, s);
 }
	public GridBagDemo() {
		init();
 this.setSize(600,600);
 this.setVisible(true);
 }
}