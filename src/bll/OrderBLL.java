package bll;

import java.util.ArrayList;
import java.util.List;

import model.Order;
import bll.validare.Validation_I;
import dao.OrderDAO;

public class OrderBLL {
	private List<Validation_I<Order>> validators;

	public OrderBLL() {
		validators = new ArrayList<Validation_I<Order>>();

	}

	public List<Order> findAllOrders() {
		List<Order> orders = new ArrayList<Order>();
		orders = OrderDAO.findAll();
		if (orders.isEmpty()) {
			throw new IllegalArgumentException("The list of orders is empty!");
		}
		return orders;
	}

	public int insertOrder(Order order) {
		for (Validation_I<Order> v : validators) {// mai intai validam
													// campurile date de user
			v.validate(order);
		}
		return OrderDAO.insert(order);// apoi inseram in bd
	}
	

}
