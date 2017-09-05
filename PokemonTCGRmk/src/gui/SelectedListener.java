package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import cardAbstract.Card;

public abstract class SelectedListener implements MouseListener {

	protected ClickableCardLabel label;
	protected Card c;
	
	public SelectedListener(Card c) {
		this.c = c;
	}
	
	public void setLabel(ClickableCardLabel label) {
		this.label = label;
	}

}
