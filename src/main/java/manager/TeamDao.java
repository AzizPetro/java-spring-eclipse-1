package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
					String teamName = resultSet.getString("teamname");

					Players player = new Players(playerName, role, nation, id, teamName);

					playerList.add(player);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		return playerList;
	}

	@Override
	public Collection<String> getAll() {
		Set<String> playerList = new HashSet<String>();
		String sql = "SELECT teamname FROM \"players\" ORDER BY \"teamid\"";

		connection.ifPresent(conn -> {
			try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

				while (resultSet.next()) {
					String teamname = resultSet.getString("teamname");
					playerList.add(teamname);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		return playerList;
	}

	@Override
	public void save(Players newPlayer) {
		Collection<Players> playerList = this.get(newPlayer.getTeamid());
		int foreign = (int) playerList.parallelStream().filter(element -> !element.getNationality().equals("tr"))
				.count();
		int goalKeeper = (int) playerList.parallelStream().filter(element -> element.getRole().equals("gk")).count();
		if (playerList.size() > 18 || (foreign >= 6 && !newPlayer.getNationality().equals("tr"))
				|| (goalKeeper >= 2 && newPlayer.getRole().equals("gk"))) {
			return;
		}
		String sql = "INSERT INTO players (playername, role, nationality, teamid, teamname) VALUES (" + "'"
				+ newPlayer.getPlayername() + "'" + ",'" + newPlayer.getRole() + "','" + newPlayer.getNationality()
				+ "'," + newPlayer.getTeamid() + ",'" + newPlayer.getTeamname() + "')";
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
	public void deleteTeam(int id) {
		String sql = "DELETE FROM \"players\" WHERE \"teamid\" = " + id;

		connection.ifPresent(conn -> {
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.execute();
			}

			catch (SQLException ex) {
				ex.printStackTrace();
			}
		});
	}

}
