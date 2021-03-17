package com.scnu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.scnu.model.load.ElementLoad;

public class Background extends SuperElement{
	
	private ImageIcon img;
	
	
	public Background() {
		super();
	}

	public Background(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
	}
	
	public static Background createBackgroud(String str) {
		String a[]=str.split(",");
		int x=Integer.parseInt(a[2]);
		int y=Integer.parseInt(a[3]);
		int w=Integer.parseInt(a[4]);
		int h=Integer.parseInt(a[5]);
		ImageIcon img=ElementLoad.getElementLoad()
				.getMap().get(a[0]);
		return new Background(x,y,w,h,img);
	}

	@Override
	public void move() {
		if(getY()>=getH()) {
			setY(0);
		}else{
			setY(getY()+1);
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void showElement(Graphics g) {
		//±³¾°¹ö¶¯
		g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
		g.drawImage(img.getImage(), getX(), getY()-getH(), getW(), getH(), null);
	}

}
