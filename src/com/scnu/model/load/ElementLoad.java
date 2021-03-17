package com.scnu.model.load;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;


public class ElementLoad {
	private static ElementLoad load;
	private Properties pro;
	private Map<String, ImageIcon> map;
	private Map<String, List<String>> playMap;
	private List<String> gameList;  
	
	private ElementLoad() {
		pro=new Properties();
		map=new HashMap<>();
		playMap=new HashMap<>();
		gameList=new ArrayList<>();
	}
	
	public static synchronized ElementLoad getElementLoad() {
		if(load==null)
			load=new ElementLoad();
		return load;
	}
	//读取背景、敌机、Boss
	public void readGamePro() {
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/scnu/pro/GameRun.pro");
		pro.clear();
		try {
			pro.load(in);
			for(Object o:pro.keySet()) {
				String str=pro.getProperty(o.toString());
				gameList.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//读取主角配置
	public void readPlayPro() {
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("com/scnu/pro/play.pro");
		try {
			pro.clear();
			pro.load(in);
			List<String> list=new ArrayList<>();
			for(Object o:pro.keySet()) {
				String str=pro.getProperty(o.toString());
				list.add(str);
				playMap.put(o.toString(), list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//加载游戏中要用的所有图片
	public void readImgPro(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream("com/scnu/pro/mapA.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				map.put(o.toString(), new ImageIcon(url));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	public Map<String, ImageIcon> getMap() {
		return map;
	}
	public Map<String, List<String>> getPlayMap() {
		return playMap;
	}
	public List<String> getGameList() {
		return gameList;
	}
	
}
