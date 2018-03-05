package model;

public class OrderedProduct {
	private int id;
	private int idOrder;
	private int idProduct;
	private int quantity;

	public OrderedProduct(int id, int idOrder, int idProduct, int quantity) {
		super();
		this.id = id;
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public OrderedProduct(int idOrder, int idProduct, int quantity) {
		super();
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderedProduct [id=" + id + ", idOrder=" + idOrder
				+ ", idProduct=" + idProduct + ", quantity=" + quantity + "]";
	}

}
