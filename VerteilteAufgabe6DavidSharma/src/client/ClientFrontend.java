package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientFrontend {

	ClientMain cM = new ClientMain();
	private JFrame frmAufgabe;
	private JTextField txtLocalhost;
	private JTextField textFieldName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrontend window = new ClientFrontend();
					window.frmAufgabe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientFrontend() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAufgabe = new JFrame();
		frmAufgabe.setTitle("Aufgabe 6 David Sharma");
		frmAufgabe.setBounds(100, 100, 450, 300);
		frmAufgabe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAufgabe = new JLabel("Aufgabe 6 - Informationsdienste");
		lblAufgabe.setFont(new Font("Rockwell", Font.BOLD, 21));
		
		JLabel lblDavidSharma = new JLabel("David Sharma");
		
		txtLocalhost = new JTextField();
		txtLocalhost.setText("localhost");
		txtLocalhost.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Server");
		textFieldName = new JTextField();
		JButton btnInit = new JButton("abonnieren");
		btnInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Neuer Thread muss gestartet werden, sonst friert die GUI ein ! 
				 new Thread(new Runnable() {
		                public void run() {
		    				cM.abonnieren(txtLocalhost.getText(), textFieldName.getText());
		                }
		            }, "Tuwas UI Thread").start();
				 
			}
		});
		
		JButton btnKndigen = new JButton("K\u00FCndigen");
		btnKndigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cM.entfernen();
			}
		});
		
	
		textFieldName.setToolTipText("name");
		textFieldName.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmAufgabe.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addComponent(lblAufgabe))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDavidSharma))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(txtLocalhost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnKndigen)))
					.addContainerGap(55, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(69, Short.MAX_VALUE)
					.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInit)
					.addGap(143))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAufgabe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDavidSharma)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtLocalhost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnInit)
							.addGap(11))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addComponent(btnKndigen)
					.addContainerGap(81, Short.MAX_VALUE))
		);
		frmAufgabe.getContentPane().setLayout(groupLayout);
	}
}
