package com.scnu.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.scnu.model.manager.ElementManager;
import com.scnu.model.vo.Player;
import com.scnu.model.vo.SuperElement;
import com.scnu.thread.GameThread.GameState;

public class GameListener implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		if(GameThread.state!=GameState.GameOvor) {
			List<SuperElement> list=ElementManager
					.getManager().getElementList("x");
			Player player=(Player)list.get(0);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				player.setUp(true);break;
			case KeyEvent.VK_LEFT:
				player.setLeft(true);break;
			case KeyEvent.VK_DOWN:
				player.setDown(true);break;
			case KeyEvent.VK_RIGHT:
				player.setRight(true);break;
			case KeyEvent.VK_SPACE:
				player.setAttack(true);break;
			default:
				break;
			}
		}
		
		//¼àÌýEnter¼ü¸Ä±äÓÎÏ·×´Ì¬
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			stateChange();
			System.out.println(GameThread.state);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(GameThread.state!=GameState.GameOvor) {
			List<SuperElement> list=ElementManager
					.getManager().getElementList("x");
			Player player=(Player)list.get(0);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				player.setUp(false);break;
			case KeyEvent.VK_LEFT:
				player.setLeft(false);break;
			case KeyEvent.VK_DOWN:
				player.setDown(false);break;
			case KeyEvent.VK_RIGHT:
				player.setRight(false);break;
			case KeyEvent.VK_SPACE:
				player.setAttack(false);
			default:
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public void stateChange() {
		switch (GameThread.state) {
		case Start:
			GameThread.state=GameState.Running;
			break;
		case Running:
			GameThread.state=GameState.Pause;
			break;
		case Pause:
			GameThread.state=GameState.Running;
			break;
		case GameOvor:
			GameThread.state=GameState.Start;
			break;
		default:
			break;
		}
	}
}
