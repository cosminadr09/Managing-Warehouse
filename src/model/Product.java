package model;

public class Product {
	private int id;
	private String denumire;
	private double pret;
	private int cantitateMagazin;

	public Product(int id, String denumire, double pret, int cantitateMagazin) {
		super();
		this.id = id;
		this.denumire = denumire;
		this.pret = pret;
		this.cantitateMagazin = cantitateMagazin;
	}

	public Product(String denumire, double pret, int cantitateMagazin) {
		super();
		this.denumire = denumire;
		this.pret = pret;
		this.cantitateMagazin = cantitateMagazin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

	public int getCantitateMagazin() {
		return cantitateMagazin;
	}

	public void setCantitateMagazin(int cantitateMagazin) {
		this.cantitateMagazin = cantitateMagazin;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", denumire=" + denumire + ", pret="
				+ pret + ", cantitateMagazin=" + cantitateMagazin + "]";
	}
	
}
