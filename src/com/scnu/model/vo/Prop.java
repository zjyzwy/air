package com.scnu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;


import com.scnu.model.load.ElementLoad;

public class Prop extends SuperElement{

	private ImageIcon img;//ҽ�ư�ͼƬ
	
	private int speed;
	
 	public boolean flag;//�����ж�
 	
 	
 	
	public Prop(int x, int y, int w, int h, ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		this.speed=3;
		this.flag=true;
	}
	
	public static Prop CreateProp(String str) {
		String a[]=str.split(",");
		int x=(int)(Math.random()*360);
		int y=Integer.parseInt(a[3]);
		int w=Integer.parseInt(a[4]);
		int h=Integer.parseInt(a[5]);
		ImageIcon img=ElementLoad.getElementLoad()
				.getMap().get(a[0]);
		return new Prop(x, y, w, h, img);
	}

	@Override
	public void move() {
		//�����ƶ�
		setY(getY()+speed);
		//�����ж�
		if(getX()>=460) flag=false;
		if(getX()<=0)   flag=true;
		//�����ƶ�
		if(flag) {
			setX(getX()+speed);
		}
		else {
			setX(getX()-speed);
		}
		if(getY()>800) {
			setVisible(false);
		}
	}

	@Override
	public void destroy() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}
	
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
