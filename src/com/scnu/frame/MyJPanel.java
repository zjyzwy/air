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

		//游戏运行动画
		gameRunTime(g);
		
		Others(g);
		
		gameOver(g);
	}
	
	//绘制评价
	public void gameOver(Graphics g) {
		if(GameThread.state==GameState.GameOvor) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("宋体", Font.PLAIN, 30));
			g.drawString(""+Player.num, 280, 328);
			g.setFont(new Font("华文行楷", Font.PLAIN, 60));
			g.setColor(Color.red);
			if(Player.num<500) {
				g.drawString("菜", 220, 530);
			}
			if(Player.num>=500&&Player.num<1000) {
				g.drawString("勉强吧", 160, 530);
			}	
			if(Player.num>=1000&&Player.num<2000) {
				g.drawString("哎哟，不错哟", 65, 530);
			}
			if (Player.num>=2000&&Player.num<4000) {
				g.drawString("玩的很棒", 135, 530);
			}
			if(Player.num>=4000) {
				g.drawString("膜拜大神", 135, 530);
			}
		}
	}
	//绘制游戏时间，分数，主角血量，当前关卡数
	private void Others(Graphics g) {
		if(GameThread.state==GameState.Running
				|| GameThread.state==GameState.Pause) {
			//绘制游戏时间阴影
			g.setColor(Color.BLACK);
			g.setFont(new Font("黑体", Font.PLAIN, 20));
			g.drawString("时间："+GameThread.realTime+"秒", 2, 770);
			//绘制游戏时间
			g.setColor(Color.white);
			g.setFont(new Font("黑体",Font.PLAIN,20));
			g.drawString("时间："+GameThread.realTime+"秒",0,768);
			//绘制分数阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体",Font.PLAIN,20));
			g.drawString("分数："+Player.num,32,42);
			g.drawString("生命值：",182,42);
			//绘制分数
			g.setColor(Color.white);
			g.setFont(new Font("黑体",Font.PLAIN,20));
			g.drawString("分数："+Player.num,30,40);
			g.drawString("生命值：",180,40);
			//绘制当前关卡数阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体",Font.PLAIN,20));
			g.drawString("Level："+GameThread.Level,400,42);
			//绘制当前关卡数阴影
			g.setColor(Color.white);
			g.setFont(new Font("黑体",Font.PLAIN,20));
			g.drawString("Level："+GameThread.Level,402,40);
			//绘制空心血条方框
			g.setColor(new Color(190,195,199));
			g.drawRect(275,22,101,18);
			//绘制实心血条方框
			g.setColor(new Color(234,75,53));
			g.fillRect(276,22,Player.hp*10,17);
			//绘制血条数值阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体",Font.PLAIN,12));
			g.drawString(""+Player.hp*10, 317, 39);
			//绘制血条数值
			g.setColor(Color.white);
			g.setFont(new Font("黑体",Font.PLAIN,12));
			g.drawString(""+Player.hp*10, 315, 37);		
			//绘制血量过低警告
			if(Player.hp*10<=30){
				//绘制生命值过低提示阴影
				g.setColor(Color.black);
				g.setFont(new Font("黑体",Font.PLAIN,16));
				g.drawString("警告：生命值过低！", 197, 64);
				//绘制生命值过低提示
				g.setColor(Color.red);
				g.setFont(new Font("黑体",Font.PLAIN,16));
				g.drawString("警告：生命值过低！", 195, 62);
			}
		}
	}
	
	//绘制游戏元素
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
