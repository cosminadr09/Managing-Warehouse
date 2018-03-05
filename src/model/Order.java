package model;

public class Order {
	private int id;
	private int idClient;
	private int totalComenzi;
	public Order(int id, int idClient, int totalComenzi) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.totalComenzi = totalComenzi;
	}
	public Order(int idClient, int totalComenzi) {
		super();
		this.idClient = idClient;
		this.totalComenzi = totalComenzi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getTotalComenzi() {
		return totalComenzi;
	}
	public void setTotalComenzi(int totalComenzi) {
		this.totalComenzi = totalComenzi;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", idClient=" + idClient + ", totalComenzi="
				+ totalComenzi + "]";
	}
	
	
}
