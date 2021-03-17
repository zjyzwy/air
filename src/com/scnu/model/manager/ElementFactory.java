package com.scnu.model.manager;

import java.util.List;
import java.util.Map;

import com.scnu.model.load.ElementLoad;
import com.scnu.model.vo.Background;
import com.scnu.model.vo.Boss;
import com.scnu.model.vo.Enemy;
import com.scnu.model.vo.Player;
import com.scnu.model.vo.Prop;
import com.scnu.model.vo.SuperElement;

/**
 * 任务：依据参数不同，自动读取 资源，填充 vo对象数据，存储到 元素管理器
 * 工厂的作用：将比较复杂的 构造方式 进行封装
 */
public class ElementFactory {
	
	public static SuperElement elementFactory(String name) {
		Map<String, List<String>> playMap=
				ElementLoad.getElementLoad().getPlayMap();
		List<String> list=ElementLoad.getElementLoad().getGameList();
		List<String> tList;
		String str;
		
		//根据参数创建对应的游戏元素对象
		switch (name) {
		case "Player":
			tList=playMap.get(name);
			str=tList.get(0);
			return Player.createPlayer(str);
			
		case "Background":
			str=list.get(7);
			return Background.createBackgroud(str);
		//开始界面
		case "Start":
			str=list.get(6);
			return Background.createBackgroud(str);
		//暂停界面	
		case "Pause":
			str=list.get(5);
			return Background.createBackgroud(str);
		//结束界面	
		case "GameOver":
			str=list.get(4);
			return Background.createBackgroud(str);
			
		case "Boss":
			str=list.get(3);
			return Boss.CreateBoss(str);

		case "Enemy":
			str=list.get(2);
			return Enemy.createEnemy(str);
		//道具	
		case "Prop":
			str=list.get(0);
			return Prop.CreateProp(str);	
		}
		
		return null;
	}
}
