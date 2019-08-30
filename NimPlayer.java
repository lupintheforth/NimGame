
import java.io.Serializable;
/************************************************************
 * Name:Yizhou ZHU
 * login name:yizzhu@nutmeg.eng.unimelb.edu.au
 * student number:1034676
 * 
 * description: this is the main class to run the Nimsys and store the array of NimPlayer
 * 
 * last updated:06/05/2019
 * 
 * 
 * 
 ************************************************************/


public abstract class NimPlayer implements Serializable  {


	private String username;
	private String firstname;
	private String lastname;
	private int GamesPlayed;
	private int GamesWon;
	
	public NimPlayer() {
		this.firstname ="no name";
		this.lastname = "no name";
		this.username = " ";
		this.GamesPlayed = 0;
		this.GamesWon = 0;
	}
	
	
	public NimPlayer (String username, String firstname, String lastname) {
		setPlayer(username, firstname, lastname);
	}
	
	public void setPlayer(String username, String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.GamesPlayed = 0;
		this.GamesWon = 0;
	}
	
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getFirstname() {
		return this.firstname;
	}
	
	public String getLastname() {
		return this.lastname;
	}
	
	public int getGamesPlayed() {
		return this.GamesPlayed;
	}
	
	public int getGamesWon() {
		return this.GamesWon;
	}
	
	public NimPlayer getPlayer(NimPlayer theNimPlayer) {
		return theNimPlayer;
	}
	

	
	public boolean UserNameequals(String otherusername) {
		return this.getUsername().equals(otherusername);
	}
	
	public void winGame() {
		this.GamesPlayed = this.GamesPlayed + 1;
		this.GamesWon = this.GamesWon + 1;
	}
	
	public void loseGame() {
		this.GamesPlayed = this.GamesPlayed +1;
	}
	
	public abstract int removeStone(int i,int maxremovestone,int stoneleft);
	
	public String advancedMove(boolean[] available, String lastMove) {
		return (" ");
	} 
	
	public void copyNimPlayer(NimPlayer otherPlayer) {
		this.firstname = otherPlayer.getFirstname();
		this.lastname = otherPlayer.getLastname();
		this.username = otherPlayer.getUsername();
		this.GamesWon = otherPlayer.getGamesWon();
		this.GamesPlayed = otherPlayer.getGamesPlayed();
	}




}
