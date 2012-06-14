package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Language_ActionListener implements ActionListener{
	
	String[] Languages;
	JDialog language_dialog;
	JComboBox language_ComboBox;
	JLabel language_label;
	String title;
	JButton ok;
	 
	public Language_ActionListener(JDialog LANGUAGE_DIALOG, String[] LANGUAGES, String TITLE, JButton OK){
		language_dialog = LANGUAGE_DIALOG;
		title = TITLE;
		Languages = LANGUAGES;
		ok = OK;
	}
	
	public void actionPerformed(ActionEvent e){
		language_dialog.setTitle(title);
		language_dialog.setMinimumSize(new Dimension(300,100));
		language_dialog.setLayout(null);
		language_label = new JLabel(title + ":");
		language_label.setBounds(10, 10, 80, 25);
		language_ComboBox = new JComboBox(Languages);
		language_ComboBox.setBounds(100, 10, 190, 25);
		ok.setBounds(120, 45, 60, 25);
		language_dialog.add(language_ComboBox);
		language_dialog.add(language_label);
		language_dialog.add(ok);
		language_dialog.setVisible(true);
		language_dialog.setModal(true);
		language_dialog.setLocationRelativeTo(null);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				language_dialog.setVisible(false);
				language_dialog.setModal(false);
			}
		});
	}
}
