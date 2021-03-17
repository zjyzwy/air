package com.scnu.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class SuperElement {
	private int x;
	private int y;
	private int w;
	private int h;
	private int speed;
	private boolean visible;
	
	public SuperElement() {}
	
	public SuperElement(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.visible=true;
	}
	
	public boolean isCrash(SuperElement b) {
		Rectangle r1=new Rectangle(x, y, w, h);
		Rectangle r2=new Rectangle(b.x, b.y, b.w, b.h);
		return r1.intersects(r2);
	}
	
	public void update() {
		move();
		destroy();
	}

	public abstract void move();
	public abstract void destroy();
	public abstract void showElement(Graphics g);

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
