package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Order;
import model.Product;
import connection.ConnectionClass;

public class OrderDAO {
	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class
			.getName());
	// definim stringurile cu interogarile
	private static final String insertStatementString = "INSERT INTO orders(idClient, totalComenzi)"
			+ " VALUES (?,?)";
	private static final String findAllStatementString = "SELECT * from orders";

	
	public static List<Order> findAll() {
		// TODO: creez o lista de comenzi
		List<Order> orders = new ArrayList<Order>();
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
				int idClient = rs.getInt("idClient");
				int totalComenzi = rs.getInt("totalComenzi");

				Order or = new Order(id, idClient, totalComenzi);
				orders.add(or);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:findAll " + e.getMessage());

		} finally {
			ConnectionClass.close(rs);
			ConnectionClass.close(findAllStatement);
			ConnectionClass.close(dbConnection);
		}
		return orders;
	}
	
	public static int insert(Order order) {
		Connection dbConnection = ConnectionClass.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(
					insertStatementString, Statement.RETURN_GENERATED_KEYS);
			// setam pe rand parametri din insertStatement
			//insertStatement.setInt(1, order.getId());
			insertStatement.setInt(1, order.getIdClient());
			insertStatement.setInt(2, order.getTotalComenzi());
			
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
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionClass.close(insertStatement);
			ConnectionClass.close(dbConnection);
		}
		return insertedId;
	}
}
