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
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class GUI_Computer extends JFrame{
	
	static BufferedReader language_reader;
	
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
		
		//read in names of elements (dependently on languagefile)
		ArrayList<String> Names = Language_name();
		
		//menubar
		JMenuBar Menubar = new JMenuBar();
		
		//menu "Datei" inlcuding menupoints
		JMenu File = new JMenu(Names.get(0));
		JSeparator Separator_File = new JSeparator();
		JMenuItem Quit = new JMenuItem(Names.get(1));
		Quit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
		JMenuItem Connection = new JMenuItem(Names.get(2));
		JMenuItem About = new JMenuItem(Names.get(3));
		File.add(About);
		File.add(Connection);
		File.add(Separator_File);
		File.add(Quit);
		
		//menu "Einstellungen" including menupoints
		JMenu Preferences = new JMenu(Names.get(4));
		JCheckBoxMenuItem Light = new JCheckBoxMenuItem(Names.get(5));
		//submenu "Kamera"
		JMenu Camera = new JMenu(Names.get(6));
		ButtonGroup Camerachoice = new ButtonGroup(); 
		JRadioButtonMenuItem Frontcamera = new JRadioButtonMenuItem(Names.get(7),true);
		JRadioButtonMenuItem Backcamera = new JRadioButtonMenuItem(Names.get(8));
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
		JToggleButton light_button = new JToggleButton(Names.get(5), false);
		light_button.setBounds(750,570,120,30);
		this.getContentPane().add(light_button);
		
		//map button
		JButton map_button = new JButton(Names.get(9));
		map_button.setBounds(880,570,120,30);
		this.getContentPane().add(map_button);
		
		//key feedback
		JToggleButton up = new JToggleButton("^");
		JToggleButton down = new JToggleButton("u");
		JToggleButton left = new JToggleButton("<--");
		JToggleButton right = new JToggleButton("-->");
		up.setBounds(890, 440, 50, 50);
		down.setBounds(890, 500, 50, 50);
		left.setBounds(830, 500, 50, 50);
		right.setBounds(950, 500, 50, 50);
		this.getContentPane().add(up);
		this.getContentPane().add(down);
		this.getContentPane().add(left);
		this.getContentPane().add(right);
		
		//resolution_change
		String resolution_list[] = {"320x240", "640x480", "720x576", "800x600", "1024x768", "1280x720", "1920x1080"};
		JComboBox resolution_change = new JComboBox(resolution_list);
		resolution_change.setBounds(880, 10, 120, 30);
		JLabel resolution_list_text = new JLabel(Names.get(10) + ": ");
		resolution_list_text.setBounds(790, 10, 80, 30);
		this.getContentPane().add(resolution_list_text);
		this.getContentPane().add(resolution_change);
	}
	
	//method for read in language file (names of different elements)
	protected ArrayList<String> Language_name (){
		ArrayList<String> Name = new ArrayList<String>();
		String Line = null;
		String Language = null;
		
		try {
			language_reader = new BufferedReader (new FileReader("/Users/k0ng3n/Documents/workspace/CarDuinoDroid Java Programm/src/View/language.txt"));
			Language = language_reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			language_reader = new BufferedReader(new FileReader("/Users/k0ng3n/Documents/workspace/CarDuinoDroid Java Programm/src/View/" + Language + ".txt"));
			while((Line = language_reader.readLine()) != null) Name.add(Line);
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return Name;
	}
}