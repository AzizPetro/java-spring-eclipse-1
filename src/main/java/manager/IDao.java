package manager;

import java.util.Collection;
import model.Players;

public interface IDao {
	Collection<Players> get(int id);

	Collection<String> getAll();

	void save(Players newPlayer);

	void deleteTeam(int id);
}
