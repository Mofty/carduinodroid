package View;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI_Computer extends JFrame{
	
	public GUI_Computer(){
		
		//create programwindow
		this.initWindow();
		
		//window listener for closing
		this.addWindowListener(new WindowListener(){
			
			public void windowActivated(WindowEvent arg0) {
		
			}

			public void windowClosed(WindowEvent arg0) {
				
			}

			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
				
			}

			public void windowDeactivated(WindowEvent arg0) {
				
			}

			public void windowDeiconified(WindowEvent arg0) {
				
			}

			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//method: create window
	protected void initWindow(){
			
		//windowsize parameter 
		int window_width = 1024;
		int window_high = 768;
		
		//create programwindow
		JFrame Mainwindow = new JFrame("CarDuinoDroid");
		Mainwindow.setSize(window_width,window_high);
		Mainwindow.setLocationRelativeTo(null);
		Mainwindow.setBackground(Color.darkGray);
		
		//menubar
		JMenuBar Menubar = new JMenuBar();
		
		//menu "Datei" inlcuding menupoints
		JMenu File = new JMenu("Datei");
		JSeparator Separator_File = new JSeparator();
		JMenuItem Quit = new JMenuItem("Beenden");
		Quit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
		JMenuItem Connection = new JMenuItem("Verbindung");
		JMenuItem About = new JMenuItem("Über CarDuinoDroid");
		File.add(About);
		File.add(Connection);
		File.add(Separator_File);
		File.add(Quit);
		
		//menu "Einstellungen" including menupoints
		JMenu Preferences = new JMenu("Einstellungen");
		JCheckBoxMenuItem Light = new JCheckBoxMenuItem("Licht");
		//submenu "Kamera"
		JMenu Camera = new JMenu("Kamera");
		ButtonGroup Camerachoice = new ButtonGroup(); 
		JRadioButtonMenuItem Frontcamera = new JRadioButtonMenuItem("Frontkamera",true);
		JRadioButtonMenuItem Backcamera = new JRadioButtonMenuItem("Rückkamera");
		Camerachoice.add(Frontcamera);
		Camerachoice.add(Backcamera);
		Camera.add(Frontcamera);
		Camera.add(Backcamera);
		//menu "Einstellungen"
		Preferences.add(Camera);
		Preferences.add(Light);
		
		//menus allocate to menubar
		Menubar.add(File);
		Menubar.add(Preferences);
		
		//menubar allocate window
		Mainwindow.setJMenuBar(Menubar);
		
		//Live-Log screen
		JPanel Live_Log = new JPanel();
		Live_Log.setBackground(Color.white);
		Live_Log.add(new JLabel("Log"));
		JScrollPane Live_Log_Scrollbar = new JScrollPane(Live_Log, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Mainwindow.add(Live_Log_Scrollbar, BorderLayout.SOUTH);
		
		//informationbox
		JPanel informationbox = new JPanel();
		informationbox.setBackground(Color.green);
		Mainwindow.add(informationbox, BorderLayout.EAST);
		
		
		
		//make programwindow visible
		Mainwindow.setVisible(true);
	}
}