package View;

/** Description of Main
*
* @author Benjamin L
* @version 13.06.2012.
*/
public class Main {

	/** Description of public static void main(String[] args)
	 * mainmethod
	 * @param args			Elements referring to Strings which contains the arguments of command line which started the program.
	 */
	public static void main(String[] args) {
		GUI_Computer programwindow = new GUI_Computer();
		programwindow.setLocationRelativeTo(null);
		programwindow.setVisible(true);
		programwindow.setFocusable(true);
	}
}
