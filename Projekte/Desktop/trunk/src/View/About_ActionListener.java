package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class About_ActionListener implements ActionListener{
	
	String title, about_1, about_2, link;
	
	public About_ActionListener(String TITLE, String ABOUT_1, String ABOUT_2, String LINK){
		about_1 = ABOUT_1;
		about_2 = ABOUT_2;
		link = LINK;
		title = TITLE;
	}
	
	public void actionPerformed(ActionEvent e){
		JOptionPane.showMessageDialog(null, about_1 + "\n\n" + about_2 + ":\n" + link, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
