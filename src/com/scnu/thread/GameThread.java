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
	public static int Level;    //�ؿ���
	public static long time;
	public static long realTime;//����Ϊ��λ��ʱ��
	public static GameState state;
	
	static {
		Level=1;
		time=0;
		realTime=time/1000;
		state=GameState.Start;
	}
	
	public void run() {
		while(true) {
			//1.���ص�ͼ��Ӣ�ۻ�
			loadElement();
			//2.��ʾ�����ͼ�����̣��Զ������ƶ�����ײ��������
			runGame();

			try {
				sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//������Ϸ����ͳ�ʼ������Ϸֵ
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
				stateUpdating();//������Ϸ����״̬
				impactChecking();//��ײ���
			}
	
			ElementManager.getManager().loadState();
			

	}
	
	//��ɫ״̬����
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
				//�����Ϸ�����������Ϸ����
				if(state==GameState.GameOvor)list.get(j).setVisible(false);
				if(!list.get(j).isVisible()) {
					list.remove(j--);
				}
			}
		}
	}
	
	//��¼ȫ��ʱ��
	public void Timer() {
		time+=2;
		realTime=time/35;
	}
	
	//��ײ���
	public void impactChecking() {
		//Ӣ�ۻ��ӵ�
		List<SuperElement> list1=ElementManager
				.getManager().getElementList("c");
		//�л�
		List<SuperElement> list2=ElementManager
				.getManager().getElementList("f");
		//Ӣ�ۻ�
		List<SuperElement> list3=ElementManager
				.getManager().getElementList("x");
		//Boss
		List<SuperElement> list4=ElementManager
				.getManager().getElementList("w");
		//Boss�ӵ�
		List<SuperElement> list5=ElementManager
				.getManager().getElementList("g");
		//�л��ӵ�
		List<SuperElement> list6=ElementManager
				.getManager().getElementList("e");
		//����
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
							//��ײ��ʧ
							Player.hp--;
							if(Player.hp<=0) {
								list1.get(i).setVisible(false);
							}
							list2.get(j).setVisible(false);
						break;

						case Prop:
							//������ʧ
							list2.get(j).setVisible(false);
							//����Ч��
							Player.hp=(Player.hp+3>10)?10:Player.hp+3;
						break;
						
						case Boss:
							list1.get(i).setVisible(false);
							Boss.hp--;
							if(Boss.hp<=0) {
								list2.get(j).setVisible(false);
								Level++;//�ؿ�����
								Player.num+=500;//�Ƿ�
								Boss.hp=100;
							}
						break;
						
						case Util:
							list1.get(i).setVisible(false);
							if(list2.get(j).isVisible()) {
								list2.get(j).setVisible(false);
								//�Ƿ�
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
	//ö����Ϸ����
	public enum GameState{
		Start,Running,Pause,GameOvor
	}
}

