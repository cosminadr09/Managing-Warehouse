package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JTable;

import model.Client;
import model.Product;
import bll.validare.CantitateMagazinValidation;
import bll.validare.Validation_I;
import dao.ProductDAO;

public class ProductBLL {
	private List<Validation_I<Product>> validators;

	public ProductBLL() {
		validators = new ArrayList<Validation_I<Product>>();
		validators.add(new CantitateMagazinValidation());
	}

	public Product findProductById(int id) {
		Product pr = ProductDAO.findById(id);// am gasit clientul cu acest id
		if (pr == null) {
			throw new NoSuchElementException("The product with id =" + id
					+ " was not found!");
		}
		return pr;
	}

	public List<Product> findAllProducts() {
		List<Product> products = new ArrayList<Product>();
		products = ProductDAO.findAll();
		if (products.isEmpty()) {
			throw new IllegalArgumentException("The list of products is empty!");
		}
		return products;
	}

	public int insertProduct(Product product) {
		for (Validation_I<Product> v : validators) {// mai intai validam
													// campurile date de user
			v.validate(product);
		}
		return ProductDAO.insert(product);// apoi inseram in bd
	}

	public void updateProduct(Product product) {
		// mai intai validam
		// campurile date de user
		// validez doar email
		for (Validation_I<Product> v : validators) {
			v.validate(product);
		}
		ProductDAO.update(product);
	}

	public void deleteProduct(int productId) {
		// la stergere nu am ce valida
		ProductDAO.delete(productId);

	}

	public JTable createTable() {

		String[] headtable = { "id", "denumire", "pret", "stoc" };
		//List<Product> products = new ArrayList<Product>();
		List<Product> products = this.findAllProducts();
		// nr linii=nr clienti, nr coloane=nr fielduri ale obj
		Object[][] list = new Object[products.size()][4];
		Object[] headers = new Object[headtable.length];

		int i = 0;
		for (Product p : products) {
			list[i][0] = p.getId();
			list[i][1] = p.getDenumire();
			list[i][2] = p.getPret();
			list[i][3] = p.getCantitateMagazin();
			i++;
		}
		int j=0;
		for(String s: headtable){
			headers[j]=s;
			j++;
		}
		for(Object s:headers){
			System.out.println(s);
		}
		
		JTable jt = new JTable(list, headers);
		return jt;

	}
}
