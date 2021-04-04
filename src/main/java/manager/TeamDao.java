package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import model.Players;

public class TeamDao implements IDao {

	private final Optional<Connection> connection;

	public TeamDao() {
		super();
		this.connection = JdbcConnection.getConnection();
	}

	@Override
	public Collection<Players> get(int id) {
		Collection<Players> playerList = new ArrayList<>();
		String sql = "SELECT * FROM \"players\" WHERE \"teamid\" = " + id;

		connection.ifPresent(conn -> {
			try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

				while (resultSet.next()) {
					String playerName = resultSet.getString("playername");
					String role = resultSet.getString("role");
					String nation = resultSet.getString("nationality");

					Players player = new Players(playerName, role, nation, id);

					playerList.add(player);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		return playerList;
	}

	@Override
	public Collection<Players> getAll() {
		Collection<Players> playerList = new ArrayList<>();
		String sql = "SELECT * FROM \"players\" ORDER BY \"teamid\"";

		connection.ifPresent(conn -> {
			try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

				while (resultSet.next()) {
					String playerName = resultSet.getString("playername");
					String role = resultSet.getString("role");
					String nation = resultSet.getString("nationality");
					int teamId = resultSet.getInt("teamid");
					Players player = new Players(playerName, role, nation, teamId);
					playerList.add(player);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		return playerList;
	}

	@Override
	public void save(Players newPlayer) {
		System.out.println(newPlayer.toString());
		Collection<Players> playerList = this.get(newPlayer.getTeamId());
		int foreign = (int) playerList.parallelStream().filter(element -> element.getNationality() != "tr").count();
		int goalKeeper = (int) playerList.parallelStream().filter(element -> element.getRole() != "gk").count();
		if (playerList.size() > 18 || foreign > 6 || goalKeeper > 2) {
			return;
		}

		String sql = "INSERT INTO players (playername, role, nationality, teamid) VALUES (" + newPlayer.getPlayerName()
				+ "," + newPlayer.getRole() + "," + newPlayer.getNationality() + "," + newPlayer.getTeamId() + ")";

		connection.flatMap(conn -> {

			try (PreparedStatement statement = conn.prepareStatement(sql)) {

				statement.execute();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			return Optional.of(newPlayer);
		});
	}

	@Override
	public boolean update(Players newPlayer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTeam(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
