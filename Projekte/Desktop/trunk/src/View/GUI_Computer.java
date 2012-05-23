package View;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI_Computer extends JFrame{
	
	public GUI_Computer(){
		this.getContentPane().setLayout(null);
		
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
				
			}

			public void windowOpened(WindowEvent arg0) {
								
			}
		});
	}
	
	//method: create window
	protected void initWindow(){
		
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
		this.setJMenuBar(Menubar);
		
		//Live-Log screen
		JTextField Live_Log = new JTextField();
		Live_Log.setBackground(Color.white);
		Live_Log.add(new JLabel("Log"));
		JScrollPane Live_Log_Scrollbar = new JScrollPane(Live_Log, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Live_Log_Scrollbar.setBounds(0, 610, 1024, 100);
		this.getContentPane().add(Live_Log_Scrollbar);
		
		//light button
		JToggleButton light_button = new JToggleButton("Licht", false);
		light_button.setBounds(750,570,120,30);
		this.getContentPane().add(light_button);
		
		//map button
		JButton map_button = new JButton("Karte öffnen");
		map_button.setBounds(880,570,120,30);
		this.getContentPane().add(map_button);
		
	}
}