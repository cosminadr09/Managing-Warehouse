package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;

public class OpComanda extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldClient;
	private JTextField textFieldProdus;
	private JTextField textFieldCantitate;
	private JTextField textFieldSuma;
	private JButton btnOrder = new JButton("Order");
	private JTable tableClienti;
	private JTable tableProduse;
	private ClientBLL cbll = new ClientBLL();
	private ProductBLL pbll = new ProductBLL();
	private int totalComenzi = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpComanda frame = new OpComanda();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OpComanda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1304, 766);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblIntroducetiIdClient = new JLabel("Introduceti ID client:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblIntroducetiIdClient, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIntroducetiIdClient, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblIntroducetiIdClient);
		
		textFieldClient = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldClient, -3, SpringLayout.NORTH, lblIntroducetiIdClient);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldClient, 23, SpringLayout.EAST, lblIntroducetiIdClient);
		contentPane.add(textFieldClient);
		textFieldClient.setColumns(10);
		
		JLabel lblIntroducetiIdProdus = new JLabel("Introduceti ID produs:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblIntroducetiIdProdus, 13, SpringLayout.SOUTH, lblIntroducetiIdClient);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIntroducetiIdProdus, 0, SpringLayout.WEST, lblIntroducetiIdClient);
		contentPane.add(lblIntroducetiIdProdus);
		
		textFieldProdus = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldProdus, -3, SpringLayout.NORTH, lblIntroducetiIdProdus);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldProdus, 0, SpringLayout.WEST, textFieldClient);
		contentPane.add(textFieldProdus);
		textFieldProdus.setColumns(10);
		
		JLabel lblIntroducetiCantitate = new JLabel("Introduceti cantitate:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblIntroducetiCantitate, 18, SpringLayout.SOUTH, lblIntroducetiIdProdus);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIntroducetiCantitate, 0, SpringLayout.WEST, lblIntroducetiIdClient);
		contentPane.add(lblIntroducetiCantitate);
		
		textFieldCantitate = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldCantitate, -3, SpringLayout.NORTH, lblIntroducetiCantitate);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldCantitate, 20, SpringLayout.EAST, lblIntroducetiCantitate);
		contentPane.add(textFieldCantitate);
		textFieldCantitate.setColumns(10);
		
		JLabel lblSumaDePlata = new JLabel("Suma de achitat (RON) :");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSumaDePlata, 28, SpringLayout.SOUTH, btnOrder);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSumaDePlata, 0, SpringLayout.WEST, lblIntroducetiIdClient);
		contentPane.add(lblSumaDePlata);
		
		textFieldSuma = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldSuma, 11, SpringLayout.SOUTH, lblSumaDePlata);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldSuma, 5, SpringLayout.WEST, lblIntroducetiIdClient);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldSuma, 0, SpringLayout.EAST, lblIntroducetiIdProdus);
		contentPane.add(textFieldSuma);
		textFieldSuma.setColumns(10);
		
		
		
		
		JLabel lblTabelClienti = new JLabel("Tabel clienti:");
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldClient, -35, SpringLayout.WEST, lblTabelClienti);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldProdus, -35, SpringLayout.WEST, lblTabelClienti);
		contentPane.add(lblTabelClienti);
		
		tableClienti = new JTable();
		tableClienti = cbll.createTable();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tableClienti, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tableClienti, 24, SpringLayout.EAST, lblTabelClienti);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tableClienti, 367, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tableClienti, 936, SpringLayout.EAST, lblTabelClienti);
		//contentPane.add(tableClienti);
		JScrollPane scrollPane1 = new JScrollPane(tableClienti);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldCantitate, -136, SpringLayout.WEST, scrollPane1);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane1, 27, SpringLayout.EAST, lblTabelClienti);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblTabelClienti, 8, SpringLayout.NORTH, scrollPane1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane1, 4, SpringLayout.NORTH, lblIntroducetiIdClient);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane1, 0, SpringLayout.SOUTH, lblSumaDePlata);
	    contentPane.add(scrollPane1, BorderLayout.EAST);

		
		JLabel lblTabelProduse = new JLabel("Tabel produse:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTabelProduse, 83, SpringLayout.EAST, textFieldSuma);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTabelClienti, 0, SpringLayout.WEST, lblTabelProduse);
		contentPane.add(lblTabelProduse);
		
		tableProduse = new JTable();
		tableProduse = pbll.createTable();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tableProduse, 27, SpringLayout.SOUTH, tableClienti);
		sl_contentPane.putConstraint(SpringLayout.WEST, tableProduse, 29, SpringLayout.EAST, lblTabelProduse);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tableProduse, 39, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tableProduse, 941, SpringLayout.EAST, lblTabelProduse);
		//contentPane.add(tableProduse);
		JScrollPane scrollPane2 = new JScrollPane(tableProduse);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblTabelProduse, 0, SpringLayout.NORTH, scrollPane2);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane2, 27, SpringLayout.SOUTH, scrollPane1);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane2, 0, SpringLayout.WEST, scrollPane1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane2, -296, SpringLayout.SOUTH, contentPane);
	    contentPane.add(scrollPane2, BorderLayout.SOUTH);
	    
	    btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				contentPane.revalidate();
				
				String idClientStr = textFieldClient.getText();
				String idProdusStr = textFieldProdus.getText();
				String cantitateStr = textFieldCantitate.getText();
				
				int idClient = Integer.parseInt(idClientStr);
				int idProdus = Integer.parseInt(idProdusStr);
				int cantitateComandata = Integer.parseInt(cantitateStr);
				
				double sumaDeAchitat = 0;
				if(cantitateComandata<0){
					JOptionPane.showMessageDialog(null,
							"Introduceti o valoare pozitiva!");
				}
				else{
					OrderBLL obll = new OrderBLL();
					
					Order order = new Order(idClient, 0);//initial cu nicio comanda
					
					Product product = pbll.findProductById(idProdus);
					if(product.getCantitateMagazin()-cantitateComandata<0){
						JOptionPane.showMessageDialog(null,
								"Comanda nu s-a putut efectua!\n Numar insuficient de produse");
					}else{
						//update totalComenzi al clientului 
						totalComenzi+=1;
						order.setTotalComenzi(totalComenzi);
						//update stoc produs
						product.setCantitateMagazin(product.getCantitateMagazin()-cantitateComandata);
						sumaDeAchitat = product.getPret()*cantitateComandata;
						pbll.updateProduct(product);
						//inserare comanda in bada de date
						obll.insertOrder(order);
						//conversie suma din double in str
						String sumaStr = Double.toString(sumaDeAchitat);
						textFieldSuma.setText(sumaStr);
						
						JOptionPane.showMessageDialog(null,
								"Comanda Dumneavoastra a fost executata!Factura eliberata!");

						Client client = cbll.findClientById(idClient);
						//String numeClient = client.getNume();
						//String totalComenziStr = Integer.toString(totalComenzi);
						List<String> lines = Arrays.asList("Numele clientului: " + client.getNume(), "Produs comandat: " + product.getDenumire(), "Suma de plata: " + sumaStr );
						Path file = Paths.get("bill.txt");
						try {
							Files.write(file, lines, Charset.forName("UTF-8"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				
				
			
				//tableProduse.repaint();
				//tableProduse.revalidate();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOrder, 36, SpringLayout.SOUTH, lblIntroducetiCantitate);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnOrder, 10, SpringLayout.WEST, contentPane);
		contentPane.add(btnOrder);
//		
//		
	}
}
