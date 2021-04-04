package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import org.postgresql.util.DriverInfo;

public class JdbcConnection {

	private static Optional<Connection> connection = Optional.empty();

	public static Optional<Connection> getConnection() {
		if (!connection.isPresent()) {
			String url = "jdbc:postgresql://localhost:8080/";
			String user = "postgres";
			String password = " ";

			try {
				Class.forName("org.postgresql.Driver");
				connection = Optional.ofNullable(DriverManager.getConnection(url, user, password));
				String sf = DriverInfo.DRIVER_FULL_NAME;
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return connection;
	}
}
