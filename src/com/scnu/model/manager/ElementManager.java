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
		
		//��ͼ
		map.put("a", new ArrayList<>());
		
		//Ӣ�ۻ��ӵ�
		map.put("c", new ArrayList<>());
		
		//��ըЧ��
		map.put("d", new ArrayList<>());
		
		//�л��ӵ�
		map.put("e", new ArrayList<>());
		
		//�л�
		map.put("f", new ArrayList<>());
		
		//Boss�ӵ�
		map.put("g", new ArrayList<>());
		
		//����
		map.put("h", new ArrayList<>());
		
		//Boss
		map.put("w", new ArrayList<>());
		
		//Ӣ�ۻ�
		map.put("x", new ArrayList<>());
		
		//��Ϸ״̬
		map.put("z", new ArrayList<>());
	}
	
	
	//synchronized �ṩ�̱߳�����
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
	
	//������Ϸ״̬�����ض�Ӧ�Ľ���
	public void loadState() {
		if(!map.get("z").isEmpty()) map.get("z").remove(0);//����ǰ״̬�Ľ����������
		if(GameThread.state==GameState.Start) {
			map.get("z").add(ElementFactory.elementFactory("Start"));
		}else if(GameThread.state==GameState.Pause){
			map.get("z").add(ElementFactory.elementFactory("Pause"));
		}else if(ElementManager.getManager().getElementList("d").isEmpty()
				&&GameThread.state==GameState.GameOvor) {
			map.get("z").add(ElementFactory.elementFactory("GameOver"));
		}
	}
	//��Ϸ����
	public void runElement() {
		//�л�����ʱ��
		if(GameThread.time%(60/GameThread.Level)==0) {
			loadEnemy();
		}
		//���߳���ʱ��
		if(GameThread.realTime%20==0
			&& manager.getElementList("h").isEmpty()) {
			loadProp();
		}
		//boss����ʱ��
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
