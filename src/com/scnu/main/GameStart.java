package com.scnu.main;

import com.scnu.frame.MyJFrame;
import com.scnu.frame.MyJPanel;
import com.scnu.thread.GameListener;

public class GameStart {
	//程序执行的入口
	public static void main(String[] args) {
		MyJFrame jf=new MyJFrame();	
		MyJPanel jp=new MyJPanel();
		GameListener gameListener=new GameListener();
		jf.setKeyListener(gameListener);
		jf.addListener();
		jf.addJPanels();
		jf.setJp(jp);
		jf.addJPanels();
		jf.start();
	}

}
