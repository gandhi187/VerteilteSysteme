package clientAbfrage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import server.Reply;
 
// Damit Objekte der Klasse BeispielListener
// zum ActionListener werden kann, muss das Interface
// ActionListener implementiert werden
public class AbfrageFrontend extends JFrame implements ActionListener
{
	ClientAbfrage clientAbfrage;
    JButton button1;

    JLabel label;
    JPanel panel;
    JLabel ergebnis;
    JLabel ergebnisAusgabe = new JLabel();
    JTextField tfName;
 
    public AbfrageFrontend(){
        this.setTitle("Abstimmungs-Client David Sharma");
        this.setSize(400, 200);
        panel = new JPanel();
        panel.setLayout(  new BorderLayout() );

        JPanel buttonPanel = new JPanel();
        // Leeres JLabel-Objekt wird erzeugt
        label = new JLabel("Server");
        
         tfName = new JTextField("localhost", 15);
        // Schriftfarbe wird gesetzt
        tfName.setForeground(Color.BLUE);
        // Hintergrundfarbe wird gesetzt
        tfName.setBackground(Color.YELLOW);
        
        //Drei Buttons werden erstellt
        button1 = new JButton("Abfragen");

 
        //Buttons werden dem Listener zugeordnet
        button1.addActionListener(this);
    
        JPanel headlinePanel = new JPanel();
        
        panel.add(headlinePanel, BorderLayout.PAGE_START);
        headlinePanel.add(label);
        headlinePanel.add(tfName);
       
        panel.add(buttonPanel, BorderLayout.CENTER);
        //Buttons werden dem JPanel hinzugefügt
        buttonPanel.add(button1);
   
        JPanel ergebnisPanel = new JPanel();
        ergebnis = new JLabel("(c) David Sharma Ergebnis");
        ergebnisPanel.add(ergebnis);
        ergebnisPanel.add(ergebnisAusgabe);
        panel.add(ergebnisPanel, BorderLayout.PAGE_END);
        //JLabel wird dem Panel hinzugefügt
        this.add(panel);
     
    }
 
    public static void main(String[] args)
    {
        // Ein neues Objekt der Klasse BeispielListener wird erzeugt
        // und sichtbar gemacht
        AbfrageFrontend bl = new AbfrageFrontend();
        bl.setVisible(true);
    }
 
    public void actionPerformed (ActionEvent ae){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, wird der Text des JLabels entsprechend geändert
        if(ae.getSource() == this.button1){
        	 clientAbfrage = new ClientAbfrage(tfName.getText());
        	Reply r =  clientAbfrage.abfragen();
        	JOptionPane.showMessageDialog(panel, "Es wurde abgefragt");
        	ergebnisAusgabe.setText(r.toString());
        	
        }
    }
}
