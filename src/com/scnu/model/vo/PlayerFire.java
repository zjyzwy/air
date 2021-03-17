package com.scnu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.scnu.model.load.ElementLoad;

public class PlayerFire extends SuperElement{

	private ImageIcon img;
	private int speed;
	
	
	public PlayerFire() {
		super();
	}

	public PlayerFire(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		this.speed=40;
	}

	public static PlayerFire createPlayFire(int x,int y,String str) {
		ImageIcon img=ElementLoad.getElementLoad()
				.getMap().get(str);
		return new PlayerFire(x,y,20,30,img);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void move() {
		if(getY()>=0) {
			setY(getY()-speed);
		}
	}

	@Override
	public void destroy() {
		if(getY()<=0) {
			setVisible(false);
		}
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), getX(), getY(), null);
//		update();
	}

}
