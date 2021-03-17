package com.scnu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.scnu.model.load.ElementLoad;


public class EnemyFire extends SuperElement{

	private int speed;
	private ImageIcon img;
	
	public EnemyFire(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.speed=20;
		this.img=img;
	}
	
	public static EnemyFire CreateFire(int x,int y,String str) {
		ImageIcon img=ElementLoad.getElementLoad()
				.getMap().get(str);
		return new EnemyFire(x, y,10,25, img);
	}
	
	@Override
	public void move() {
		setY(getY()+speed);
	}
		

	@Override
	public void destroy() {
		if(getY()>800) 
			setVisible(false);	
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}

}
