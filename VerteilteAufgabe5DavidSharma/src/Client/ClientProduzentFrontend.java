package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import Server.Produkt;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientProduzentFrontend {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	JButton btnPufferEinfgen;
	private Produkt produkt;
	private ClientProduzent clientProduzent;
	private boolean ok = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientProduzentFrontend window = new ClientProduzentFrontend();
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
	public ClientProduzentFrontend() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		clientProduzent = new ClientProduzent();
		frame = new JFrame("Aufgabe 4 David Sharma");
		frame.getContentPane().setBackground(new Color(153, 204, 255));
		frame.setBounds(100, 100, 450, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblProduzentfrontendcDavid = new JLabel("ProduzentFrontend David Sharma");
		lblProduzentfrontendcDavid.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProduzentfrontendcDavid.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblProduktGenerieren = new JLabel("Produkt generieren");

		JLabel lblProduktName = new JLabel("Produkt Name:");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblProduktAnzahl = new JLabel("Produkt Anzahl:");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnGenerieren = new JButton("Generieren");
		btnGenerieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("Produkt erstellt");
				ok = true;
				btnPufferEinfgen.setEnabled(true);
			}
		});
		btnGenerieren.setIcon(new ImageIcon(
				ClientProduzentFrontend.class.getResource("/javax/swing/plaf/metal/icons/ocean/newFolder.gif")));

		JLabel lblServer = new JLabel("Server:");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField_2 = new JTextField("localhost");
		textField_2.setColumns(10);

		btnPufferEinfgen = new JButton("Puffer einf\u00FCgen");
		btnPufferEinfgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				int anzahl = Integer.parseInt(textField_1.getText());

				produkt = new Produkt(anzahl, name);
			
				clientProduzent.init(textField_2.getText());
				clientProduzent.einfuegen(produkt);
			}
		});
		btnPufferEinfgen.setEnabled(false);

		btnPufferEinfgen.setIcon(new ImageIcon(
				ClientProduzentFrontend.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		JLabel aktuellerStandLabel = new JLabel(" ");

		JLabel pufferGroesseLabel = new JLabel(" ");
		JButton btnAktuellerStand = new JButton("aktueller Stand");
		btnAktuellerStand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				aktuellerStandLabel.setText("Stand: " + clientProduzent.aktuellerStand());
//				System.out.println(clientProduzent.aktuellerStand());
				;
			}
		});

		JButton btnPuffergre = new JButton("Puffergr\u00F6\u00DFe");
		btnPuffergre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pufferGroesseLabel.setText("Größe: " + clientProduzent.pufferGroese());
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblProduzentfrontendcDavid, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProduktGenerieren)
					.addGap(121)
					.addComponent(lblServer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProduktName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProduktAnzahl)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGenerieren)
						.addComponent(btnPufferEinfgen)))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAktuellerStand)
					.addGap(18)
					.addComponent(aktuellerStandLabel))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPuffergre)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pufferGroesseLabel)
					.addGap(109))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblProduzentfrontendcDavid)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduktGenerieren)
						.addComponent(lblServer)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduktName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduktAnzahl)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGenerieren)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPufferEinfgen)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAktuellerStand)
						.addComponent(aktuellerStandLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPuffergre)
						.addComponent(pufferGroesseLabel))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
