package com.scnu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.scnu.model.load.ElementLoad;
import com.scnu.model.vo.BossFire;
import com.scnu.thread.GameThread;

public class BossFire extends SuperElement{

	private double speed;
	private double Move_num;
	private double speed_x;
	private double speed_y;
	private ImageIcon img;
	private int MoveX;
	private int type;
	
	public BossFire(int x,int y,int w,int h,ImageIcon img,int angle) {
		super(x,y,w,h);
		this.img=img;
		this.type=angle;
		this.speed=10;
		this.speed_x=speed*(Math.sin(angle));
		this.speed_y=speed*(Math.cos(angle));
		this.MoveX=0;
		this.Move_num=0;
	}
	
	public static BossFire CreateBossFire(int x,int y,int w,int h,int angle,String str) {
		ImageIcon img=ElementLoad.getElementLoad()
				.getMap().get(str);
		return new BossFire(x, y, w, h, img, angle);
	}
	
	@Override
	public void move() {
		switch (GameThread.Level) {
		case 1:
			setY((int)(getY()+speed_y));
			setX((int)(getX()+speed_x));	
			break;

		case 2:
			MoveX=(MoveX==0)?1:0;
			setY((int)(getY()+speed));	
			break;
			
		case 3:
			MoveX=(MoveX==2)?0:MoveX+1;
			setY((int)(getY()+speed));
			//方向判定
			if(type==0) {
				Move_num++;
				//左右移动
				if(Move_num<=15) {
					setX((int)(getX()+speed));
				}
				else if(Move_num>15&&Move_num<=30){
					setX((int)(getX()-speed));
				}
				if (Move_num==30) {
					Move_num=0;
				}
			}
			if (type==1) {
				Move_num++;
				//左右移动
				if(Move_num<=15) {
					setX((int)(getX()-speed));
				}
				else if(Move_num>15&&Move_num<=30){
					setX((int)(getX()+speed));
				}
				if (Move_num==30) {
					Move_num=0;
				}
			}
			break;
		}
			
	}

	@Override
	public void destroy() {
		if(getY()>800||getY()<0||getX()>600||getX()<0) {
			this.setVisible(false);
		}
	}

	@Override
	public void showElement(Graphics g) {
		switch (GameThread.Level) {
		case 1:
			g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);	
			break;

		case 2:
			g.drawImage(img.getImage(),
					getX(), getY(), 
					getX()+getW(),getY()+getH(),
					0+MoveX*135,0,
					135+MoveX*135,135,
					null);
			break;
			
		case 3:
			g.drawImage(img.getImage(),
					getX(), getY(), 
					getX()+getW(),getY()+getH(),
					0+MoveX*33,0,
					33+MoveX*33,80,
					null);
			break;
		}
		
		
	}

	public int getMoveX() {
		return MoveX;
	}

	public void setMoveX(int moveX) {
		MoveX = moveX;
	}
}
