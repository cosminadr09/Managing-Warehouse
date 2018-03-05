package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;

import model.Client;
import connection.ConnectionClass;

public class ClientDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class
			.getName());
	// definim stringurile cu interogarile
	private static final String insertStatementString = "INSERT INTO client (nume, adresa, email)"
			+ " VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM client where id = ?";

	private static final String deleteStatementString = "DELETE from client where id= ?";
	private static final String updateStatementString = "UPDATE client set nume=?, adresa=?, email=? where id=?";
	private static final String findAllStatementString = "SELECT * from client";

	public static Client findById(int clientId) {
		Client toReturn = null;
		// creez oconexiune la bd
		Connection dbConnection = ConnectionClass.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			// init query pt a fi trimisa catre bd
			findStatement = dbConnection.prepareStatement(findStatementString);
			// setez primul parametru din findStatement ca fiind StudentID dat
			findStatement.setLong(1, clientId);
			// executam query
			rs = findStatement.executeQuery();
			// in urma executeQUery obtinem un resultSet
			rs.next();
			// luam din resultSet valorile si creez un nou obiect cu acele
			// valori
			String name = rs.getString("nume");
			String address = rs.getString("adresa");
			String email = rs.getString("email");

			toReturn = new Client(name, address, email);
			toReturn.setId(clientId);


		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionClass.close(rs);
			ConnectionClass.close(findStatement);
			ConnectionClass.close(dbConnection);
		}
		return toReturn;
	}

	public static List<Client> findAll() {
		// TODO: creez o lista de clienti
		List<Client> clients = new ArrayList<Client>();
		Connection dbConnection = ConnectionClass.getConnection();

		PreparedStatement findAllStatement = null;
		ResultSet rs = null;
		try {
			// nu avem parametri deci executam query pt a gasi toti clientii
			findAllStatement = dbConnection
					.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("nume");
				String address = rs.getString("adresa");
				String email = rs.getString("email");

				Client cl = new Client(id, name, address, email);
				clients.add(cl);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findAll " + e.getMessage());

		} finally {
			ConnectionClass.close(rs);
			ConnectionClass.close(findAllStatement);
			ConnectionClass.close(dbConnection);
		}
		return clients;
	}

	public static int insert(Client client) {
		Connection dbConnection = ConnectionClass.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(
					insertStatementString, Statement.RETURN_GENERATED_KEYS);
			// setam pe rand parametri din insertStatement
			insertStatement.setString(1, client.getNume());
			insertStatement.setString(2, client.getAdresa());
			insertStatement.setString(3, client.getEmail());

			// executeUpdate folosim pt a face INSERT,UPDATE,DETELE
			insertStatement.executeUpdate();

			// dupa executie, se geenreaza cheile/valorile ce vor alcatui un
			// resultSet
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				// iteram peste resultSet - true if the new current row is
				// valid; false if there are no more rows
				insertedId = rs.getInt(1);// returneaza val de pe coloana 1,
											// adica id
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionClass.close(insertStatement);
			ConnectionClass.close(dbConnection);
		}
		return insertedId;
	}

	public static void delete(int clientId) {
		Connection dbConnection = ConnectionClass.getConnection();

		PreparedStatement deleteStatement = null;
		try {
			deleteStatement = dbConnection
					.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, clientId);

			deleteStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionClass.close(deleteStatement);
			ConnectionClass.close(dbConnection);
		}
	}

	public static int update(Client client) {
		Connection dbConnection = ConnectionClass.getConnection();

		PreparedStatement updateStatement = null;
		int updatedId = -1;
		try {
			updateStatement = dbConnection.prepareStatement(
					updateStatementString);
			updateStatement.setString(1, client.getNume());
			updateStatement.setString(2, client.getAdresa());
			updateStatement.setString(3, client.getEmail());
			updateStatement.setInt(4, client.getId());
			//updateStatement.setString(4, client.getNume());
			
			updateStatement.executeUpdate();
		

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionClass.close(updateStatement);
			ConnectionClass.close(dbConnection);
		}
		return updatedId;
	}

}
