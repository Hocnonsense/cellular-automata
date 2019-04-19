package tmp.accepted;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OperateFrame{
	private static JFrame jf = new JFrame("Haor");
 JButton jb1 = new JButton("��");
 JPanel jp= new JPanel(); String[] str = {"java�ʼ�", "C#�ʼ�"};
 JComboBox<?> jcb = new JComboBox<Object>(str);
 JTextArea jtf = new JTextArea(); JButton jb2 = new JButton("���");
 JList<?> jl = new JList<Object>(str); JTextArea jta = new JTextArea();
 GridBagLayout gblayout = new GridBagLayout();
 GridBagConstraints gbcts= new GridBagConstraints();
	private void init() {
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("this window is selected");
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("this window is unselected");
			}
		});
		jta.addMouseListener(new MyMouseAdaptor());
		jf.add(jb1); jf.add(jp); jf.add(jcb); jf.add(jtf); jf.add(jb2); jf.add(jl); jf.add(jta);
		gbcts.fill = GridBagConstraints.BOTH;
		gbcts.gridwidth=1; gbcts.weightx = 0; gbcts.weighty=0; gblayout.setConstraints(jb1, gbcts);
		gbcts.gridwidth=0; gbcts.weightx = 0; gbcts.weighty=0; gblayout.setConstraints(jp, gbcts);
		gbcts.gridwidth=2; gbcts.weightx = 0; gbcts.weighty=0; gblayout.setConstraints(jcb, gbcts);
		gbcts.gridwidth=4; gbcts.weightx = 1; gbcts.weighty=0; gblayout.setConstraints(jtf, gbcts);
		gbcts.gridwidth=0; gbcts.weightx = 0; gbcts.weighty=0; gblayout.setConstraints(jb2, gbcts);
		gbcts.gridwidth=2; gbcts.weightx = 0; gbcts.weighty=1; gblayout.setConstraints(jl, gbcts);
		gbcts.gridwidth=5; gbcts.weightx = 0; gbcts.weighty=1; gblayout.setConstraints(jta, gbcts);
	}
	public OperateFrame() {
		jf.setLayout(gblayout);
		init();
		jf.setVisible(true);
		jf.setSize(200, 200);
	}
}
class MyMouseAdaptor extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		int c = e.getButton();// �õ����µ�����
		String mouseInfo = null;// ������Ϣ
		if (c == MouseEvent.BUTTON1)// �ж�������������
		{
			mouseInfo = "���";
		} else if (c == MouseEvent.BUTTON3) {// �ж�������Ҽ�����
			mouseInfo = "�Ҽ�";
		} else {
			mouseInfo = "����";
		}
		System.out.append("��굥����" + mouseInfo + ".\n");
	}
 
	public void mouseEntered(MouseEvent e)// ���������
	{
		System.out.append("���������.\n");
	}
 
	public void mouseExited(MouseEvent e)// ����˳����
	{
		System.out.append("����˳����.\n");
	}
 
	public void mousePressed(MouseEvent e)// ��갴��
	{
		System.out.append("��갴��.\n");
	}
 
	public void mouseReleased(MouseEvent e)// ����ɿ�
	{
		System.out.append("����ɿ�.\n");
	}
}