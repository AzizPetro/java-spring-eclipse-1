package manager;

import java.util.Collection;
import java.util.Optional;

import model.Players;

public interface IDao {
	Collection<Players> get(int id);

	Collection<Players> getAll();

	void save(Players newPlayer);

	boolean update(Players newPlayer);

	boolean deleteTeam(int id);
}
