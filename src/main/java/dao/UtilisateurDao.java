package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Utilisateur;

public class UtilisateurDao {

	private static final String COLS = "id, nom, prenom, login, password, role";

	public static boolean ajouter(Utilisateur u) {
		String sql = "INSERT INTO utilisateurs (nom, prenom, login, password, role) VALUES (?, ?, ?, ?, ?)";
		try (Connection cn = DBConnection.get();
				PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, u.getNom());
			ps.setString(2, u.getPrenom());
			ps.setString(3, u.getLogin());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getRole() == null ? "USER" : u.getRole());
			if (ps.executeUpdate() == 0) return false;
			try (ResultSet keys = ps.getGeneratedKeys()) {
				if (keys.next()) u.setId(keys.getInt(1));
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean modifier(Utilisateur u) {
		String sql = "UPDATE utilisateurs SET nom = ?, prenom = ?, login = ?, password = ?, role = ? WHERE id = ?";
		try (Connection cn = DBConnection.get();
				PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, u.getNom());
			ps.setString(2, u.getPrenom());
			ps.setString(3, u.getLogin());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getRole() == null ? "USER" : u.getRole());
			ps.setInt(6, u.getId());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean supprimer(int id) {
		String sql = "DELETE FROM utilisateurs WHERE id = ?";
		try (Connection cn = DBConnection.get();
				PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<Utilisateur> lister() {
		ArrayList<Utilisateur> result = new ArrayList<>();
		String sql = "SELECT " + COLS + " FROM utilisateurs ORDER BY id";
		try (Connection cn = DBConnection.get();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) result.add(map(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Utilisateur get(int id) {
		String sql = "SELECT " + COLS + " FROM utilisateurs WHERE id = ?";
		try (Connection cn = DBConnection.get();
				PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) return map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Utilisateur authentifier(String login, String password) {
		String sql = "SELECT " + COLS + " FROM utilisateurs WHERE login = ? AND password = ?";
		try (Connection cn = DBConnection.get();
				PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, login);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) return map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Utilisateur map(ResultSet rs) throws SQLException {
		return new Utilisateur(
				rs.getInt("id"),
				rs.getString("nom"),
				rs.getString("prenom"),
				rs.getString("login"),
				rs.getString("password"),
				rs.getString("role"));
	}
}
