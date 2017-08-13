package gui;

import java.awt.event.MouseListener;

public abstract class ClickableCardLabel extends JMultilineLabel implements MouseListener {

	public ClickableCardLabel(String s) {
		super(s);
	}
	
}
