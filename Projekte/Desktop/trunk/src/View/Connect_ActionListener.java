package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Model.Log;

public class Connect_ActionListener implements ActionListener{
	
	String Question;
	String Ip_adress;
	Log log;
	JLabel ip;
	
	public Connect_ActionListener(String QUESTION, Log LOG, JLabel IP){
		Question = QUESTION;
		log = LOG;
		ip = IP;
	}

	public void actionPerformed(ActionEvent e) {
		Ip_adress = JOptionPane.showInputDialog(Question);
		ip.setText(Ip_adress);
		log.writelogfile("Connect to: " + Ip_adress);
	}
}
