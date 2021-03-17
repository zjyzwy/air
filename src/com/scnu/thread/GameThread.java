package com.scnu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.scnu.model.vo.Boss;
import com.scnu.model.vo.Player;
import com.scnu.model.manager.ElementManager;
import com.scnu.model.vo.SuperElement;

public class GameThread extends Thread{
	public static int Level;    //关卡数
	public static long time;
	public static long realTime;//以秒为单位的时间
	public static GameState state;
	
	static {
		Level=1;
		time=0;
		realTime=time/1000;
		state=GameState.Start;
	}
	
	public void run() {
		while(true) {
			//1.加载地图，英雄机
			loadElement();
			//2.显示任务地图（流程，自动化（移动，碰撞……））
			runGame();

			try {
				sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//载入游戏对象和初始化各游戏值
	private void loadElement() {
		if((ElementManager.getManager().getElementList("x").isEmpty())
				&&state==GameState.Start) {
			ElementManager.getManager().load();
			Level=1;
			time=0;
			Player.hp=10;
			Player.num=0;
		}
	}
	
	private void runGame() {

			if(!(ElementManager.getManager().getElementList("d").isEmpty())
					||state==GameState.Running) {
				
				Timer();
				ElementManager.getManager().runElement();
				stateUpdating();//更新游戏对象状态
				impactChecking();//碰撞检测
			}
	
			ElementManager.getManager().loadState();
			

	}
	
	//角色状态更新
	public void stateUpdating() {
		Map<String, List<SuperElement>> map=ElementManager
				.getManager().getMap();
		Set<String> set=map.keySet();
		List<String> keyList=new ArrayList<>();
		keyList.addAll(set);
		for(int i=0;i<keyList.size();i++) {
			String key=keyList.get(i);
			List<SuperElement> list=ElementManager
					.getManager().getElementList(key);
			for(int j=0;j<list.size();j++) {
				list.get(j).update();
				//如果游戏结束则清除游戏对象
				if(state==GameState.GameOvor)list.get(j).setVisible(false);
				if(!list.get(j).isVisible()) {
					list.remove(j--);
				}
			}
		}
	}
	
	//记录全局时间
	public void Timer() {
		time+=2;
		realTime=time/35;
	}
	
	//碰撞检测
	public void impactChecking() {
		//英雄机子弹
		List<SuperElement> list1=ElementManager
				.getManager().getElementList("c");
		//敌机
		List<SuperElement> list2=ElementManager
				.getManager().getElementList("f");
		//英雄机
		List<SuperElement> list3=ElementManager
				.getManager().getElementList("x");
		//Boss
		List<SuperElement> list4=ElementManager
				.getManager().getElementList("w");
		//Boss子弹
		List<SuperElement> list5=ElementManager
				.getManager().getElementList("g");
		//敌机子弹
		List<SuperElement> list6=ElementManager
				.getManager().getElementList("e");
		//道具
		List<SuperElement> list7=ElementManager
				.getManager().getElementList("h");
		
		check(list1, list2,CrashType.Util);   
		check(list3, list2, CrashType.Plane);
		check(list3, list6, CrashType.Plane);
		check(list3, list5, CrashType.Plane);
		check(list3, list7, CrashType.Prop);
		check(list1, list4, CrashType.Boss);
	}
	
	public void check(List<SuperElement> list1,List<SuperElement> list2, CrashType type) {
		for(int i=0;i<list1.size();i++) {
			for(int j=0;j<list2.size();j++) {
				if(list1.get(i).isCrash(list2.get(j))) {
					switch (type) {
						case Plane:
							//碰撞消失
							Player.hp--;
							if(Player.hp<=0) {
								list1.get(i).setVisible(false);
							}
							list2.get(j).setVisible(false);
						break;

						case Prop:
							//道具消失
							list2.get(j).setVisible(false);
							//道具效果
							Player.hp=(Player.hp+3>10)?10:Player.hp+3;
						break;
						
						case Boss:
							list1.get(i).setVisible(false);
							Boss.hp--;
							if(Boss.hp<=0) {
								list2.get(j).setVisible(false);
								Level++;//关卡增加
								Player.num+=500;//记分
								Boss.hp=100;
							}
						break;
						
						case Util:
							list1.get(i).setVisible(false);
							if(list2.get(j).isVisible()) {
								list2.get(j).setVisible(false);
								//记分
								Player.num+=50;
							}
						break;
					}		
				}
			}
		}
	}

	
	public enum CrashType{
		Plane,Prop,Boss,Util
	}
	//枚举游戏对象
	public enum GameState{
		Start,Running,Pause,GameOvor
	}
}

