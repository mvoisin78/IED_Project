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
import java.awt.Color;

@SuppressWarnings("serial")
public class Interface extends JFrame {

	private JFrame frmFilmSearch;
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
					window.frmFilmSearch.setVisible(true);
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
		frmFilmSearch = new JFrame();
		frmFilmSearch.setTitle("Film Search");
		frmFilmSearch.getContentPane().setBackground(Color.GRAY);
		frmFilmSearch.setBackground(Color.WHITE);
		frmFilmSearch.setBounds(100, 100, 671, 450);
		frmFilmSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFilmSearch.getContentPane().setLayout(null);
		frmFilmSearch.setResizable(false);

		JRadioButton rdbtnRechercheParTitre = new JRadioButton("Recherche par titre");
		rdbtnRechercheParTitre.setBackground(Color.WHITE);
		rdbtnRechercheParTitre.setBounds(401, 29, 151, 23);
		frmFilmSearch.getContentPane().add(rdbtnRechercheParTitre);

		JRadioButton rdbtnRechercheParActeur = new JRadioButton("Recherche par acteur");
		rdbtnRechercheParActeur.setBackground(Color.WHITE);
		rdbtnRechercheParActeur.setBounds(401, 55, 151, 23);
		frmFilmSearch.getContentPane().add(rdbtnRechercheParActeur);

		ButtonGroup group = new ButtonGroup();
		rdbtnRechercheParTitre.setSelected(true);
		group.add(rdbtnRechercheParTitre);
		group.add(rdbtnRechercheParActeur);

		textField = new JTextField();
		textField.setBounds(26, 35, 310, 37);
		frmFilmSearch.getContentPane().add(textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 162, 645, 221);
		frmFilmSearch.getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);

		Button btnRecherche = new Button("Rechercher");
		btnRecherche.setBackground(Color.WHITE);
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
		btnRecherche.setBounds(26, 97, 119, 46);
		frmFilmSearch.getContentPane().add(btnRecherche);

	}

	public void updateList(JTextArea textArea) {
		textArea.removeAll();
		textArea.setText(result);
		repaint();
	}
}
