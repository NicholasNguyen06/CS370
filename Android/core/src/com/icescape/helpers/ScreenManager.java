package com.icescape.helpers;

import java.util.Stack;
import com.icescape.game.AbstractScreen;

public class ScreenManager {

	public Stack<AbstractScreen> screens;
	
	public ScreenManager() {
		screens = new Stack<AbstractScreen>();
	}
	
}
