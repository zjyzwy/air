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
 * �������ݲ�����ͬ���Զ���ȡ ��Դ����� vo�������ݣ��洢�� Ԫ�ع�����
 * ���������ã����Ƚϸ��ӵ� ���췽ʽ ���з�װ
 */
public class ElementFactory {
	
	public static SuperElement elementFactory(String name) {
		Map<String, List<String>> playMap=
				ElementLoad.getElementLoad().getPlayMap();
		List<String> list=ElementLoad.getElementLoad().getGameList();
		List<String> tList;
		String str;
		
		//���ݲ���������Ӧ����ϷԪ�ض���
		switch (name) {
		case "Player":
			tList=playMap.get(name);
			str=tList.get(0);
			return Player.createPlayer(str);
			
		case "Background":
			str=list.get(7);
			return Background.createBackgroud(str);
		//��ʼ����
		case "Start":
			str=list.get(6);
			return Background.createBackgroud(str);
		//��ͣ����	
		case "Pause":
			str=list.get(5);
			return Background.createBackgroud(str);
		//��������	
		case "GameOver":
			str=list.get(4);
			return Background.createBackgroud(str);
			
		case "Boss":
			str=list.get(3);
			return Boss.CreateBoss(str);

		case "Enemy":
			str=list.get(2);
			return Enemy.createEnemy(str);
		//����	
		case "Prop":
			str=list.get(0);
			return Prop.CreateProp(str);	
		}
		
		return null;
	}
}
