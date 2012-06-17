package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/** Description of About_ActionListener
*
* @author Benjamin L
* @version 12.06.2012.
*/
public class About_ActionListener implements ActionListener{
	
	String title, about_1, about_2, link;
	
	/** Description of About_ActionListener(String TITLE, String ABOUT_1, String ABOUT_2, String LINK) 
	 * 
	 * @param TITLE		Title of About-window.
	 * @param ABOUT_1	Content of first part of information about the project.
	 * @param ABOUT_2	Content of second part of information about the project.
	 * @param LINK		The weblink to Tracsite.
	 */
	public About_ActionListener(String TITLE, String ABOUT_1, String ABOUT_2, String LINK){
		about_1 = ABOUT_1;
		about_2 = ABOUT_2;
		link = LINK;
		title = TITLE;
	}
	
	/** Description of actionPreformed(ActionEvent e)
	 * 
	 * Shows the information about this project in popup window when pressing the menuitem.
	 * 
	 * @param e			Event by pressing the About menuitem.
	 */
	public void actionPerformed(ActionEvent e){
		JOptionPane.showMessageDialog(null, about_1 + "\n\n" + about_2 + ":\n" + link, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
