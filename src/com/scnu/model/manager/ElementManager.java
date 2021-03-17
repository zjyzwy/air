package com.scnu.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scnu.model.load.ElementLoad;
import com.scnu.model.vo.SuperElement;
import com.scnu.thread.GameThread;
import com.scnu.thread.GameThread.GameState;

public class ElementManager {
	private static ElementManager manager;
	private Map<String , List<SuperElement>> map;
	
	private ElementManager() {
		init();
	}
	
	public void init() {
		map=new HashMap<>();
		
		//地图
		map.put("a", new ArrayList<>());
		
		//英雄机子弹
		map.put("c", new ArrayList<>());
		
		//爆炸效果
		map.put("d", new ArrayList<>());
		
		//敌机子弹
		map.put("e", new ArrayList<>());
		
		//敌机
		map.put("f", new ArrayList<>());
		
		//Boss子弹
		map.put("g", new ArrayList<>());
		
		//道具
		map.put("h", new ArrayList<>());
		
		//Boss
		map.put("w", new ArrayList<>());
		
		//英雄机
		map.put("x", new ArrayList<>());
		
		//游戏状态
		map.put("z", new ArrayList<>());
	}
	
	
	//synchronized 提供线程保护锁
	public static synchronized ElementManager getManager() {
		if(manager==null)
			manager=new ElementManager();
		return manager;
	}
	
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readPlayPro();
		ElementLoad.getElementLoad().readGamePro();
		map.get("a").add(ElementFactory.elementFactory("Background"));
		loadPlayer();
	}
	
	public void loadPlayer() {
		map.get("x").add(ElementFactory.elementFactory("Player"));
	}
	
	public void loadEnemy() {
		map.get("f").add(ElementFactory.elementFactory("Enemy"));
	}
	
	
	
	public void loadBoss() {
		map.get("w").add(ElementFactory.elementFactory("Boss"));
	}
	
	public void loadProp() {
		map.get("h").add(ElementFactory.elementFactory("Prop"));
	}
	
	//根据游戏状态来加载对应的界面
	public void loadState() {
		if(!map.get("z").isEmpty()) map.get("z").remove(0);//把先前状态的界面先清除掉
		if(GameThread.state==GameState.Start) {
			map.get("z").add(ElementFactory.elementFactory("Start"));
		}else if(GameThread.state==GameState.Pause){
			map.get("z").add(ElementFactory.elementFactory("Pause"));
		}else if(ElementManager.getManager().getElementList("d").isEmpty()
				&&GameThread.state==GameState.GameOvor) {
			map.get("z").add(ElementFactory.elementFactory("GameOver"));
		}
	}
	//游戏流程
	public void runElement() {
		//敌机出现时间
		if(GameThread.time%(60/GameThread.Level)==0) {
			loadEnemy();
		}
		//道具出现时间
		if(GameThread.realTime%20==0
			&& manager.getElementList("h").isEmpty()) {
			loadProp();
		}
		//boss出现时间
		if(GameThread.realTime>=5+(GameThread.Level-1)*10
				&&ElementManager.getManager().getElementList("w").isEmpty()
				&&GameThread.Level<=3) {
			loadBoss();
		}
	}
	
	public Map<String, List<SuperElement>> getMap(){
		return map;
	}
	
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}
	
	
}
