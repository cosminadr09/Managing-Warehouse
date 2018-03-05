package model;

public class Client {
	private int id;
	private String nume;
	private String adresa;
	private String email;

	public Client(int id, String nume, String adresa, String email) {
		super();
		this.id = id;
		this.nume = nume;
		this.adresa = adresa;
		this.email = email;
	}

	public Client(String nume, String adresa, String email) {
		super();
		this.nume = nume;
		this.adresa = adresa;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nume=" + nume + ", adresa=" + adresa
				+ ", email=" + email + "]";
	}

}
