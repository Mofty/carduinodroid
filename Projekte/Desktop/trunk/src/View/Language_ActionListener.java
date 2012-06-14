package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Language_ActionListener implements ActionListener{
	
	JDialog language_dialog;
	JComboBox language_ComboBox;
	JLabel language_label, info_label;
	String Title, Info, Selected;
	JButton ok;
	 
	public Language_ActionListener(JDialog LANGUAGE_DIALOG, JComboBox LANGUAGES, String TITLE, String INFO, JButton OK){
		language_dialog = LANGUAGE_DIALOG;
		Title = TITLE;
		ok = OK;
		Info = INFO;
		language_ComboBox = LANGUAGES;
	}
	
	public void actionPerformed(ActionEvent e){

		//dialog settings
		language_dialog.setTitle(Title);
		language_dialog.setMinimumSize(new Dimension(400,150));
		language_dialog.setLayout(null);
		
		//label settings
		language_label = new JLabel(Title + ":");
		language_label.setBounds(10, 10, 80, 25);
		info_label = new JLabel(Info);
		info_label.setBounds(10, 45, 370, 20);
		
		//ComboBox settings
		language_ComboBox.setBounds(100, 10, 270, 25);
		ok.setBounds(170, 75, 60, 25);
		
		//visual options
		language_dialog.add(language_ComboBox);
		language_dialog.add(language_label);
		language_dialog.add(info_label);
		language_dialog.add(ok);
		language_dialog.setVisible(true);
		language_dialog.setModal(true);
		language_dialog.setLocationRelativeTo(null);
	}
}
