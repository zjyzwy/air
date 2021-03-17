package com.scnu.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;

import com.scnu.model.vo.Player;
import com.scnu.model.manager.ElementManager;
import com.scnu.model.vo.SuperElement;
import com.scnu.thread.GameThread;
import com.scnu.thread.GameThread.GameState;

public class MyJPanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = -8392672648924499527L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		//��Ϸ���ж���
		gameRunTime(g);
		
		Others(g);
		
		gameOver(g);
	}
	
	//��������
	public void gameOver(Graphics g) {
		if(GameThread.state==GameState.GameOvor) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("����", Font.PLAIN, 30));
			g.drawString(""+Player.num, 280, 328);
			g.setFont(new Font("�����п�", Font.PLAIN, 60));
			g.setColor(Color.red);
			if(Player.num<500) {
				g.drawString("��", 220, 530);
			}
			if(Player.num>=500&&Player.num<1000) {
				g.drawString("��ǿ��", 160, 530);
			}	
			if(Player.num>=1000&&Player.num<2000) {
				g.drawString("��Ӵ������Ӵ", 65, 530);
			}
			if (Player.num>=2000&&Player.num<4000) {
				g.drawString("��ĺܰ�", 135, 530);
			}
			if(Player.num>=4000) {
				g.drawString("Ĥ�ݴ���", 135, 530);
			}
		}
	}
	//������Ϸʱ�䣬����������Ѫ������ǰ�ؿ���
	private void Others(Graphics g) {
		if(GameThread.state==GameState.Running
				|| GameThread.state==GameState.Pause) {
			//������Ϸʱ����Ӱ
			g.setColor(Color.BLACK);
			g.setFont(new Font("����", Font.PLAIN, 20));
			g.drawString("ʱ�䣺"+GameThread.realTime+"��", 2, 770);
			//������Ϸʱ��
			g.setColor(Color.white);
			g.setFont(new Font("����",Font.PLAIN,20));
			g.drawString("ʱ�䣺"+GameThread.realTime+"��",0,768);
			//���Ʒ�����Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����",Font.PLAIN,20));
			g.drawString("������"+Player.num,32,42);
			g.drawString("����ֵ��",182,42);
			//���Ʒ���
			g.setColor(Color.white);
			g.setFont(new Font("����",Font.PLAIN,20));
			g.drawString("������"+Player.num,30,40);
			g.drawString("����ֵ��",180,40);
			//���Ƶ�ǰ�ؿ�����Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����",Font.PLAIN,20));
			g.drawString("Level��"+GameThread.Level,400,42);
			//���Ƶ�ǰ�ؿ�����Ӱ
			g.setColor(Color.white);
			g.setFont(new Font("����",Font.PLAIN,20));
			g.drawString("Level��"+GameThread.Level,402,40);
			//���ƿ���Ѫ������
			g.setColor(new Color(190,195,199));
			g.drawRect(275,22,101,18);
			//����ʵ��Ѫ������
			g.setColor(new Color(234,75,53));
			g.fillRect(276,22,Player.hp*10,17);
			//����Ѫ����ֵ��Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����",Font.PLAIN,12));
			g.drawString(""+Player.hp*10, 317, 39);
			//����Ѫ����ֵ
			g.setColor(Color.white);
			g.setFont(new Font("����",Font.PLAIN,12));
			g.drawString(""+Player.hp*10, 315, 37);		
			//����Ѫ�����;���
			if(Player.hp*10<=30){
				//��������ֵ������ʾ��Ӱ
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.PLAIN,16));
				g.drawString("���棺����ֵ���ͣ�", 197, 64);
				//��������ֵ������ʾ
				g.setColor(Color.red);
				g.setFont(new Font("����",Font.PLAIN,16));
				g.drawString("���棺����ֵ���ͣ�", 195, 62);
			}
		}
	}
	
	//������ϷԪ��
	public void gameRunTime(Graphics g) {
		Map<String , List<SuperElement>> map=ElementManager
				.getManager().getMap();
		Set<String> set=map.keySet();
		List<String> keyList=new ArrayList<String>();
		keyList.addAll(set);
		for(int i=0;i<keyList.size();i++){
			String key=keyList.get(i);
			List<SuperElement> list=map.get(key);
			for(int j=0;j<list.size();j++) {
				list.get(j).showElement(g);
			}
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.repaint();
		}	
	}

}
