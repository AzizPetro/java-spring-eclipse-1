package controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manager.IDao;
import manager.TeamDao;
import model.Players;

@RestController
@RequestMapping("/")
public class MainController {
	private IDao teamDao = new TeamDao();

	public MainController() {
	}

	@GetMapping("/")
	public Collection<Players> home() {
		return this.teamDao.getAll();

	}

	@GetMapping("/teams")
	public Collection<Players> getTeams() {
		return this.teamDao.getAll();
	}

	@GetMapping("/teams/{id}")
	public Collection<Players> getTeam(@PathVariable int id) {
		return this.teamDao.get(id);
	}

	@PostMapping("/teams/add")
	public void add(@RequestBody Players newPlayer) {
		this.teamDao.save(newPlayer);
	}

	@DeleteMapping("/teams/{id}")
	public void deleteTeam(@PathVariable int id) {
		this.teamDao.deleteTeam(id);
	}

}
