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

import model.OrderedProduct;
import model.Product;
import connection.ConnectionClass;

public class OrderedProductDAO {
	
	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class
			.getName());
	// definim stringurile cu interogarile
	
	private static final String insertStatementString = "INSERT INTO orderedProduct (idOrder, idProduct, quantity)"
			+ " VALUES (?,?,?)";
	private static final String deleteStatementString = "DELETE from orderedProduct where id= ?";

	private static final String findAllStatementString = "SELECT * from orderedProduct";

	public static List<OrderedProduct> findAll() {
		// TODO: creez o lista de clienti
		List<OrderedProduct> products = new ArrayList<OrderedProduct>();
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
				int idOrder = rs.getInt("idOrder");
				int idProduct = rs.getInt("idProduct");
				int quantity = rs.getInt("quantity");

				OrderedProduct pr = new OrderedProduct(id, idOrder, idProduct, quantity);
				products.add(pr);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderedProductDAO:findAll " + e.getMessage());

		} finally {
			ConnectionClass.close(rs);
			ConnectionClass.close(findAllStatement);
			ConnectionClass.close(dbConnection);
		}
		return products;
	}
	public static int insert(OrderedProduct product) {
		Connection dbConnection = ConnectionClass.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		int updateStoc = -1, currentStoc = 0;
		try {
			insertStatement = dbConnection.prepareStatement(
					insertStatementString, Statement.RETURN_GENERATED_KEYS);
			// setam pe rand parametri din insertStatement
			insertStatement.setInt(1, product.getIdOrder());
			insertStatement.setInt(2, product.getIdProduct());
			insertStatement.setInt(3, product.getQuantity());
			//e nevoie sa parsez la String?
			
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
				//sa fac update la cantitate
				//currentStoc = rs.getInt(3);//stoc curent
				
				
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderedProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionClass.close(insertStatement);
			ConnectionClass.close(dbConnection);
		}
		return insertedId;
	}
	public static void delete(int productId) {
		Connection dbConnection = ConnectionClass.getConnection();

		PreparedStatement deleteStatement = null;
		try {
			deleteStatement = dbConnection
					.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, productId);

			deleteStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderedProductDAO:delete " + e.getMessage());
		} finally {
			ConnectionClass.close(deleteStatement);
			ConnectionClass.close(dbConnection);
		}
	}
}
