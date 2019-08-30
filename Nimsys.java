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
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;


import java.util.Scanner;

public class Nimsys {
	
	private static  NimPlayer[] NimPlayerArray; // set the Array static to make it the ONE!
	
	private  Nimsys() {
		NimPlayerArray = new NimPlayer [100];
		for (int i=0; i< NimPlayerArray.length;i++) { // constructor, initialize the ONLY NimPlayerArray
			NimPlayerArray[i] = new NimHumanPlayer();
		}
	}
		
	public  NimPlayer[] getArray() { 
		return NimPlayerArray;// accessor
	}
	
	public static NimPlayer getPlayer(int i) {
		return NimPlayerArray[i]; // accessor
	}

	

		
	
	public static int existPlayer(String username) { // judge the existence of the player. if exist, return the index, otherwise return -1 
		for (int i=0; i< NimPlayerArray.length;i++) 
			if(NimPlayerArray[i].UserNameequals(username)) {
				return i;
			}
			
		return -1;
		
		}
	
	private void addAIPlayer(String username, String Firstname, String Lastname) {// add one player to the array
		for (int i=0; i< NimPlayerArray.length;i++) {// go through the array to see duplicated username. Once found, print information and end the method
			if (NimPlayerArray[i].UserNameequals(username)) {
				System.out.println("The player already exists.");
				return;
			}
		}
		for (int i=0; i< NimPlayerArray.length;i++) {	
		if	(NimPlayerArray[i].UserNameequals(" ")) {// add player to the first vacancy
				NimPlayerArray[i] = new NimAIPlayer();
				NimPlayerArray[i].setPlayer(username,Firstname,Lastname);
				break;
			}
			
		
		}
		
	}
	
	
	private void addPlayer(String username, String Firstname, String Lastname) {// add one player to the array
		for (int i=0; i< NimPlayerArray.length;i++) {// go through the array to see duplicated username. Once found, print information and end the method
			if (NimPlayerArray[i].UserNameequals(username)) {
				System.out.println("The player already exists.");
				return;
			}
		}
		for (int i=0; i< NimPlayerArray.length;i++) {	
		if	(NimPlayerArray[i].UserNameequals(" ")) {// add player to the first vacancy
				NimPlayerArray[i] = new NimHumanPlayer();
				NimPlayerArray[i].setPlayer(username,Firstname,Lastname);
				break;
			}
			
		
		}
		
	}
	
	private  void removePlayer(String username) {
		for (int i=0; i< NimPlayerArray.length;i++) {
			if (NimPlayerArray[i].UserNameequals(username)) {// find the matching username to restore it to the initial value
				NimPlayerArray[i].setPlayer(" ","no name","no name");
				return;
			}
		}
		 												// if the last player in the array is not matched, print the information(although I know it will be redundant condition)
				System.out.println("The player does not exist.");
		
	}

	
	private void removePlayer() {
		
		System.out.println("Are you sure you want to remove all players? (y/n)");// double-check
		String judge =keyboard.nextLine();
		if (judge.equals("y"))
			for (int i=0; i< NimPlayerArray.length;i++) {
					NimPlayerArray[i].setPlayer(" ","no firstname","no lastname");// set all of them to the initial value
			}
		
	}
	
	private void displayPlayer(String username) {
		for (int i =0; i < NimPlayerArray.length;i++) {
			if (NimPlayerArray[i].UserNameequals(username)) {// find the matching one and display the information
				System.out.println(NimPlayerArray[i].getUsername()+","+NimPlayerArray[i].getLastname()+","+
				NimPlayerArray[i].getFirstname()+","
				+NimPlayerArray[i].getGamesPlayed()+" games,"+NimPlayerArray[i].getGamesWon()+" wins");
				return;// end the method as there will only be one matching object in this case
			}
		
			
		}
			System.out.println("The player does not exist.");

	}
	
	
	private void editPlayer(String username, String newfirstname, String newlastname) {
		for (int i =0; i < NimPlayerArray.length;i++) {
			if (NimPlayerArray[i].UserNameequals(username)) {
				NimPlayerArray[i].setFirstname(newfirstname);
				NimPlayerArray[i].setLastname(newlastname);// set theme with the new name but retain the username
				return;
			}
		}
		
			System.out.println("The player does not exist.");

	}
	
	private  void Insertionsort() {// selection sort part1
		int i,iofNextSmallest;
		for (i=0; i<NimPlayerArray.length-1; i++) {
			iofNextSmallest = iofSmallest(i);
			interchange(i,iofNextSmallest);
		
		}
	}
	
	private int iofSmallest(int index) {// find the minimum object in this sorting condition and return the index of it
		NimPlayer min = NimPlayerArray[index];
		int iofMin = index;
		int i;
		for(i = index + 1; i<NimPlayerArray.length;i++) {
			if(NimPlayerArray[i].getUsername().compareTo(min.getUsername())<0){
				min = NimPlayerArray[i];
				iofMin = i;
			}
		}
		return iofMin;
	}
	
	private void interchange(int i,int j) {
		NimPlayer temp;
		temp = NimPlayerArray[i];
		NimPlayerArray[i] = NimPlayerArray[j];
		NimPlayerArray[j] = temp;
	}
					
	private int max(int a,int b) {//return the bigger number in the two integers
		if(a>=b) {
			return a;
		}
		else {
			return b;
		}
	}
	
	
	
	
	private  void InsertionsortVicDsc(){// following 4 methods are all parts of the sorting algorithm for the rankings, refer to the alphabetical sorting
		int iofNextSmallestrank;
		for (int i=0; i<NimPlayerArray.length-1; i++) {
			iofNextSmallestrank = iofSmallestrankvsc(i);
			interchange(i,iofNextSmallestrank);
		}
	}
	
	private int  iofSmallestrankvsc(int index) {
		NimPlayer min = NimPlayerArray[index];
		int iofMin = index;
		int i;
		for(i = index + 1; i<NimPlayerArray.length;i++) {
			double vici = ((double)NimPlayerArray[i].getGamesWon()/max(NimPlayerArray[i].getGamesPlayed(),1));
			double vicimin =((double)min.getGamesWon()/max(min.getGamesPlayed(),1)); /* As the initial vacancy have 0 games played, the insertion will return a problem if vici = 0/0, 
																														so i set the 0 one to zero but retain all the other games played.
			 																																									*/
			if(vici>vicimin) {
				min = NimPlayerArray[i];
				iofMin = i;
				}
			else if(vici == vicimin) {
				if(NimPlayerArray[i].getUsername().compareTo(min.getUsername())<0) {
					min = NimPlayerArray[i];
					iofMin = i;
				}
					
			}
		}
		return iofMin;
	}
	
	private  void InsertionsortVicAsc(){
		int iofNextSmallestrank;
		for (int i=0; i<NimPlayerArray.length-1; i++) {
			iofNextSmallestrank = iofSmallestrankasc(i);
			interchange(i,iofNextSmallestrank);
		}
	}
	
	
	private int  iofSmallestrankasc(int index){
		NimPlayer min = NimPlayerArray[index];
		int iofMin = index;
		int i;
		for(i = index + 1; i<NimPlayerArray.length;i++) {
			double vici = ((double)NimPlayerArray[i].getGamesWon()/max(NimPlayerArray[i].getGamesPlayed(),1));
			double vicimin =((double)min.getGamesWon()/max(min.getGamesPlayed(),1));
			if(vici<vicimin) {
				min = NimPlayerArray[i];
				iofMin = i;
				}
			else if(vici == vicimin) {
				if(NimPlayerArray[i].getUsername().compareTo(min.getUsername())<0) {
					min = NimPlayerArray[i];
					iofMin = i;
				}
					
			}
		}
		return iofMin;
	}
	
	
	
	
	
			
		
	

		
	
	
	private  void displayPlayer() {
		
		Insertionsort();// sort the array first
		for (int i =0; i < NimPlayerArray.length; i++) {
			if (NimPlayerArray[i].getUsername().compareTo(" ") != 0) { // As long as the object is not the initial value , display it;
				System.out.println(NimPlayerArray[i].getUsername()+","+NimPlayerArray[i].getLastname()+","+
				NimPlayerArray[i].getFirstname()+","
				+NimPlayerArray[i].getGamesPlayed()+" games,"+NimPlayerArray[i].getGamesWon()+" wins");
			}
		}
	}
	
	
	private  void resetPlayer(String username) {
		for (int i =0; i < NimPlayerArray.length;i++) {
			if (NimPlayerArray[i].UserNameequals(username)) {
				NimPlayerArray[i].setPlayer(username,NimPlayerArray[i].getFirstname(),NimPlayerArray[i].getLastname());// As the mutator in the NimPlayer will set the games stats to the 0, it works!
				return;
			}
		}
		
		System.out.println("The player does not exist.");
	
	}
	
	private void resetPlayer() {
		System.out.println("Are you sure you want to reset all player statistics? (y/n)");
		String judge =keyboard.nextLine();
		if (judge.equals("y"))
			for (int i=0; i< NimPlayerArray.length;i++) {
				NimPlayerArray[i].setPlayer(NimPlayerArray[i].getUsername(),NimPlayerArray[i].getFirstname(),NimPlayerArray[i].getLastname());
			}
	}
	
	private void displayrank() {
		for (int i =0; i < NimPlayerArray.length; i++) {
			if (NimPlayerArray[i].UserNameequals(" "))//skip the initial value in 
				continue;
				
			
			if (NimPlayerArray[i] != null) {
				double vicper = ((double)NimPlayerArray[i].getGamesWon()/max(NimPlayerArray[i].getGamesPlayed(),1))*100;
				System.out.printf("%-1.0f%%",vicper);
				if (vicper==100) {
					System.out.print(" | "); // for the different percentage returned , to keep the 5 characters spaces
				}
				else if(vicper<100 && vicper>= 10) {
					System.out.print("  | ");
				}
				else {
					System.out.print("   | ");
				}
				System.out.printf("%02d",NimPlayerArray[i].getGamesPlayed());
				System.out.print(" games | "+
				NimPlayerArray[i].getLastname()+" "+NimPlayerArray[i].getFirstname());
				System.out.println();
			}
		}
	}
			
	
	private void rankings() {
		InsertionsortVicDsc();
		displayrank();
	}
	
	private void rankingsasc() {
		InsertionsortVicAsc();
		displayrank();
	}
	
	
	public static Scanner keyboard = new Scanner(System.in);
		

	
	private void prompt() {
		System.out.println();
		System.out.print("$");
	}
	
	private void incorrectArgsNumber() throws Exception{
		
		throw new Exception("Incorrect number of arguments supplied to command.");
		
	}
	
	
	private void record() {
		try 
		{
			ObjectInputStream record = new ObjectInputStream(new FileInputStream("player.dat"));
			
			Object NimPlayerArrayrecord;
			
			try
			{
				
					NimPlayerArrayrecord = record.readObject();
					NimPlayerArray = (NimPlayer[]) NimPlayerArrayrecord;
				
			
				
			}
			
			catch(ClassNotFoundException e)
			{
				System.out.println("1");
			}
			
			catch(EOFException e)
			{
				System.out.println("2");
			}
			
			
			record.close();
		}
		catch(FileNotFoundException e)
			{
			
			}

		catch (IOException e)
		{
			String message = e.getMessage();
			System.out.println(message);
		
		}
	}
	
	
	private void save() {
		try
		{
			ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("player.dat"));
			save.writeObject(NimPlayerArray);
			save.flush();
			save.close();
			
			
		}
		catch(IOException e)
		{
			String message = e.getMessage();
			System.out.println(message);
		}
	}
	
	
	public static void main(String[] args) { // the main method is to judge what your input is to return the corresponding command
		
		
		
		
		
		
		System.out.println("Welcome to Nim");
		
			
		Nimsys newNimsys = new Nimsys();
		
		newNimsys.record();

		
		while(true) {
		newNimsys.prompt();
		
		String commandtoDo = keyboard.nextLine();
		String[] commandseq = commandtoDo.split(" ");
		
	
		
		
		
		
		
		try {
		
		
		if (commandseq[0].equals("exit"))
		{
			newNimsys.save();
			System.out.println();
			System.exit(0);
		}
		
		else if(commandseq[0].equals("startadvancedgame")) {
			NimGame newNimGame = new NimGame();
			String[] parameterSeq = commandseq[1].split(",");
			if(parameterSeq.length == 3)
				newNimGame.startadvancedgame(Integer.parseInt(parameterSeq[0]),parameterSeq[1], parameterSeq[2]);
			else
				newNimsys.incorrectArgsNumber();
			
			
			
		}
		
		
		
		
		
		
		
		
		
		else if(commandseq[0].equals("addplayer"))
		{
			if (commandseq.length == 2) {
				
				String[] parameterSeq = commandseq[1].split(",");
				if(parameterSeq.length == 3)
					newNimsys.addPlayer(parameterSeq[0], parameterSeq[1], parameterSeq[2]);
				else
					newNimsys.incorrectArgsNumber();
			}
		}
				
		else if(commandseq[0].equals("addaiplayer"))
		{
			if (commandseq.length == 2) {
				
				String[] parameterSeq = commandseq[1].split(",");
				if(parameterSeq.length == 3)
					newNimsys.addAIPlayer(parameterSeq[0], parameterSeq[1], parameterSeq[2]);
				else
					newNimsys.incorrectArgsNumber();
			}
		}
				
		
		
		
		
		
		
		else if(commandseq[0].equals("removeplayer"))
		{
			if (commandseq.length == 2)
				newNimsys.removePlayer(commandseq[1]);
			else
				newNimsys.removePlayer();
		}
		
		
		
		
		
		
		
		else if(commandseq[0].equals("editplayer")) 
		{
			if (commandseq.length ==2) {
				String[] parameterSeq = commandseq[1].split(",");
				if(parameterSeq.length == 3)
					newNimsys.editPlayer(parameterSeq[0], parameterSeq[1], parameterSeq[2]);
				else
					newNimsys.incorrectArgsNumber();
			}
		}
		
		
		
		
		
		
		else if(commandseq[0].equals("resetstats")) 
		{
			if(commandseq.length == 2) {
				newNimsys.resetPlayer(commandseq[1]);
			}
			else
				newNimsys.resetPlayer();
		}
		
		
		
		
		
		
		
		else if(commandseq[0].equals("displayplayer"))
		{	
			if(commandseq.length == 2) {
				newNimsys.displayPlayer(commandseq[1]);
			}
			else
				newNimsys.displayPlayer();
		}
		
		
		
		
		
		else if(commandseq[0].equals("rankings")) 
		{
			if(commandseq.length == 2) {
				if(commandseq[1].equals("desc")) {
					newNimsys.rankings();
				}
				else if(commandseq[1].equals("asc")) {
					newNimsys.rankingsasc();
				}
			}
			else
				newNimsys.rankings();
		}
		
		
		
		
		
		else if(commandseq[0].equals("startgame"))
		{
			if(commandseq.length == 2){  
				NimGame newNimGame = new NimGame();
				String[] parameterSeq = commandseq[1].split(",");
				if(parameterSeq.length == 4)
					newNimGame.startGame(Integer.parseInt(parameterSeq[0]), Integer.parseInt(parameterSeq[1]), parameterSeq[2], parameterSeq[3]);
				else
					newNimsys.incorrectArgsNumber();
				}
		}
		
		else
		{
			throw new Exception("'"+commandseq[0]+"' "+"is not a valid command.");
		}
		
		
		}
		
	
		
		catch(Exception e) {  
			String message = e.getMessage( );
			System.out.println(message);
			
		}
		
			
			
	
	
			}
		}
	}
