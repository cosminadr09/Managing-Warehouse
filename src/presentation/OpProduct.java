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
import javax.swing.JTable;
import javax.swing.JButton;

import model.Product;
import bll.ProductBLL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class OpProduct extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDenumire;
	private JTextField textFieldPret;
	private JTextField textFieldCantitateMagazin;
	private JTable table;
	private ProductBLL bll = new ProductBLL();
	private JTextField textFieldID;
	private List<Product> products = new ArrayList<Product>();
	SpringLayout sl_contentPane = new SpringLayout();
	JScrollPane scrollPane;// = new JScrollPane();
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpProduct frame = new OpProduct();
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
	public OpProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblDenumire = new JLabel("Denumire:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDenumire, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDenumire, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblDenumire);
		
		textFieldDenumire = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldDenumire, 12, SpringLayout.EAST, lblDenumire);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textFieldDenumire, 0, SpringLayout.SOUTH, lblDenumire);
		contentPane.add(textFieldDenumire);
		textFieldDenumire.setColumns(10);
		
		JLabel lblPret = new JLabel("Pret:");
		contentPane.add(lblPret);
		
		textFieldPret = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldPret, 22, SpringLayout.SOUTH, textFieldDenumire);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPret, 3, SpringLayout.NORTH, textFieldPret);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldPret, 0, SpringLayout.WEST, textFieldDenumire);
		contentPane.add(textFieldPret);
		textFieldPret.setColumns(10);
		
		JLabel lblStoc = new JLabel("Stoc:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblStoc, 32, SpringLayout.SOUTH, lblPret);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPret, 0, SpringLayout.EAST, lblStoc);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblStoc, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblStoc);
		
		textFieldCantitateMagazin = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldCantitateMagazin, 0, SpringLayout.NORTH, lblStoc);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldCantitateMagazin, 0, SpringLayout.EAST, textFieldDenumire);
		contentPane.add(textFieldCantitateMagazin);
		textFieldCantitateMagazin.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblId, 31, SpringLayout.SOUTH, lblStoc);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblId, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblId);
		
		textFieldID = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textFieldID, 0, SpringLayout.SOUTH, lblId);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldID, 0, SpringLayout.EAST, textFieldDenumire);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		
		table = new JTable();
		table=bll.createTable();
		sl_contentPane.putConstraint(SpringLayout.NORTH, table, 25, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, table, 51, SpringLayout.EAST, textFieldDenumire);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, table, -19, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, table, -51, SpringLayout.EAST, contentPane);
		//contentPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, lblDenumire);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 205, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -21, SpringLayout.EAST, contentPane);
	    contentPane.add(scrollPane, BorderLayout.EAST);

		
		JButton btnAddProduct = new JButton("Add product");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAddProduct, 26, SpringLayout.SOUTH, textFieldID);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAddProduct, 72, SpringLayout.WEST, lblDenumire);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAddProduct, 0, SpringLayout.EAST, textFieldDenumire);
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String idString =textFieldID.getText();
				//int id = Integer.parseInt(idString);
				
				contentPane.revalidate();
				String denumire = textFieldDenumire.getText();
				
				String pretString = textFieldPret.getText();
				double pret = Double.parseDouble(pretString);
				
				String stocString = textFieldCantitateMagazin.getText();
				int stoc = Integer.parseInt(stocString);
				
				Product product = new Product(denumire, pret, stoc);
				bll.insertProduct(product);
				System.out.println("Added: "+product);
				
				
			}
		});
		contentPane.add(btnAddProduct);
		
		JButton btnEditProduct = new JButton("Edit product");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnEditProduct, 56, SpringLayout.SOUTH, textFieldID);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnEditProduct, 72, SpringLayout.WEST, lblDenumire);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnEditProduct, 0, SpringLayout.EAST, textFieldDenumire);
		btnEditProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.revalidate();
				
				String idString =textFieldID.getText();
				int id = Integer.parseInt(idString);
				
				String denumire = textFieldDenumire.getText();
				
				String pretString = textFieldPret.getText();
				double pret = Double.parseDouble(pretString);
				
				String stocString = textFieldCantitateMagazin.getText();
				int stoc = Integer.parseInt(stocString);
				
				Product p = bll.findProductById(id);
				p.setDenumire(denumire);
				p.setPret(pret);
				p.setCantitateMagazin(stoc);
				
				bll.updateProduct(p);
				System.out.println("Updated: "+ p);
				
			}
		});
		contentPane.add(btnEditProduct);
		
		JButton btnDeleteProduct = new JButton("Delete product");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnDeleteProduct, 87, SpringLayout.SOUTH, textFieldID);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnDeleteProduct, 0, SpringLayout.EAST, textFieldDenumire);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnEditProduct, -6, SpringLayout.NORTH, btnDeleteProduct);
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.revalidate();
				String idString =textFieldID.getText();
				int id = Integer.parseInt(idString);
				Product p = bll.findProductById(id);
				bll.deleteProduct(id);
				System.out.println("Deleted!");
				
			}
		});
		contentPane.add(btnDeleteProduct);
		
//		JButton btnShowTable = new JButton("Show table");
//		btnShowTable.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JTable table2 = new JTable();
//				table2 = bll.createTable();
//				JScrollPane scrollPane = new JScrollPane(table);
//				contentPane.add(scrollPane, BorderLayout.EAST);
//				
//			}
//		});
//		sl_contentPane.putConstraint(SpringLayout.NORTH, btnShowTable, 28, SpringLayout.SOUTH, btnDeleteProduct);
//		sl_contentPane.putConstraint(SpringLayout.WEST, btnShowTable, 89, SpringLayout.WEST, contentPane);
//		contentPane.add(btnShowTable);
		
		
		
//		JButton btnShowTable = new JButton("Show table");
//		btnShowTable.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				table_1 = new JTable();
//				sl_contentPane.putConstraint(SpringLayout.NORTH, table_1, 28, SpringLayout.SOUTH, scrollPane);
//				sl_contentPane.putConstraint(SpringLayout.WEST, table_1, 30, SpringLayout.EAST, btnEditProduct);
//				sl_contentPane.putConstraint(SpringLayout.SOUTH, table_1, 223, SpringLayout.SOUTH, scrollPane);
//				sl_contentPane.putConstraint(SpringLayout.EAST, table_1, 0, SpringLayout.EAST, scrollPane);
//				contentPane.add(table_1);
//				JScrollPane scrollPane1 = new JScrollPane(table);
//				sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, lblDenumire);
//				sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 205, SpringLayout.NORTH, contentPane);
//				sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -21, SpringLayout.EAST, contentPane);
//				contentPane.add(scrollPane, BorderLayout.EAST);
//			}
//		});
//		sl_contentPane.putConstraint(SpringLayout.NORTH, btnShowTable, 17, SpringLayout.SOUTH, btnDeleteProduct);
//		sl_contentPane.putConstraint(SpringLayout.EAST, btnShowTable, -47, SpringLayout.WEST, table_1);
//		contentPane.add(btnShowTable);
		

		
	}
}
