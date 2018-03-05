package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;

public class View extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setTitle("Meniu");
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
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton btnOperatiiClienti = new JButton("Operatii clienti");
		btnOperatiiClienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ViewClient viewClient=new ViewClient();
				//viewClient.setVisible(true);
				OpClient op = new OpClient();
				op.setTitle("Operatii Clienti");
				op.setVisible(true);
				op.setResizable(false);
			}
		});
		contentPane.add(btnOperatiiClienti);
		
		JButton btnOperatiiProduse = new JButton("Operatii produse");
		btnOperatiiProduse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpProduct op = new OpProduct();
				op.setTitle("Operatii produse");
				op.setVisible(true);
				op.setResizable(false);

			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOperatiiClienti, 0, SpringLayout.NORTH, btnOperatiiProduse);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnOperatiiClienti, -6, SpringLayout.WEST, btnOperatiiProduse);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOperatiiProduse, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnOperatiiProduse, 146, SpringLayout.WEST, contentPane);
		contentPane.add(btnOperatiiProduse);
		
		JButton btnCreareComanda = new JButton("Creare comanda");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnCreareComanda, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCreareComanda, 278, SpringLayout.WEST, contentPane);
		btnCreareComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpComanda op = new OpComanda();
				op.setTitle("Creare comanda");
				op.setVisible(true);
				op.setResizable(false);

			}
		});
		contentPane.add(btnCreareComanda);
	}
	


}
