package bll;

import java.util.ArrayList;
import java.util.List;

import model.OrderedProduct;
import bll.validare.QuantityValidation;
import bll.validare.Validation_I;
import dao.OrderedProductDAO;

public class OrderedProductBLL {
	private List<Validation_I<OrderedProduct>> validators;

	public OrderedProductBLL() {
		validators = new ArrayList<Validation_I<OrderedProduct>>();
		validators.add(new QuantityValidation());
	}

	public List<OrderedProduct> findAllOrderedProducts() {
		List<OrderedProduct> products = new ArrayList<OrderedProduct>();
		products = OrderedProductDAO.findAll();
		if (products.isEmpty()) {
			throw new IllegalArgumentException("The list of ordered products is empty!");
		}
		return products;
	}

	public int insertOrderedProduct(OrderedProduct product) {
		for (Validation_I<OrderedProduct> v : validators) {// mai intai validam
													// campurile date de user
			v.validate(product);
		}
		return OrderedProductDAO.insert(product);// apoi inseram in bd
	}

	

	public void deleteOrderedProduct(int productId) {
		// la stergere nu am ce valida
		OrderedProductDAO.delete(productId);

	}
}
