package com.scnu.frame;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.scnu.thread.GameThread;

public class MyJFrame extends JFrame{			
	private static final long serialVersionUID = 5578067229156162837L;
	private KeyListener keyListener;
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	private JPanel jp;
	private ImageIcon img;//����
	

	public MyJFrame() {
		init();
	}
	public void init() {
		img=new ImageIcon("img/play/icon.png");
		this.setTitle("��սӢ��");
		this.setSize(500, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setIconImage(img.getImage());//ͼ��
	}
	
	//�󶨼���
	public void addListener() {
		if(keyListener!=null)
			this.addKeyListener(keyListener);
		if(mouseListener!=null)
			this.addMouseListener(mouseListener);
		if(mouseMotionListener!=null)
			this.addMouseMotionListener(mouseMotionListener);
	}
	
	//�󶨻���
	public void addJPanels() {
		if(jp!=null)
			this.add(jp);
	}
	
	//��������
	public void start() {
		//�߳�����
		GameThread gt=new GameThread();
		gt.start();
		//����ˢ���߳�����
		if(jp instanceof Runnable) {
			new Thread((Runnable)jp).start();
		}
		//���ڿ���
		this.setVisible(true);
	}
	
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	public void setJp(JPanel jp) {
		this.jp = jp;
	}
}
