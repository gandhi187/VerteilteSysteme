package clientAbstimmung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AbstimmungFrontend extends JFrame implements ActionListener {
	ClientAbstimmung clientAbstimmung;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton buttonReset;
	JLabel label;
	JPanel panel;
	JTextField tfName;

	public AbstimmungFrontend() {
		this.setTitle("Abstimmungs-Client David Sharma");
		this.setSize(400, 200);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		label = new JLabel("Server");
		JLabel labelName = new JLabel("(c) David Sharma");
		
		tfName = new JTextField("localhost", 15);

		tfName.setForeground(Color.BLUE);
		tfName.setBackground(Color.YELLOW);

		button1 = new JButton("Ja");
		button2 = new JButton("Nein");
		button3 = new JButton("Enthalten");
		buttonReset = new JButton("Reset");

		// Buttons werden dem Listener zugeordnet
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		buttonReset.addActionListener(this);
		JPanel headlinePanel = new JPanel();

		panel.add(headlinePanel, BorderLayout.PAGE_START);
		headlinePanel.add(labelName);
		headlinePanel.add(label);
		headlinePanel.add(tfName);

		panel.add(buttonPanel, BorderLayout.CENTER);
		// Buttons werden dem JPanel hinzugefügt
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);

		panel.add(buttonReset, BorderLayout.PAGE_END);

		// JLabel wird dem Panel hinzugefügt
		this.add(panel);

	}

	public static void main(String[] args) {

		AbstimmungFrontend bl = new AbstimmungFrontend();
		bl.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {

		// Erzeugung der Clients mit Übergabe der jeweiligen Werte

		if (ae.getSource() == this.button1) {
			clientAbstimmung = new ClientAbstimmung(tfName.getText(), "Ja");

			JOptionPane.showMessageDialog(panel, "Es wurde mit Ja abgestimmt");
		} else if (ae.getSource() == this.button2) {
			clientAbstimmung = new ClientAbstimmung(tfName.getText(), "Nein");
			JOptionPane.showMessageDialog(panel, "Es wurde mit Nein abgestimmt");
		} else if (ae.getSource() == this.button3) {
			clientAbstimmung = new ClientAbstimmung(tfName.getText(), "Enthaltung");
			JOptionPane.showMessageDialog(panel, "Es wurde mit Enthalten abgestimmt");
		}

		else if (ae.getSource() == this.buttonReset) {
			String eingabe = JOptionPane.showInputDialog(null, "Geben Sie das Passwort ein", "Gesperrter Bereich",
					JOptionPane.PLAIN_MESSAGE);
			if (eingabe.equals("abc123")) {
				System.out.println("Korrektes Passwort, Umfrage wird zurückgesetzt");
				clientAbstimmung = new ClientAbstimmung(tfName.getText(), "reset");

			} else {
				System.exit(0);
			}
		}
	}
}
