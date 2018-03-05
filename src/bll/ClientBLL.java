package bll;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import model.Client;
import bll.validare.Validation_I;
import bll.validare.EmailValidation;
import dao.ClientDAO;

public class ClientBLL {
	private List<Validation_I<Client>> validators;

	public ClientBLL() {
		validators = new ArrayList<Validation_I<Client>>();
		validators.add(new EmailValidation());
	}

	public Client findClientById(int id) {
		Client cl = ClientDAO.findById(id);// am gasit clientul cu acest id
		if (cl == null) {
			throw new NoSuchElementException("The client with id =" + id
					+ " was not found!");
		}
		return cl;
	}

	public List<Client> findAllClients() {
		List<Client> clients = new ArrayList<Client>();
		clients = ClientDAO.findAll();
		if (clients.isEmpty()) {
			throw new IllegalArgumentException("The list of clients is empty!");
		}
		return clients;
	}

	public int insertClient(Client client) {
		for (Validation_I<Client> v : validators) {// mai intai validam
													// campurile date de user
			v.validate(client);
		}
		return ClientDAO.insert(client);// apoi inseram in bd
	}

	public void updateClient(Client client) {
		// mai intai validam
		// campurile date de user
		// validez doar email
		for (Validation_I<Client> v : validators) {
			v.validate(client);
		}
		ClientDAO.update(client);
	}

	public void deleteClient(int clientId) {
		// la stergere nu am ce valida
		ClientDAO.delete(clientId);

	}
	public JTable createTable(){
		
		String[] headtable = { "id", "nume", "adresa", "email" };
		List<Client> clients = new ArrayList<Client>();
		clients=this.findAllClients();
		//nr linii=nr clienti, nr coloane=nr fielduri ale obj
		Object[][] list = new Object[clients.size()][4];
		Object[] headers = new Object[headtable.length];
		int i=0;
		for(Client c: clients){
			list[i][0]=c.getId();
			list[i][1]=c.getNume();
			list[i][2]=c.getAdresa();
			list[i][3]=c.getEmail();
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
		
		TableModel model = new DefaultTableModel(list, headers); 
		JTable jt = new JTable(model);
		
		return jt;

	}
}
