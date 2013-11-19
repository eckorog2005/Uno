package cmsc519.team8.uno.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UnoMenuBar extends JMenuBar {

	/**
	 * UID for JToolBar
	 */
	private static final long serialVersionUID = -665122809628935394L;


	/**
	 * Create the panel.
	 */
	public UnoMenuBar() {
		super();
		
		//add game option
		JMenu menu = new JMenu("Game");
		JMenuItem item = new JMenuItem("Help");
		add(menu);
		add(item);
	}

}
