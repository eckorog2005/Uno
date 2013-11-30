package cmsc519.team8.uno.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	/**
	 * UID for main frame
	 */
	private static final long serialVersionUID = 4618955547537310901L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setTitle("UNO");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(800, 600);
		this.setLocation(dim.width/2-this.getSize().width/2, 
				dim.height/2-this.getSize().height/2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.GREEN.darker().darker().darker());
		contentPane = new UnoGamePanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//add menubar and game panel
		setJMenuBar(new UnoMenuBar());

	}

}
