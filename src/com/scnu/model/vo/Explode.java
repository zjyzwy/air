package com.scnu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public  class Explode extends SuperElement{
	
	private ImageIcon img;
	private int moveX;
	
	public Explode() {
		super();
	}

	public Explode(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		moveX=0;
	}
	
	public static Explode createExplode(int x,int y,int w,int h,String str) {
		String url="img/explode.png";
		return new Explode(x,y,w,h,new ImageIcon(url));
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void move() {moveX++;}

	@Override
	public void destroy() {
		if(moveX==8)
			setVisible(false);
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(),
				getX(), getY(),
				getX()+getW(), getY()+getH(),
				66*moveX, 0,
				66*(moveX+1), 66,
				null);
	}
	
}
