package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import model.Client;
import bll.ClientBLL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;

public class OpClient extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNume;
	private JLabel lblAdresa;
	private JTextField textFieldAdresa;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JButton btnAdd;
	ClientBLL bll = new ClientBLL();
	private JTable table;
	private JButton btnEdit;
	private JButton btnDelete;
	private JLabel lblId_1;
	private JTextField textFieldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpClient frame = new OpClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OpClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblId = new JLabel("Nume:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblId, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblId, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblId);
		
		textFieldNume = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldNume, 0, SpringLayout.NORTH, lblId);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldNume, 7, SpringLayout.EAST, lblId);
		contentPane.add(textFieldNume);
		textFieldNume.setColumns(10);
		
		lblAdresa = new JLabel("Adresa:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAdresa, 0, SpringLayout.WEST, lblId);
		contentPane.add(lblAdresa);
		
		textFieldAdresa = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAdresa, 3, SpringLayout.NORTH, textFieldAdresa);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldAdresa, 19, SpringLayout.SOUTH, textFieldNume);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldAdresa, 0, SpringLayout.WEST, textFieldNume);
		contentPane.add(textFieldAdresa);
		textFieldAdresa.setColumns(10);
		
		lblEmail = new JLabel("E-mail:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblEmail, 23, SpringLayout.SOUTH, textFieldAdresa);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, lblId);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldEmail, 0, SpringLayout.NORTH, lblEmail);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldEmail, 0, SpringLayout.EAST, textFieldNume);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		lblId_1 = new JLabel("ID:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblId_1, 24, SpringLayout.SOUTH, textFieldEmail);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblId_1, 0, SpringLayout.WEST, lblId);
		contentPane.add(lblId_1);
		
		textFieldID = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldID, 0, SpringLayout.NORTH, lblId_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldID, 0, SpringLayout.EAST, textFieldNume);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		
		
		table = new JTable();
		table = bll.createTable();
		sl_contentPane.putConstraint(SpringLayout.NORTH, table, 32, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, table, 20, SpringLayout.EAST, textFieldAdresa);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, table, 335, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, table, -23, SpringLayout.EAST, contentPane);
	//	contentPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 5, SpringLayout.NORTH, textFieldNume);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 37, SpringLayout.EAST, textFieldNume);
	    contentPane.add(scrollPane, BorderLayout.EAST);
//	    
	    btnAdd = new JButton("Add");
	    sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 15, SpringLayout.SOUTH, btnAdd);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, 9, SpringLayout.SOUTH, textFieldID);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, 0, SpringLayout.WEST, textFieldNume);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, 0, SpringLayout.EAST, textFieldNume);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.revalidate();
				String nume = textFieldNume.getText();
				String adresa = textFieldAdresa.getText();
				String email = textFieldEmail.getText();
				Client addClient = new Client(nume, adresa, email);
				bll.insertClient(addClient);
				System.out.println("Added: "+ addClient);
				//contentPane.repaint();
			}
		});
		contentPane.add(btnAdd);

		
		btnEdit = new JButton("Edit");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnEdit, 55, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnEdit, 0, SpringLayout.EAST, textFieldNume);
		btnEdit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				contentPane.revalidate();
				String id = textFieldID.getText();
				int idInt = Integer.parseInt(id);
				String nume = textFieldNume.getText();
				String adresa = textFieldAdresa.getText();
				String email = textFieldEmail.getText();
				Client editClient = bll.findClientById(idInt);

				editClient.setNume(nume);
				editClient.setAdresa(adresa);
				editClient.setEmail(email);
				bll.updateClient(editClient);
				System.out.println("Edited: "+editClient);
				//contentPane.revalidate();
				//contentPane.repaint();
				
			}
		});
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.validate();
				String id = textFieldID.getText();
				int idInt = Integer.parseInt(id);
				bll.deleteClient(idInt);
				System.out.println("Client deleted!");
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnEdit, -16, SpringLayout.NORTH, btnDelete);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnDelete, 258, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnDelete, 53, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnDelete, -20, SpringLayout.WEST, table);
		contentPane.add(btnDelete);
		
		
	}
}
