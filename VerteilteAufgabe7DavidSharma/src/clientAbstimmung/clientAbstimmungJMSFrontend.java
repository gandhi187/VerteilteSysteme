package clientAbstimmung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import api.Umfrage;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class clientAbstimmungJMSFrontend {
	ClientAbstimmungJMS cA;
	Umfrage u;

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientAbstimmungJMSFrontend window = new clientAbstimmungJMSFrontend();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public clientAbstimmungJMSFrontend() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textField = new JTextField();

		JLabel lblAufgabeDavid = new JLabel("Aufgabe 7 JMS");
		lblAufgabeDavid.setFont(new Font("Tahoma", Font.PLAIN, 25));

		JLabel lblDavidSharma = new JLabel("David Sharma");

		JButton btnJa = new JButton("Ja");
		btnJa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u = new Umfrage();
				u.setZustimmung();
				try {
					cA.sendMessage(u);
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton btnNein = new JButton("Nein");
		btnNein.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u = new Umfrage();
				u.setAblehnung();
				try {
					cA.sendMessage(u);
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton btnEnthaltung = new JButton("Enthaltung");
		btnEnthaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u = new Umfrage();
				u.setEnthaltung();
				try {
					cA.sendMessage(u);
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		textField.setColumns(10);

		JLabel lblServer = new JLabel("TimeToLive");

		JButton btnInit = new JButton("init");
		btnInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cA = new ClientAbstimmungJMS(Long.parseLong(textField.getText()));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(129).addComponent(lblAufgabeDavid)
						.addContainerGap(139, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(30).addComponent(lblServer)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnInit)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblDavidSharma).addGap(80))
				.addGroup(groupLayout.createSequentialGroup().addGap(46).addComponent(btnJa).addGap(18)
						.addComponent(btnNein).addGap(18).addComponent(btnEnthaltung)
						.addContainerGap(161, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblAufgabeDavid)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(
												groupLayout.createSequentialGroup().addComponent(lblDavidSharma)
														.addGap(37))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(textField, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblServer).addComponent(btnInit))
												.addGap(18)))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnJa)
								.addComponent(btnNein).addComponent(btnEnthaltung))
						.addContainerGap(124, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}
}
