package View;

import Controller.*;
import Model.*;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
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
import java.awt.event.WindowAdapter;
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
	Log log;
	Controller_Computer controller_Computer;
	
	public GUI_Computer(){
		this.getContentPane().setLayout(null);
		log  = new Log();
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
	protected void initWindow(){
		
		//read in names of elements (dependently on languagefile)
		ArrayList<String> Names = Language();
		
		//initiate Strings
		String resolution_list[] = {"320x240", "640x480", "720x576", "800x600", "1024x768", "1280x720", "1920x1080"};
		String Ip_adress;

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
		JButton signal_button = new JButton(Names.get(15));
		JButton ip_config_ok_button = new JButton("Ok");
		
		//initiate ToggleButtons
		JToggleButton light_button = new JToggleButton(Names.get(5), false);
		JToggleButton soundrecord_button = new JToggleButton(Names.get(16), false);
		JToggleButton up = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_up.gif"));
		JToggleButton down = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_down.gif"));
		JToggleButton left = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_left.gif"));
		JToggleButton right = new JToggleButton(new ImageIcon ("src/View/Icons/Icon_right.gif"));
		
		//initiate ComboBoxes
		JComboBox resolution_change = new JComboBox(resolution_list);
		
		//initiate ButtonGroups
		ButtonGroup Camerachoice = new ButtonGroup(); 
		
		//initiate TextFilds
		final JTextField ip_adress_input = new JTextField();
		JTextField Live_Log = new JTextField();
		
		//initiate ScrollPanes
		JScrollPane Live_Log_Scrollbar = new JScrollPane(Live_Log, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//initiate JLabels
		JLabel resolution_list_text = new JLabel(Names.get(10) + ": ");
		JLabel gps_coordinates_text = new JLabel(Names.get(11) + ": ");
		JLabel connection_type_text = new JLabel(Names.get(12) + ": ");
		JLabel label_ip_config = new JLabel(Names.get(13) + ": ");
		
		//initiate JPanels
		JPanel panel_video = new JPanel();
		JPanel panel_other = new JPanel();
		
		//initiate JDialoges
		final JDialog ip_configuration = new JDialog();

		//edit Layout
		this.setLayout(new BorderLayout());
		this.getContentPane().add(panel_video, BorderLayout.CENTER);
		this.getContentPane().add(panel_other, BorderLayout.EAST);
		this.getContentPane().add(Live_Log, BorderLayout.SOUTH);
		panel_video.setBackground(Color.lightGray);
		panel_other.setPreferredSize(new Dimension(200,500));
		panel_other.setLayout(null);
		Live_Log.setPreferredSize(new Dimension(800,100));
		
		//ip_configuration
		ip_configuration.setTitle(Names.get(13));
		ip_configuration.setSize(300, 200);
		ip_configuration.setLocationRelativeTo(null);
		ip_configuration.setLayout(null);
		label_ip_config.setBounds(100, 20, 200, 20);
		ip_adress_input.setBounds(20, 60 , 240, 20);
		ip_config_ok_button.setBounds(110, 135, 80, 20);
		ip_configuration.add(label_ip_config);
		ip_configuration.add(ip_adress_input);
		ip_configuration.add(ip_config_ok_button);
		ip_config_ok_button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String Ip = ip_adress_input.getText();
		    	System.out.println(Ip);
		    	ip_configuration.setVisible(false);
				ip_configuration.setModal(false);
		    }
		});
		ip_configuration.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ip_configuration.setVisible(false);
				ip_configuration.setModal(false);
			}
		});
			
		//menu "file"
		Quit.addActionListener(new Quit_ActionListener(log));
		Connection.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ip_configuration.setVisible(true);
				ip_configuration.setModal(true);
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
		
		//gps_coordinates
		gps_coordinates_text.setBounds(5, 30, 80, 20);
		panel_other.add(gps_coordinates_text);
		
		//connection_type
		connection_type_text.setBounds(5, 90, 80, 20);
		panel_other.add(connection_type_text);
		
		//key feedback
		up.setBounds(130, 370, 30, 30);
		down.setBounds(130, 405, 30, 30);
		left.setBounds(95, 405, 30, 30);
		right.setBounds(165, 405, 30, 30);
		panel_other.add(up);
		panel_other.add(down);
		panel_other.add(left);
		panel_other.add(right);
		
		//light_button
		light_button.setBounds(30,345,80,20);
		panel_other.add(light_button);
		
		//map_button
		map_button.setBounds(115,345,80,20);
		panel_other.add(map_button);
		
		//signal_button
		signal_button.setBounds(30, 275, 165, 40);
		panel_other.add(signal_button);
		signal_button.addActionListener(new Signal_ActionListener(controller_Computer));
		
		//soundrecord_button
		soundrecord_button.setBounds(30, 320, 165, 20);
		panel_other.add(soundrecord_button);
		
		//Live-Log screen
		Live_Log.add(Live_Log_Scrollbar);
	}
		
	//method for read language.txt
	protected ArrayList<String> Language(){
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
	protected ArrayList<String> Language_name (String Language){
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