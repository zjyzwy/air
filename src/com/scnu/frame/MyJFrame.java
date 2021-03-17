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
	private ImageIcon img;//窗口
	

	public MyJFrame() {
		init();
	}
	public void init() {
		img=new ImageIcon("img/play/icon.png");
		this.setTitle("空战英豪");
		this.setSize(500, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setIconImage(img.getImage());//图标
	}
	
	//绑定监听
	public void addListener() {
		if(keyListener!=null)
			this.addKeyListener(keyListener);
		if(mouseListener!=null)
			this.addMouseListener(mouseListener);
		if(mouseMotionListener!=null)
			this.addMouseMotionListener(mouseMotionListener);
	}
	
	//绑定画板
	public void addJPanels() {
		if(jp!=null)
			this.add(jp);
	}
	
	//窗体启动
	public void start() {
		//线程启动
		GameThread gt=new GameThread();
		gt.start();
		//界面刷新线程启动
		if(jp instanceof Runnable) {
			new Thread((Runnable)jp).start();
		}
		//窗口可视
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
