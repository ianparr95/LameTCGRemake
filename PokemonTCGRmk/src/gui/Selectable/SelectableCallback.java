package gui.Selectable;

import java.awt.event.MouseEvent;

import cardAbstract.Card;

public class SelectableCallback extends SelectedListener {

	private static SelectableGui sl;
	
	public SelectableCallback(Card c) {
		super(c);
	}
	
	public static void setSelectableGui(SelectableGui s) {
		sl = s;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (sl != null) {
			sl.onClick(this);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}


}
