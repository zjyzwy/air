package com.scnu.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.scnu.model.load.ElementLoad;
import com.scnu.model.manager.ElementManager;
import com.scnu.model.vo.BossFire;
import com.scnu.model.vo.SuperElement;
import com.scnu.thread.GameThread;


public class Boss extends SuperElement{

	public static int hp;
	private int speed;       //�ٶ�
	private ImageIcon img;
	private boolean flag=true; //�����ж�
	private boolean attack;    //�����ж�
	private String fireName;
	
	static {
		hp=100;
	}
	
	public Boss(int x,int y,int w,int h,String fireName,ImageIcon img) {
		super(x,y,w,h);
		this.speed=3;
		this.attack=true;
		this.img=img;
		this.fireName=fireName;
	}
	//Boss����
	public static Boss CreateBoss(String str) {
		//boss,�ӵ�����,x,y,w,h
		String  a[]=str.split(",");
		int x=Integer.parseInt(a[2]);
		int y=Integer.parseInt(a[3]);
		int w=Integer.parseInt(a[4]);
		int h=Integer.parseInt(a[5]);
		ImageIcon img=ElementLoad.getElementLoad()
				.getMap().get(a[0]+GameThread.Level);//��ͬ�ؿ���boss�ӵ����Ͳ�ͬ
		System.out.println(img);
		return new Boss(x, y, w, h, a[1]+GameThread.Level, img);
	}
	
	//Boss����Ϊ
	@Override
	public void update() {
		super.update();
		if(GameThread.time%100==0) {//boss������
			setAttack(true);
			Fire();
		}
	}
	
	//boss����
	public void Fire() {
		if(!attack)
			return;
		if (getY()>0) {//��boss���ֺ󿪻�
			List<SuperElement> list=
					ElementManager.getManager().getElementList("g");
			if(list==null) {
				list=new ArrayList<>();
			}
			ElementManager.getManager().getMap().put("g",list);
			//���ݹؿ��Ų�ͬ�ļ���
			switch (GameThread.Level) {
			case 1:
				for(int i=0;i<=180;i+=20) {		
					list.add(BossFire.CreateBossFire(getX()+getW()/2, getY()+getH()/2, 18, 34, i, fireName));
				}
				break;

			case 2:
				for(int i=0;i<4;i++) {
					list.add(BossFire.CreateBossFire(getX()-getW()*2+getW()*i, getY()+getH()/2, 30, 60, i, fireName));
				}
				break;
				
			case 3:
				for(int i=0;i<2;i++) {
					list.add(BossFire.CreateBossFire(getX()-getX()/2+getW()*i, getY()+getH()/2, 30, 75, i, fireName));
				}
				break;
			}
		}
	}
	
	@Override
	public void move() {
		//�����ж�
		if(getX()>=300) flag=false;
		if(getX()<=-20) flag=true;
		//�����ƶ�
		if(getY()<=80)
			setY(getY()+speed);
		//�����ƶ�
		if(getY()>=80) {
			if(flag) {
				setX(getX()+speed);
			}else {
				setX(getX()-speed);
			}
		}
		
	}

	@Override
	public void destroy() {
		if(!isVisible())Boom();
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}
	
	public void Boom() {
		List<SuperElement> list=ElementManager
				.getManager().getElementList("d");
		list.add(Explode.createExplode(getX(), getY(), getW(), getH(), null));
	}


	public static int getHp() {
		return hp;
	}
	
	public static void setHp(int hp) {
		Boss.hp = hp;
	}
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

}
