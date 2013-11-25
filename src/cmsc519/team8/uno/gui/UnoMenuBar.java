package cmsc519.team8.uno.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


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
		
		//add game option menu
		JMenu menu = new JMenu("Game");
		
		//quit button
		final JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent) {
				for (Frame frame : Frame.getFrames())
				{
					if (frame.isActive())
					{
						WindowEvent windowClosing = new WindowEvent(
								frame, WindowEvent.WINDOW_CLOSING);
						frame.dispatchEvent(windowClosing);
					}
				}
			}
		});
		menu.add(quit);
		
		//help option
		JMenuItem help = new JMenuItem("Help");
		help.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	try{
	    		String location = "/cmsc519/team8/uno/data/UnoManual.txt";
	    	    BufferedReader br = new BufferedReader(
	    	    		new InputStreamReader(
	    	    				this.getClass().getResourceAsStream(location)));
	    	    JTextArea textArea = new JTextArea(40,50);
	    	    String line = br.readLine();
	    	    String newline = "\n";
	    	    textArea.setLineWrap( true );
	    	    textArea.setWrapStyleWord( true );
	    	    
	    	    while ((line = br.readLine()) != null) {  
	    	        if ( line.trim().length() == 0 ) {  
	    	          textArea.append(newline);
	    	        }
	    	        	textArea.append(line + newline);
	    	      }  
	    	    br.close();
	    		
	    	    JOptionPane.showMessageDialog(
	    	    		   null, textArea, "UNO Help", 
	    	    		   JOptionPane.INFORMATION_MESSAGE);
	        	}catch(IOException e){
	        		//do something if the file is not found
	        	}
	        }
		});
		
		add(menu);
		add(help);
		
	}

}
