package Controller;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GPS_Map extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JButton iNetButt;

    public GPS_Map() {
        iNetButt = new JButton("Inet");
        iNetButt.addActionListener(this);
        this.setLayout(new FlowLayout());
        this.add(iNetButt);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void ladeINet(String seite) {
        try {
            Desktop.getDesktop().browse(new URI(seite));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iNetButt)
            ladeINet("http://goo.gl/01V2O"); 
        //http://maps.google.com/maps/api/staticmap?center=50.679423,10.861477&zoom=14&size=400x400&markers=color:blue|label:S|50.679423,10.861477&sensor=true
        //orginal link aber zu lang für testprog
    }
    //main nur zu testzecken
    public static void main(String[] args) {
        new GPS_Map();
    }
}