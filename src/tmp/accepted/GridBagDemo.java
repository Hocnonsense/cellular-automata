package tmp.accepted;

import java.awt.*;
import javax.swing.*;

public class GridBagDemo extends JFrame {
	private static final long serialVersionUID = 2L;
 JButton j1 = new JButton("��");
 JPanel j4= new JPanel(); String[] str = {"java�ʼ�", "C#�ʼ�"};
 JComboBox<?> j5 = new JComboBox<Object>(str);
 JTextField j6 = new JTextField(); JButton j7 = new JButton("���");
 JList<?> j8 = new JList<Object>(str); JTextArea j9 = new JTextArea();
 GridBagLayout layout = new GridBagLayout();
 GridBagConstraints s= new GridBagConstraints();
 public void init() {
 j9.setBackground(Color.PINK);//Ϊ�˿���Ч������������ɫ
 this.setLayout(layout);
 this.add(j1);this.add(j4);this.add(j5);this.add(j6);this.add(j7);this.add(j8);this.add(j9);
 //Must in order!
 //������������ӽ����������ʾλ��
 s.fill = GridBagConstraints.BOTH;//ʹ�����ȫ��������ʾ����
 s.gridwidth=1;//�÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
 s.weightx = 0;//�÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
 s.weighty=0;//�÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
 layout.setConstraints(j1, s);//�������
 s.gridwidth=0;//�÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
 s.weightx = 0;//����Ϊ1��j4��ռ��4���񣬲��ҿ��Ժ�������/�������Ϊ1�������е��еĸ�Ҳ���������,����j7���ڵ���Ҳ��������/����Ӧ���Ǹ���j6��������
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