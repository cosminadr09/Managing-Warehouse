package demo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.ClientDAO;
import model.Client;
import model.Order;
import model.OrderedProduct;
import model.Product;
import bll.ClientBLL;
import bll.OrderBLL;
import bll.OrderedProductBLL;
import bll.ProductBLL;

/**
 * 
 * Creez un nou client, apoi folosesc clasa de BLL pt a-l insera in bd Daca
 * metoda returneaza un id>0, s-a reusit inserarea
 * 
 * @author cosmina
 *
 */
public class Demo {
	protected static final Logger LOGGER = Logger.getLogger(Demo.class
			.getName());
	
	public static void testClient(){
		System.out.println("Testare clasa Client!");
		ClientBLL bll = new ClientBLL();
		Client client = bll.findClientById(10);
		System.out.println("Retrieved :  "+ client);
		client.setNume("Alt nume!");
		bll.updateClient(client);
		System.out.println("New update! :  "+ client);
		
		List<Client> clients = new ArrayList<Client>();

		ClientBLL clientBll = new ClientBLL();
		clients = clientBll.findAllClients();
		System.out.println("After update!");
		for (Client c : clients) {
			System.out.println(c.toString());
		}
		
		Client newClient = new Client("Dummy", "Dummy address", "Dummy@address.co");
		int id = bll.insertClient(newClient);
		clients = clientBll.findAllClients();
		System.out.println("After insert!");
		for (Client c : clients) {
			System.out.println(c.toString());
		}
		//while(id>10){
			bll.deleteClient(id);
		//	id--;
		//}
		clients = clientBll.findAllClients();
		System.out.println("After deleting the last inserted client!");
		for (Client c : clients) {
			System.out.println(c.toString());
		}		
		
	}
	
	public static void testProduct(){
		System.out.println("Testare clasa Product!");
		ProductBLL bll = new ProductBLL();
		Product product = bll.findProductById(5);
		System.out.println("Retrieved :  "+ product);
		product.setPret(180.3);
		bll.updateProduct(product);
		System.out.println("New update! :  "+ product);
		
		List<Product> products = new ArrayList<Product>();

		ProductBLL prodBll = new ProductBLL();
		products = prodBll.findAllProducts();
		System.out.println("After update!");
		for (Product p : products) {
			System.out.println(p.toString());
		}
		
		Product newProduct = new Product("dummy product", 0.11, 50);
		int id = bll.insertProduct(newProduct);
		products = prodBll.findAllProducts();
		System.out.println("After insert!");
		for (Product p : products) {
			System.out.println(p.toString());
		}
		
		bll.deleteProduct(id);
		
		products = prodBll.findAllProducts();
		System.out.println("After deleting the last inserted product!");
		for (Product p : products) {
			System.out.println(p.toString());
		}
	}
	public static void testOrder(){
		System.out.println("Testare clasa Order!");
		OrderBLL bll  = new OrderBLL();
		List<Order> orders = new ArrayList<Order>();
		orders = bll.findAllOrders();
		System.out.println("Before insert!");
		for(Order o : orders){
			System.out.println(o.toString());
		}
		Order newOrder = new Order(1, 7);
		

		bll.insertOrder(newOrder);
		orders = bll.findAllOrders();
			
		System.out.println("After insert!");
		for(Order o : orders){
			System.out.println(o.toString());
		}
	}
	
	public static void testOrderedProduct(){
		System.out.println("Testare clasa OrderedProduct!");
		OrderedProductBLL bll = new OrderedProductBLL();
		List<OrderedProduct> orders = new ArrayList<OrderedProduct>();
		
		orders = bll.findAllOrderedProducts();
		System.out.println("Before insert!");

		for(OrderedProduct o : orders){
			System.out.println(o.toString());
		}
		
		OrderedProduct op = new OrderedProduct(1, 1, 10);
		bll.insertOrderedProduct(op);
		orders = bll.findAllOrderedProducts();

		
		System.out.println("After insert!");

		for(OrderedProduct o : orders){
			System.out.println(o.toString());
		}
	}
	
	public static void main(String[] args) throws SQLException {

		// Generate error
		try {
			Demo.testClient();
			Demo.testProduct();
			Demo.testOrder();
			Demo.testOrderedProduct();
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}

	}
}
