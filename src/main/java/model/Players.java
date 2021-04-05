package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Players {

	private @Id String playername;
	private String role;
	private String nationality;
	private int teamid;
	private String teamname;

	public Players(String playerName, String role, String nationality, int teamId, String teamname) {
		this.setPlayername(playerName);
		this.setRole(role);
		this.setNationality(nationality);
		this.setTeamid(teamId);
		this.setTeamname(teamname);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "player [playername=" + this.playername + ", role=" + this.role + ", nationality =" + this.nationality
				+ ", teamid =" + this.teamid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		result = prime * result + ((playername == null) ? 0 : playername.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + teamid;
		result = prime * result + ((teamname == null) ? 0 : teamname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Players other = (Players) obj;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		if (playername == null) {
			if (other.playername != null)
				return false;
		} else if (!playername.equals(other.playername))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (teamid != other.teamid)
			return false;
		if (teamname == null) {
			if (other.teamname != null)
				return false;
		} else if (!teamname.equals(other.teamname))
			return false;
		return true;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public int getTeamid() {
		return teamid;
	}

	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

}
