package com.scnu.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.scnu.model.vo.EnemyFire;
import com.scnu.model.vo.SuperElement;
import com.scnu.model.load.ElementLoad;
import com.scnu.model.manager.ElementManager;
import com.scnu.thread.GameThread;

public class Enemy extends SuperElement{
	private ImageIcon img;
	private boolean attack;//¹¥»÷×´Ì¬
	private int speed;
	private String fireName;
	
	public Enemy() {
		super();
	}

	public Enemy(int x, int y, int w, int h,String fireName,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		this.attack=true;
		this.speed=10;
		this.fireName=fireName;
	}
	
	public static Enemy createEnemy(String str) {
		String a[]=str.split(",");
		int x=(int)(Math.random()*400);
		int y=Integer.parseInt(a[3]);
		int w=Integer.parseInt(a[4]);
		int h=Integer.parseInt(a[5]);
		
		ImageIcon img=ElementLoad
				.getElementLoad().getMap().get(a[0]+(int)(Math.random()*5));
		String fireName=a[1]+(int)(Math.random()*3);
		return new Enemy(x, y, w, h, fireName, img);
	}
	
	@Override
	public void update() {
		super.update();
		if(GameThread.time%20==0) {
			setAttack(true);
			Fire();
		}
	}
	//µÐ»ú»ðÁ¦
	public void Fire() {
		if(!attack) {//·Ç¹¥»÷×´Ì¬
			return;
		}
		List<SuperElement> list=
				ElementManager.getManager().getElementList("e");
		if(list==null) {
			list=new ArrayList<>();
		}
		ElementManager.getManager().getMap().put("e",list);
		list.add(EnemyFire.CreateFire(getX()+15, getY()+5, fireName));
		this.attack=false;
	}
	
	@Override
	public void move() {
		setY(getY()+speed);
	}

	@Override
	public void destroy() {
		if(!isVisible())Boom();
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), getX(), getY(),getW(),getH(), null);
	}
	
	public void Boom() {
		List<SuperElement> list=ElementManager
				.getManager().getElementList("d");
		list.add(Explode.createExplode(getX(), getY(), getW(), getH(), null));
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getFireName() {
		return fireName;
	}

	public void setFireName(String fireName) {
		this.fireName = fireName;
	}

	
}
