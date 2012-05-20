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
import java.awt.BorderLayout;
import java.awt.Color;


public class GUI_Computer extends JFrame {
	public static void main(String[]args){
		
		//Programm Startgrößenparameter 
		int window_width = 1024;
		int window_high = 768;
		
		//Programmfenster erzeugen mit festgelegter Startgröße von 800*600 Pixeln als zentriertes Fenster
		JFrame Mainwindow = new JFrame("CarDuinoDroid");
		Mainwindow.setSize(window_width,window_high);
		Mainwindow.setLocationRelativeTo(null);
		Mainwindow.setBackground(Color.darkGray);
		
		//Menüleiste
		JMenuBar Menubar = new JMenuBar();
		
		//Menü "Datei" mit Unterpunkten
		JMenu File = new JMenu("Datei");
		JSeparator Separator_File = new JSeparator();
		JMenuItem Quit = new JMenuItem("Beenden");
		JMenuItem Connection = new JMenuItem("Verbindung");
		JMenuItem About = new JMenuItem("‹ber CarDuinoDroid");
		File.add(About);
		File.add(Connection);
		File.add(Separator_File);
		File.add(Quit);
		
		//Menü "Einstellungen" mit Unterpunkten
		JMenu Preferences = new JMenu("Einstellungen");
		JCheckBoxMenuItem Light = new JCheckBoxMenuItem("Licht");
		//Untermenü "Kamera"
		JMenu Camera = new JMenu("Kamera");
		boolean bool_active_camera = true;
		JRadioButtonMenuItem Frontcamera = new JRadioButtonMenuItem("Frontkamera", bool_active_camera);
		JRadioButtonMenuItem Backcamera = new JRadioButtonMenuItem("R¸ckkamera", !bool_active_camera);
		Camera.add(Frontcamera);
		Camera.add(Backcamera);
		//Menü "Einstellungen mit Unterpunkten
		Preferences.add(Camera);
		Preferences.add(Light);
		
		//Menüs der Menüleiste zuordnen
		Menubar.add(File);
		Menubar.add(Preferences);
		
		//Menüleiste dem Programmfenster zuordnen
		Mainwindow.setJMenuBar(Menubar);
		
		//Live-Log Anzeige
		JPanel Live_Log = new JPanel();
		Live_Log.setBackground(Color.white);
		Live_Log.add(new JLabel("Log"));
		JScrollPane Live_Log_Scrollbar = new JScrollPane(Live_Log, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Mainwindow.add(Live_Log_Scrollbar,BorderLayout.SOUTH);
		
		
		//Programmfenster sichtbar machen
		Mainwindow.setVisible(true);
	}
}