import java.net.Socket;
import java.util.ArrayList;

public class Team {
	private Flag  flag;
	private String color;
	private int maxPlayers;
	private GeoCoordinate base;
	private ArrayList<Player> players;
	
	public Team(Flag flag, String color,int maxPlayers,GeoCoordinate base) {
		this.players = new ArrayList<Player>();
		this.maxPlayers = maxPlayers;
		this.flag = flag;
		this.color = color;
		this.base = base;
	}
	public void addPlayer(Player player) throws Exception {
		if(players.size() >= maxPlayers) {
			throw new Exception("TEAM_ALREADY_FULL");
		}else{
			players.add(player);
		}
	}
	public Player getPlayerBySocket(Socket socket) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getConnection() == socket) {
				return players.get(i);
			}
		}
		return null;
	}
}
