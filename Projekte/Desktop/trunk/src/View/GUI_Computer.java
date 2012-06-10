package View;

import Controller.*;
import Model.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class GUI_Computer extends JFrame{
	
	static BufferedReader language_reader;
	Log log;
	Controller_Computer controller_Computer;
	public JTextArea Live_Log;
	
	public GUI_Computer(){
		this.getContentPane().setLayout(null);
		log  = new Log(this);
		controller_Computer = new Controller_Computer(log);
		
		//create programwindow
		this.initWindow();
				
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(800,600));
		this.setTitle("CarDuinoDroid");
		
		//window listener for closing
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				log.writelogfile("CarDuinoDroid closed.");
				System.exit(0);
			}
			public void windowClosing(WindowEvent arg0) {
				log.writelogfile("CarDuinoDroid closed.");
				System.exit(0);
			}
		});
	}
	
	//method: create window
	private void initWindow(){
		
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
		JMenuItem Connect = new JMenuItem(Names.get(20));
		JMenuItem About = new JMenuItem(Names.get(3));
	
		//initiate RadioButtonMenuItems
		JRadioButtonMenuItem Frontcamera = new JRadioButtonMenuItem(Names.get(7),true);
		JRadioButtonMenuItem Backcamera = new JRadioButtonMenuItem(Names.get(8));
		
		//initiate Separators
		JSeparator Separator_File = new JSeparator();
	
		//initiate Buttons
		JButton map_button = new JButton(Names.get(9));
		JButton signal_button = new JButton(Names.get(13));
		
		//initiate ToggleButtons
		JToggleButton light_button = new JToggleButton(Names.get(5), false);
		JToggleButton soundrecord_button = new JToggleButton(Names.get(14), false);
		JToggleButton up = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_up.gif"));
		JToggleButton down = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_down.gif"));
		JToggleButton left = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_left.gif"));
		JToggleButton right = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_right.gif"));
		
		//initiate ComboBoxes
		JComboBox resolution_change = new JComboBox(resolution_list);
		
		//initiate ButtonGroups
		ButtonGroup Camerachoice = new ButtonGroup(); 
		
		//initiate TextAreas
		Live_Log = new JTextArea();
		
		//initiate ScrollPanes
		JScrollPane Live_Log_Scrollbar = new JScrollPane(Live_Log, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//initiate JSliders
		JSlider speed_slider = new JSlider(1, 100, 20);
		JSlider angle_slider = new JSlider(1, 100, 50);
		
		//initiate JLabels
		JLabel resolution_list_text = new JLabel(Names.get(10) + ": ");
		JLabel gps_coordinates_text = new JLabel(Names.get(11) + ": ");
		JLabel connection_type_text = new JLabel(Names.get(2) + ": ");
		JLabel latitude_text = new JLabel(Names.get(17) + ": ");
		JLabel longitude_text = new JLabel(Names.get(18) + ": ");
		JLabel speed_label = new JLabel(Names.get(15) + ": ");
		JLabel angle_label = new JLabel(Names.get(16) + ": ");
		JLabel connection_type = new JLabel("W-Lan");
		JLabel latitude = new JLabel("50.687222");
		JLabel longitude = new JLabel("10.914167");
		JLabel present_ip_text = new JLabel(Names.get(19) + ": ");
		JLabel ip_label = new JLabel();
		
		//initiate JPanels
		JPanel panel_video = new JPanel();
		JPanel panel_other = new JPanel();

		//edit Layout
		this.setLayout(new BorderLayout());
		this.getContentPane().add(panel_video, BorderLayout.CENTER);
		this.getContentPane().add(panel_other, BorderLayout.EAST);
		this.getContentPane().add(Live_Log_Scrollbar, BorderLayout.SOUTH);
		panel_video.setBackground(Color.lightGray);
		panel_other.setPreferredSize(new Dimension(200,500));
		panel_other.setLayout(null);
		Live_Log_Scrollbar.setPreferredSize(new Dimension(800,100));
			
		//menu "file"
		Quit.addActionListener(new Quit_ActionListener(log));
		Connect.addActionListener(new Connect_ActionListener(Names.get(12), log, ip_label));
		File.add(About);
		File.add(Connect);
		File.add(Separator_File);
		File.add(Quit);
		
		//menu "Preferences" including submenus
		Camerachoice.add(Frontcamera);
		Camerachoice.add(Backcamera);
		Camera.add(Frontcamera);
		Camera.add(Backcamera);
		Preferences.add(Camera);
		
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
		resolution_change.addActionListener(new Resolution_ActionListener(controller_Computer, log, resolution_change));
		
		//gps_coordinates
		gps_coordinates_text.setBounds(5, 30, 180, 20);
		latitude_text.setBounds(10, 55, 75, 20);
		longitude_text.setBounds(10, 80, 75, 20);
		latitude.setBounds(90, 55, 105, 20);
		longitude.setBounds(90, 80, 105, 20);
		panel_other.add(gps_coordinates_text);
		panel_other.add(latitude_text);
		panel_other.add(longitude_text);
		panel_other.add(latitude);
		panel_other.add(longitude);
		
		//connection_type
		connection_type_text.setBounds(5, 105, 80, 20);
		connection_type.setBounds(90, 105, 105, 20);
		panel_other.add(connection_type_text);
		panel_other.add(connection_type);
		
		//present_ip
		present_ip_text.setBounds(5, 130, 20, 20);
		ip_label.setBounds(30, 130, 165, 20);
		panel_other.add(present_ip_text);
		panel_other.add(ip_label);
		
		//key feedback
		up.setBounds(95, 370, 30, 30);
		down.setBounds(95, 405, 30, 30);
		left.setBounds(60, 405, 30, 30);
		right.setBounds(130, 405, 30, 30);
		panel_other.add(up);
		panel_other.add(down);
		panel_other.add(left);
		panel_other.add(right);
				
		//light_button
		light_button.setBounds(30,345,80,20);
		panel_other.add(light_button);
		light_button.addActionListener(new Light_ActionListener(controller_Computer, log, light_button));
		
		//map_button
		map_button.setBounds(115,345,80,20);
		panel_other.add(map_button);
		map_button.addActionListener(new Map_ActionListener(controller_Computer, latitude, longitude));
		
		//signal_button
		signal_button.setBounds(30, 275, 165, 40);
		panel_other.add(signal_button);
		signal_button.addActionListener(new Signal_ActionListener(controller_Computer));
		
		//soundrecord_button
		soundrecord_button.setBounds(30, 320, 165, 20);
		panel_other.add(soundrecord_button);
		soundrecord_button.addActionListener(new Record_ActionListener(controller_Computer, log, soundrecord_button));
		
		//speed_slider
		speed_slider.setBounds(5, 200, 190, 20);
		speed_label.setBounds(5, 175, 190, 20);
		panel_other.add(speed_slider);
		panel_other.add(speed_label);
		
		//angle_slider
		angle_slider.setBounds(5, 250, 190, 20);
		angle_label.setBounds(5, 225, 190, 20);
		panel_other.add(angle_slider);
		panel_other.add(angle_label);
		
		//Live-Log screen
		Live_Log.setEditable(false);
	}
	
	//method for read language.txt
	private ArrayList<String> Language(){
		ArrayList<String> Language_Name = new ArrayList<String>();
		String Language = null;
		
		try {
			language_reader = new BufferedReader (new FileReader("src/View/language.txt"));
			Language = language_reader.readLine();
		} catch (FileNotFoundException e){
			log.writelogfile("language.txt doesn't exist!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Language_Name = Language_name(Language);
		//language_reader.close();
		return Language_Name;
	}
	
	//method for read in language (names of different elements)
	private ArrayList<String> Language_name (String Language){
		ArrayList<String> Name = new ArrayList<String>();
		String Line = null;
		
		try {
			language_reader = new BufferedReader(new FileReader("src/View/languages/" + Language + ".txt"));
			while((Line = language_reader.readLine()) != null) Name.add(Line);
		} catch (FileNotFoundException e) {
			log.writelogfile(Language + ".txt doesn't exist!");
			Name = Language_name("english");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Name;
	}
}