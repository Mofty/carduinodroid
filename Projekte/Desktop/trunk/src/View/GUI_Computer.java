package View;

import Controller.*;
import Model.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
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
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** Description of GUI_Computer
*
* @author Benjamin L
* @version 14.06.2012
*/
public class GUI_Computer extends JFrame{
	
	static BufferedReader language_reader;
	Log log;
	GPSTrack gpstrack;
	Controller_Computer controller_Computer;
	public JTextArea Live_Log;
	public JSlider speed_slider;
	public JSlider angle_slider;
	public JLabel connection_type;
	public JLabel latitude;
	public JLabel longitude;
	public JLabel up;
	public JLabel down;
	public JLabel left;
	public JLabel right;
	public JLabel image;

	
	//initiate icons
	ImageIcon up_icon = new ImageIcon("src/View/Icons/Icon_up.gif");
	ImageIcon down_icon = new ImageIcon("src/View/Icons/Icon_down.gif");
	ImageIcon left_icon = new ImageIcon("src/View/Icons/Icon_left.gif");
	ImageIcon right_icon = new ImageIcon("src/View/Icons/Icon_right.gif");
	ImageIcon up_pressed_icon = new ImageIcon("src/View/Icons/Icon_up_pressed.gif");
	ImageIcon down_pressed_icon = new ImageIcon("src/View/Icons/Icon_down_pressed.gif");
	ImageIcon left_pressed_icon = new ImageIcon("src/View/Icons/Icon_left_pressed.gif");
	ImageIcon right_pressed_icon = new ImageIcon("src/View/Icons/Icon_right_pressed.gif");
	
	/** Description of GUI_Computer()
	 * Constructor which creates the graphical user interface.
	 */
	public GUI_Computer(){
		
		//set layout
		this.getContentPane().setLayout(null);
		
		//create objects of other classes
		log  = new Log(this);
		gpstrack = new GPSTrack();
		controller_Computer = new Controller_Computer(log, this);
		
		//create programwindow
		this.initWindow();
				
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(800,600));
		this.setTitle("CarDuinoDroid");
		
		//window listener for closing
		this.addWindowListener(new WindowAdapter() {
			
			/** Description of windowClosed(WindowEvent arg0)
			 * 
			 * @param arg0		argument when window was closed
			 * @return			void
			 */
			public void windowClosed(WindowEvent arg0) {
				gpstrack.savegpxfile();
				log.writelogfile("CarDuinoDroid closed.");
				System.exit(0);
			}
			
			/** Description of windowClosed(WindowEvent arg0)
			 * 
			 * @param arg0		argument when pressing the closebutton of window
			 * @return			void
			 */
			public void windowClosing(WindowEvent arg0) {
				gpstrack.savegpxfile();
				log.writelogfile("CarDuinoDroid closed.");
				System.exit(0);
			}
		});
		
		//KeyListener
		this.addKeyListener(new Direction_KeyListener(this));		
	}
	
	//method: create window
	/** Description of intiWindow()
	 * 
	 * Creates window with all elements.
	 */
	private void initWindow(){
		
		//read in names of elements (dependently on languagefile)
		ArrayList<String> Names = Language();
		
		//read in language filenames
		File language_file = new File("src/View/languages/");
		
		//initiate Strings
		String resolution_list[] = {"320x240", "640x480", "720x576", "800x600", "1024x768", "1280x720", "1920x1080"};
		String languages[] = language_file.list();
		for (int i = 0; i <= languages.length-1; i++) languages[i] = languages[i].substring(0, languages[i].indexOf('.'));
		
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
		JMenuItem Language = new JMenuItem(Names.get(24));
	
		//initiate RadioButtonMenuItems
		JRadioButtonMenuItem Frontcamera = new JRadioButtonMenuItem(Names.get(7),true);
		JRadioButtonMenuItem Backcamera = new JRadioButtonMenuItem(Names.get(8));
		
		//initiate Separators
		JSeparator Separator_File = new JSeparator();
	
		//initiate Buttons
		JButton map_button = new JButton(Names.get(9));
		JButton signal_button = new JButton(Names.get(13));
		JButton  language_ok_button = new JButton(Names.get(25));
		
		//initiate ToggleButtons
		JToggleButton light_button = new JToggleButton(Names.get(5), false);
		JToggleButton soundrecord_button = new JToggleButton(Names.get(14), false);
		
		//initiate ComboBoxes
		JComboBox resolution_change = new JComboBox(resolution_list);
		JComboBox language_ComboBox = new JComboBox(languages);
		
		//initiate ButtonGroups
		ButtonGroup Camerachoice = new ButtonGroup(); 
		
		//initiate TextAreas
		Live_Log = new JTextArea();
		
		//initiate ScrollPanes
		JScrollPane Live_Log_Scrollbar = new JScrollPane(Live_Log, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//initiate JSliders
		speed_slider = new JSlider(1, 100, 20);
		angle_slider = new JSlider(1, 100, 50);
				
		//initiate BevelBorders
		Border unpressed_border = BorderFactory.createRaisedBevelBorder();
		Border pressed_border = BorderFactory.createLoweredBevelBorder();
		Border live_log_border = BorderFactory.createTitledBorder("Log:");
		
		//initiate JLabels
		JLabel resolution_list_text = new JLabel(Names.get(10) + ": ");
		JLabel gps_coordinates_text = new JLabel(Names.get(11) + ": ");
		JLabel connection_type_text = new JLabel(Names.get(2) + ": ");
		JLabel latitude_text = new JLabel(Names.get(17) + ": ");
		JLabel longitude_text = new JLabel(Names.get(18) + ": ");
		JLabel speed_label = new JLabel(Names.get(15) + ": ");
		JLabel angle_label = new JLabel(Names.get(16) + ": ");
		connection_type = new JLabel();
		latitude = new JLabel();
		longitude = new JLabel();
		JLabel present_ip_text = new JLabel(Names.get(19) + ": ");
		JLabel ip_label = new JLabel();
		up = new JLabel(up_icon);
		down = new JLabel(down_icon);
		left = new JLabel(left_icon);
		right = new JLabel(right_icon);
		
		//initiate JDialoges
		JDialog language_dialog = new JDialog();
		
		//initiate JPanels
		image = new JLabel();
		JPanel panel_other = new JPanel();

		//edit Layout
		this.setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.CENTER);
		this.getContentPane().add(panel_other, BorderLayout.EAST);
		this.getContentPane().add(Live_Log_Scrollbar, BorderLayout.SOUTH);
		image.setBackground(Color.lightGray);
		panel_other.setPreferredSize(new Dimension(200,500));
		panel_other.setLayout(null);
		Live_Log_Scrollbar.setPreferredSize(new Dimension(800,100));
		panel_other.setFocusable(false);
		image.setFocusable(false);
			
		//menu "file"
		Quit.addActionListener(new Quit_ActionListener(log,gpstrack));
		Connect.addActionListener(new Connect_ActionListener(Names.get(12), log, ip_label, this));
		About.addActionListener(new About_ActionListener(Names.get(3), Names.get(21), Names.get(22), Names.get(23)));
		File.add(About);
		File.add(Connect);
		File.add(Separator_File);
		File.add(Quit);
		File.setFocusable(false);
		
		//menu "Preferences" including submenus
		Camerachoice.add(Frontcamera);
		Camerachoice.add(Backcamera);
		Camera.add(Frontcamera);
		Camera.add(Backcamera);
		Language.setMinimumSize(new Dimension(300,100));
		Language.setLayout(null);
		Language.addActionListener(new Language_ActionListener(language_dialog, language_ComboBox, Names.get(24), Names.get(26), language_ok_button));
		language_ok_button.addActionListener(new language_ok_button_ActionListener(language_dialog, language_ComboBox));
		Frontcamera.addActionListener(new SwitchCameraType_ActionListener(controller_Computer, log, true));
		Backcamera.addActionListener(new SwitchCameraType_ActionListener(controller_Computer, log, false));
		Preferences.add(Camera);
		Preferences.add(Language);
		
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
		resolution_change.setFocusable(false);
		
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
		up.setBorder(unpressed_border);
		down.setBorder(unpressed_border);
		left.setBorder(unpressed_border);
		right.setBorder(unpressed_border);
		panel_other.add(up);
		panel_other.add(down);
		panel_other.add(left);
		panel_other.add(right);
				
		//light_button
		light_button.setBounds(30,345,80,20);
		panel_other.add(light_button);
		light_button.addActionListener(new Light_ActionListener(controller_Computer, log, light_button));
		light_button.setFocusable(false);
		
		//map_button
		map_button.setBounds(115,345,80,20);
		panel_other.add(map_button);
		map_button.addActionListener(new Map_ActionListener(controller_Computer, latitude, longitude));
		map_button.setFocusable(false);
		
		//signal_button
		signal_button.setBounds(30, 275, 165, 40);
		panel_other.add(signal_button);
		signal_button.addActionListener(new Signal_ActionListener(controller_Computer));
		signal_button.setFocusable(false);
		
		//soundrecord_button
		soundrecord_button.setBounds(30, 320, 165, 20);
		panel_other.add(soundrecord_button);
		soundrecord_button.addActionListener(new Record_ActionListener(controller_Computer, log, soundrecord_button));
		soundrecord_button.setFocusable(false);
		
		//speed_slider
		speed_slider.setBounds(5, 200, 190, 20);
		speed_label.setBounds(5, 175, 190, 20);
		panel_other.add(speed_slider);
		panel_other.add(speed_label);
		speed_slider.setFocusable(false);
		
		//angle_slider
		angle_slider.setBounds(5, 250, 190, 20);
		angle_label.setBounds(5, 225, 190, 20);
		panel_other.add(angle_slider);
		panel_other.add(angle_label);
		angle_slider.setFocusable(false);
		
		//Live-Log screen
		Live_Log.setEditable(false);
		Live_Log.setFocusable(false);
		Live_Log_Scrollbar.setBorder(live_log_border);
	}
	
	/** Description of Language()
	 * 
	 * Method for read language.txt.
	 * 
	 * @return			Names of elements of window.
	 */
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
	
	/** Description of Language_name(String Language)
	 * 
	 * Method for read in language (names of different elements).
	 * 
	 * @param Language	Language which is written in the language.txt
	 * @return			Names of elements of window.
	 */
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
	
	/** Description of PressedBorderUp()
	 * 
	 * Method for feedback, when car drives forward.
	 */
	public void PressedBorderUp(){
		Border pressed_border = BorderFactory.createLoweredBevelBorder();
		up.setBorder(pressed_border);
		up.setIcon(up_pressed_icon);
	}
	
	/** Description of PressedBorderDown()
	 * 
	 * Method for feedback, when car drives back.
	 */
	public void PressedBorderDown(){
		Border pressed_border = BorderFactory.createLoweredBevelBorder();
		down.setBorder(pressed_border);
		down.setIcon(down_pressed_icon);
	}
	
	/** Description of PressedBorderRight()
	 * 
	 * Method for feedback, when car drives right.
	 */
	public void PressedBorderRight(){
		Border pressed_border = BorderFactory.createLoweredBevelBorder();
		right.setBorder(pressed_border);
		right.setIcon(right_pressed_icon);
	}
	
	/** Description of PressedBorderLeft()
	 * 
	 * Method for feedback, when car drives left.
	 */
	public void PressedBorderLeft(){
		Border pressed_border = BorderFactory.createLoweredBevelBorder();
		left.setBorder(pressed_border);
		left.setIcon(left_pressed_icon);
	}
	
	/** Description of UnpressedBorderUp()
	 * 
	 * Method for feedback, when car stops driving forward.
	 */
	public void UnpressedBorderUp(){
		if (up != null){
			Border unpressed_border = BorderFactory.createRaisedBevelBorder();
			up.setBorder(unpressed_border);
			up.setIcon(up_icon);
		}
	}
	
	/** Description of UnpressedBorderDown()
	 * 
	 * Method for feedback, when car stops driving back.
	 */
	public void UnpressedBorderDown(){
		if (down != null){
			Border unpressed_border = BorderFactory.createRaisedBevelBorder();
			down.setBorder(unpressed_border);
			down.setIcon(down_icon);
		}
	}
	
	/** Description of UnpressedBorderRight()
	 * 
	 * Method for feedback, when car stops driving right.
	 */
	public void UnpressedBorderRight(){
		if (right != null){
			Border unpressed_border = BorderFactory.createRaisedBevelBorder();
			right.setBorder(unpressed_border);
			right.setIcon(right_icon);
		}
	}
	
	/** Description of UnpressedBorderLeft()
	 * 
	 * Method for feedback, when car stops driving left.
	 */
	public void UnpressedBorderLeft(){
		if (left != null){
			Border unpressed_border = BorderFactory.createRaisedBevelBorder();
			left.setBorder(unpressed_border);
			left.setIcon(left_icon);
		}
	}
}