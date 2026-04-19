package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fournit la connexion JDBC à la base MySQL.
 *
 * Les paramètres peuvent être surchargés via variables d'environnement
 * (DB_URL, DB_USER, DB_PASSWORD) — utile en docker. Sinon valeurs par
 * défaut alignées sur le docker-compose.yml du projet.
 */
public class DBConnection {

	private static final String URL;
	private static final String USER;
	private static final String PASSWORD;

	static {
		URL = getEnv("DB_URL",
				"jdbc:mysql://localhost:3306/gesusers?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
		USER = getEnv("DB_USER", "gesusers");
		PASSWORD = getEnv("DB_PASSWORD", "gesusers");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver MySQL introuvable dans WEB-INF/lib", e);
		}
	}

	private static String getEnv(String key, String defaultValue) {
		String v = System.getenv(key);
		if (v == null || v.isEmpty()) {
			v = System.getProperty(key);
		}
		return (v == null || v.isEmpty()) ? defaultValue : v;
	}

	public static Connection get() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	private DBConnection() {
	}
}
