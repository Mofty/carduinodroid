package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class language_ok_button_ActionListener implements ActionListener{
	
	JDialog language_dialog;
	JComboBox language_ComboBox;
	String Selected;
	PrintWriter languagefile_writer;

	public language_ok_button_ActionListener(JDialog LANGUAGE_DIALOG, JComboBox LANGUAGES){
		language_dialog = LANGUAGE_DIALOG;
		language_ComboBox = LANGUAGES;
	}
	
	public void actionPerformed(ActionEvent e){
		//visual options
		language_dialog.setVisible(false);
		language_dialog.setModal(false);
		
		//overwrite  language.txt
		Selected = (String)language_ComboBox.getSelectedItem();
		try{
			languagefile_writer = new PrintWriter(new FileWriter("src/View/language.txt"));
            languagefile_writer.print(Selected);
            languagefile_writer.flush();
            languagefile_writer.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        } 
	}
}
