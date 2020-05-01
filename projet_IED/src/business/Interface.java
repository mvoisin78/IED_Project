package business;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import org.apache.commons.lang3.StringUtils;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Interface extends JFrame {

	private JFrame frame;
	private JTextField textField;

	private Mediateur mediateur = new Mediateur();
	private String result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
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
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 415, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JRadioButton rdbtnRechercheParTitre = new JRadioButton("Recherche par titre");
		rdbtnRechercheParTitre.setBounds(39, 87, 151, 23);
		frame.getContentPane().add(rdbtnRechercheParTitre);

		JRadioButton rdbtnRechercheParActeur = new JRadioButton("Recherche par acteur");
		rdbtnRechercheParActeur.setBounds(39, 110, 151, 23);
		frame.getContentPane().add(rdbtnRechercheParActeur);

		ButtonGroup group = new ButtonGroup();
		rdbtnRechercheParTitre.setSelected(true);
		group.add(rdbtnRechercheParTitre);
		group.add(rdbtnRechercheParActeur);

		textField = new JTextField();
		textField.setBounds(45, 26, 310, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(26, 162, 348, 221);
		frame.getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);

		Button btnRecherche = new Button("Rechercher");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				if (rdbtnRechercheParActeur.isSelected()) {
					result = mediateur.resultActeur(StringUtils.capitalize(name));
				} else {
					result = mediateur.resultTitre(StringUtils.capitalize(name));
				}
				updateList(textArea);
			}
		});
		btnRecherche.setBounds(225, 87, 119, 46);
		frame.getContentPane().add(btnRecherche);

	}

	public void updateList(JTextArea textArea) {
		textArea.removeAll();
		textArea.setText(result);
		repaint();
	}
}
