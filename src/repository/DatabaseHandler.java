package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
/**
 * Klasse DatabaseHandler
 * Ist als Singelton Pattern realisiert
 * Wenn es eine Instance gibt wird diese zurück gegeben
 * Wenn die Connection null ist wird eine erstellt 
 * sollte eine Connection vorhanden sein wird diese zurück gegeben 
 */
	private static DatabaseHandler instance = null;

	private static Connection connection = null;

	private static final String PROPERTY_URL = "jdbc:mysql://langnerg86.mysql.univie.ac.at/langnerg86?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private static final String PROPERTY_DRIVER = "com.mysql.cj.jdbc.Driver";

	private static final String PROPERTY_USERNAME = "langnerg86";

	private static final String PROPERTY_PASSWORD = "dbkzlxlq1";
	private DatabaseHandler() {
	}

	public static Connection getConnection() throws SQLException {

		if (connection == null) {

			try {

				Class.forName(PROPERTY_DRIVER);

			} catch (ClassNotFoundException e) {

				// LOGGER.log(Level.SEVERE, "MySQL JDBC-Driver could not be
				// loaded!", e);

			}

			connection = DriverManager.getConnection(PROPERTY_URL, PROPERTY_USERNAME, PROPERTY_PASSWORD);

		}

		return connection;

	}

	public static void closeConnection() throws SQLException {

		try {

			if (connection != null) {

				connection.close();

				// LOGGER.info("DatabaseHandler: Connection was closed");

			}

		} catch (SQLException e) {

			// LOGGER.severe("DatabaseHandler: " + e.getMessage());

			throw new SQLException(e + "");

		}

	}

	public static DatabaseHandler getInstance() {

		if (instance == null) {

			instance = new DatabaseHandler();

		}

		return instance;

	}
}
