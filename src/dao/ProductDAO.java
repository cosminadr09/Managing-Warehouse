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

import model.Client;
import model.Product;
import connection.ConnectionClass;

public class ProductDAO {
	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class
			.getName());
	// definim stringurile cu interogarile
	private static final String insertStatementString = "INSERT INTO product (denumire, pret, cantitateMagazin)"
			+ " VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM product where id = ?";

	private static final String deleteStatementString = "DELETE from product where id= ?";
	private static final String updateStatementString = "UPDATE product set denumire=?, pret=?, cantitateMagazin=? where id=?";
	private static final String findAllStatementString = "SELECT * from product";
	
	public static Product findById(int productId) {
		Product toReturn = null;
		// creez oconexiune la bd
		Connection dbConnection = ConnectionClass.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			// init query pt a fi trimisa catre bd
			findStatement = dbConnection.prepareStatement(findStatementString);
			// setez primul parametru din findStatement ca fiind productID dat
			findStatement.setLong(1, productId);
			// executam query
			rs = findStatement.executeQuery();
			// in urma executeQUery obtinem un resultSet
			rs.next();
			// luam din resultSet valorile si creez un nou obiect cu acele
			// valori
			String denumire = rs.getString("denumire");
			double pret = rs.getDouble("pret");
			int cantitateMagazin = rs.getInt("cantitateMagazin");

			toReturn = new Product(denumire, pret, cantitateMagazin);
			toReturn.setId(productId);


		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:findById " + e.getMessage());
		} finally {
			ConnectionClass.close(rs);
			ConnectionClass.close(findStatement);
			ConnectionClass.close(dbConnection);
		}
		return toReturn;
	}
	public static List<Product> findAll() {
		// TODO: creez o lista de clienti
		List<Product> products = new ArrayList<Product>();
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
				String denumire = rs.getString("denumire");
				double pret = rs.getDouble("pret");
				int cantitateMagazin = rs.getInt("cantitateMagazin");

				Product pr = new Product(id, denumire, pret, cantitateMagazin);
				products.add(pr);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:findAll " + e.getMessage());

		} finally {
			ConnectionClass.close(rs);
			ConnectionClass.close(findAllStatement);
			ConnectionClass.close(dbConnection);
		}
		return products;
	}
	public static int insert(Product product) {
		Connection dbConnection = ConnectionClass.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(
					insertStatementString, Statement.RETURN_GENERATED_KEYS);
			// setam pe rand parametri din insertStatement
			insertStatement.setString(1, product.getDenumire());
			insertStatement.setDouble(2, product.getPret());
			insertStatement.setInt(3, product.getCantitateMagazin());
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
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
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
			LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
		} finally {
			ConnectionClass.close(deleteStatement);
			ConnectionClass.close(dbConnection);
		}
	}
	public static int update(Product product) {
		Connection dbConnection = ConnectionClass.getConnection();

		PreparedStatement updateStatement = null;
		int updatedId = -1;
		try {
			updateStatement = dbConnection.prepareStatement(
					updateStatementString);
			updateStatement.setString(1, product.getDenumire());
			updateStatement.setDouble(2, product.getPret());
			updateStatement.setInt(3, product.getCantitateMagazin());
			updateStatement.setInt(4, product.getId());
			//updateStatement.setString(4, client.getNume());
			
			updateStatement.executeUpdate();
		

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
		} finally {
			ConnectionClass.close(updateStatement);
			ConnectionClass.close(dbConnection);
		}
		return updatedId;
	}
}
