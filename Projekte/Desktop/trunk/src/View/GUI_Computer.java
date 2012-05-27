package View;

import Model.Log;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPanel;
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		ArrayList<String> Names = Language();
		
		//initiate Strings
		String resolution_list[] = {"320x240", "640x480", "720x576", "800x600", "1024x768", "1280x720", "1920x1080"};

		//initiate Menubar
		JMenuBar Menubar = new JMenuBar();
		
		//initiate Menus
		JMenu File = new JMenu(Names.get(0));
		JMenu Preferences = new JMenu(Names.get(4));
		JMenu Camera = new JMenu(Names.get(6));
		
		//initiate MenuItems
		JMenuItem Quit = new JMenuItem(Names.get(1));
		JMenuItem Connection = new JMenuItem(Names.get(2));
		JMenuItem About = new JMenuItem(Names.get(3));
		
		//initiate CheckBoxMenuItems
		JCheckBoxMenuItem Light = new JCheckBoxMenuItem(Names.get(5));
		
		//initiate RadioButtonMenuItems
		JRadioButtonMenuItem Frontcamera = new JRadioButtonMenuItem(Names.get(7),true);
		JRadioButtonMenuItem Backcamera = new JRadioButtonMenuItem(Names.get(8));
		
		//initiate Separators
		JSeparator Separator_File = new JSeparator();
	
		//initiate Buttons
		JButton map_button = new JButton(Names.get(9));
		
		//initiate ToggleButtons
		JToggleButton light_button = new JToggleButton(Names.get(5), false);
		JToggleButton up = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_up.gif"));
		JToggleButton down = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_down.gif"));
		JToggleButton left = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_left.gif"));
		JToggleButton right = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_right.gif"));
		
		//initiate ComboBoxes
		JComboBox resolution_change = new JComboBox(resolution_list);
		
		//initiate ButtonGroups
		ButtonGroup Camerachoice = new ButtonGroup(); 
		
		//initiate TextFilds
		JTextField Live_Log = new JTextField();
		
		//initiate ScrollPanes
		JScrollPane Live_Log_Scrollbar = new JScrollPane(Live_Log, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//initiate JLabels
		JLabel resolution_list_text = new JLabel(Names.get(10) + ": ");
		
		//initiate JPanels
		JPanel panel_video = new JPanel();
		JPanel panel_other = new JPanel();

		//edit Layout
		this.setLayout(new BorderLayout());
		this.getContentPane().add(panel_video, BorderLayout.CENTER);
		this.getContentPane().add(panel_other, BorderLayout.EAST);
		this.getContentPane().add(Live_Log, BorderLayout.SOUTH);
		panel_other.setPreferredSize(new Dimension(200,600));
		panel_other.setBackground(Color.lightGray);
		panel_other.setLayout(null);
		Live_Log.setPreferredSize(new Dimension(1024,100));
		
		//menu "file"
		Quit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
		File.add(About);
		File.add(Connection);
		File.add(Separator_File);
		File.add(Quit);
		
		//menu "Preferences" including submenus
		Camerachoice.add(Frontcamera);
		Camerachoice.add(Backcamera);
		Camera.add(Frontcamera);
		Camera.add(Backcamera);
		Preferences.add(Camera);
		Preferences.add(Light);
		
		//menus allocate to menubar
		Menubar.add(File);
		Menubar.add(Preferences);
		
		//menubar allocate window
		this.setJMenuBar(Menubar);

		//resolution_change
		resolution_change.setBounds(90, 5, 105, 20);
		resolution_list_text.setBounds(5, 5, 80, 20);
		panel_other.add(resolution_list_text);
		panel_other.add(resolution_change);
		
		//key feedback
		up.setBounds(130, 530, 30, 30);
		down.setBounds(130, 565, 30, 30);
		left.setBounds(95, 565, 30, 30);
		right.setBounds(165, 565, 30, 30);
		panel_other.add(up);
		panel_other.add(down);
		panel_other.add(left);
		panel_other.add(right);
		
		/*
		//Live-Log screen
		Live_Log.setBackground(Color.white);
		Live_Log_Scrollbar.setBounds(0, 610, 1024, 100);
		this.getContentPane().add(Live_Log_Scrollbar);
		
		//light button
		light_button.setBounds(750,570,120,30);
		this.getContentPane().add(light_button);
		
		//map button
		map_button.setBounds(880,570,120,30);
		this.getContentPane().add(map_button);
		
		*/
	}
	
	//method for read in language file (names of different elements)
	protected ArrayList<String> Language(){
		ArrayList<String> Language_Name = new ArrayList<String>();
		String Language = null;
		
		try {
			language_reader = new BufferedReader (new FileReader("src/View/language.txt"));
			Language = language_reader.readLine();
		} catch (FileNotFoundException e){
			Log.writelogfile("language.txt doesn't exist!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Language_Name = Language_name(Language);
		//language_reader.close();
		return Language_Name;
	}
	
	protected ArrayList<String> Language_name (String Language){
		ArrayList<String> Name = new ArrayList<String>();
		String Line = null;
		
		try {
			language_reader = new BufferedReader(new FileReader("src/View/languages/" + Language + ".txt"));
			while((Line = language_reader.readLine()) != null) Name.add(Line);
		} catch (FileNotFoundException e) {
			Name = Language_name("english");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Name;
	}
}