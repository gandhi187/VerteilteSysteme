package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import Server.Produkt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import javax.swing.JTextField;

public class ClientKonsumentFrontend {

	private JFrame frame;
	private JTextField txtLocalhost;
	private Produkt p;
	private JLabel lblAusgabeProdukt;
	ClientKonsument cK;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientKonsumentFrontend window = new ClientKonsumentFrontend();
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
	public ClientKonsumentFrontend() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		 cK = new ClientKonsument();
		frame.getContentPane().setBackground(SystemColor.info);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblClientKonsumentDavid = new JLabel("Client Konsument David Sharma");
		lblClientKonsumentDavid.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnPufferEntnehmen = new JButton("Puffer entnehmen");
		btnPufferEntnehmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cK.init(txtLocalhost.getText());
				p=cK.auslesen();
				lblAusgabeProdukt.setText(p.toString());
			}
		});
		
		JLabel lblAusgabe = new JLabel("Ausgabe:");
		lblAusgabe.setFont(new Font("Tahoma", Font.ITALIC, 15));
		
		JLabel label = new JLabel(" ");
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setBackground(SystemColor.textHighlight);
		
		txtLocalhost = new JTextField();
		txtLocalhost.setBackground(SystemColor.activeCaption);
		txtLocalhost.setText("localhost");
		txtLocalhost.setColumns(10);
		
		 lblAusgabeProdukt = new JLabel("Ausgabe Produkt");
		

			JLabel labelAktuellerSTand = new JLabel(" ");
			
			JLabel labelGroesse = new JLabel("");
			
		JButton btnAktuellerStand = new JButton("aktueller Stand");
		btnAktuellerStand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelAktuellerSTand.setText("Aktueller Stand: " + cK.aktuellerStand());
			}
		});
		
		JButton btnGre = new JButton("gr\u00F6\u00DFe");
		btnGre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelGroesse.setText("Gesamtgröße : " + cK.pufferGroese());
				
			}
		});
		
		JButton btnInit = new JButton("Init");
		btnInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cK.init(txtLocalhost.getText());

			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addComponent(lblClientKonsumentDavid))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblServer)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtLocalhost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(btnInit))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAusgabe)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblAusgabeProdukt))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnPufferEntnehmen)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAktuellerStand)
							.addGap(18)
							.addComponent(labelAktuellerSTand))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnGre)
							.addGap(18)
							.addComponent(labelGroesse)))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClientKonsumentDavid)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblServer)
						.addComponent(txtLocalhost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInit))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(btnPufferEntnehmen))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAusgabe)
						.addComponent(lblAusgabeProdukt))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAktuellerStand)
						.addComponent(labelAktuellerSTand))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGre)
						.addComponent(labelGroesse))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
