package com.scnu.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.scnu.model.load.ElementLoad;
import com.scnu.model.manager.ElementManager;
import com.scnu.thread.GameThread;
import com.scnu.thread.GameThread.GameState;

public class Player extends SuperElement{
	public static int hp;      //血量
	public static int num;     //分数
	private int moveX;
	private ImageIcon img;
	private boolean attack;
	private String fireName;

	private boolean up,right,down,left;//移动方向
	
	public Player() {}
	
	static {
		num=0;
		hp=10;
	}
	
	public Player(int x,int y,int w,int h,String fireName,ImageIcon img) {
		super(x,y,w,h);
		this.moveX=0;
		this.img=img;
		this.fireName=fireName;
	}
	
	public static Player createPlayer(String str) {
		String a[]=str.split(",");
		int x=Integer.parseInt(a[2]);
		int y=Integer.parseInt(a[3]);
		int w=Integer.parseInt(a[4]);
		int h=Integer.parseInt(a[5]);
		ImageIcon img=ElementLoad.getElementLoad()
				.getMap().get(a[0]);
//		String url="img/play/3.png";
		return new Player(x, y, w, h, a[1], img);
	}
	
	public void update() {
		super.update();
		addPlayerFire();
		updateImage();
	}
	
	@Override
	public void move() {
		if(up&&getY()>0)setY(getY()-10);
		if(down&&getY()+getH()<790)setY(getY()+10);
		if(left&&getX()>0)setX(getX()-10);
		if(right&&getX()+getW()<490)setX(getX()+10);
	}

	@Override
	public void destroy() {
		if(!isVisible()) {
			Boom();
			GameThread.state=GameState.GameOvor;
		}
	}

	
	public void updateImage() {
		moveX=(moveX==0)?1:0;
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(),
				getX(),getY(),
				getX()+getW(),getY()+getH(),
				60*moveX,0,         //图片左上角坐标
				60*(moveX+1),60,    //图片右下角坐标
				null);
	}
	
	public void addPlayerFire() {
		if(attack) {
			List<SuperElement> list1=ElementManager
					.getManager().getElementList("c");
			list1.add(PlayerFire.createPlayFire(getX(), getY(), fireName));
			list1.add(PlayerFire.createPlayFire(getX()+getW()-10, getY(), fireName));
			attack=false;
		}
	}
	
	public void Boom() {
		List<SuperElement> list=ElementManager
				.getManager().getElementList("d");
		list.add(Explode.createExplode(getX(), getY(), getW(), getH(), null));
	}

	public int getHp() {
		return hp;
	}


	public int getNum() {
		return num;
	}


	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	//设置飞机运动方向
	public void setUp(boolean up) {
		this.up = up;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	//攻击状态
	public boolean isAttack() {
		return attack;
	}
	public void setAttack(boolean attack) {
		this.attack = attack;
	}
}
