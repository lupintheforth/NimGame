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




public class NimGame {
	
	private int stonenum;
	private int bound;
	private  NimPlayer Player1;
	private NimPlayer Player2;
	
	public NimGame() {
		stonenum = 0;
		bound = 0;
		Player1 = new NimHumanPlayer();
		Player2 = new NimHumanPlayer();
		
		
	}
	
	
		
	
	public void startGame(int stonenum, int bound, String Player1username, String Player2username) { 
		
		
		
		if (Nimsys.existPlayer(Player1username) == (-1) || Nimsys.existPlayer(Player2username) == (-1)) {// judge the Player existence in the array
			System.out.println("One of the players does not exist.");
			return;
		}
		
		this.stonenum =stonenum;
		this.bound = bound;
			
		this.Player1 = Nimsys.getPlayer(Nimsys.existPlayer(Player1username));
		this.Player2 = Nimsys.getPlayer(Nimsys.existPlayer(Player2username));
		
		System.out.println();
		System.out.println("Initial stone count: "+this.stonenum);
		System.out.println("Maximum stone removal: "+this.bound);
		System.out.println("Player 1: "+Player1.getLastname()+" "+Player1.getFirstname());
		System.out.println("Player 2: "+Player2.getLastname()+" "+Player2.getFirstname());

		
		while(this.stonenum>=0)
		
		{
			
			gameprocess(Player1, Player2);
			if (this.stonenum == 0)
				break;
			gameprocess(Player2, Player1);
			if (this.stonenum == 0)
				break;
			
			
			
	
			
		}
	}
	
	private int min(int a,int b) {
		if(a>=b) {
		return b;
		}
		else {
		return a;
		}
	}
	
	private void gameprocess(NimPlayer Player, NimPlayer otherPlayer) 
	{	
		while (true) {
			
			
			
			System.out.println();
			System.out.print(stonenum+" stones left:");
			for (int sta = 1; sta<=stonenum; sta++)// print the asterik 
				System.out.print(" *"); 
			System.out.println();
			System.out.println(Player.getLastname() +"'s turn - remove how many?");
			
			if (Player instanceof NimHumanPlayer) {
				String movedStoneSt = Nimsys.keyboard.nextLine();
				int movedStone = Integer.parseInt(movedStoneSt);// get the number of the moved stones
				try {
					if ((movedStone<=bound)&&(movedStone>0)) {
						if (movedStone<=stonenum) {
							stonenum = stonenum-Player.removeStone(movedStone,stonenum,bound);
							break;
						}
					}
			
					else {
						System.out.println();
						throw new Exception("Invalid move. You must remove between 1 and "+min(bound,stonenum)+" stones.");// print warning informaiton if out of bound
						}
					}
				
				catch(Exception e) {
					String message = e.getMessage( );
					System.out.println(message);
				}
			}
			
				else {
					Player = (NimAIPlayer)Player;
					stonenum = stonenum - Player.removeStone(0,min(bound,stonenum), stonenum);
					break;
				}
			}
			
		
			
			if	(stonenum == 0) { // if there is no stone left, end the loop and end the game. Judge the winner and add the number related.
				System.out.println();
				System.out.println("Game Over");
				System.out.println(otherPlayer.getLastname()+" "+otherPlayer.getFirstname()+" wins!");
				otherPlayer.winGame();
				Player.loseGame();
			}
	}
	
	
	public void startadvancedgame(int initialstones , String Player1username, String Player2username ) 
	
	{ 	
		
		if (Nimsys.existPlayer(Player1username) == (-1) || Nimsys.existPlayer(Player2username) == (-1)) {// judge the Player existence in the array
		System.out.println("One of the players does not exist.");
		return;
		}
		
		this.stonenum = initialstones;
		this.Player1 = Nimsys.getPlayer(Nimsys.existPlayer(Player1username));
		this.Player2 = Nimsys.getPlayer(Nimsys.existPlayer(Player2username));
		boolean[] stones = new boolean[initialstones];
		for (int i = 0; i < initialstones; i++) {
			stones[i] = true;
		}
		
		
		System.out.println();
		System.out.println("Initial stone count: "+this.stonenum);
		System.out.print("Stones display:");
		int index = 1;
		for (int i = 0; i < initialstones; i++) {
            if(stones[i]) {
                System.out.print(" <" + index + ",*>");
                index++;
            } 
           }
		System.out.println();
		System.out.println("Player 1: "+Player1.getLastname()+" "+Player1.getFirstname());
		System.out.println("Player 2: "+Player2.getLastname()+" "+Player2.getFirstname());
		
		String movedStoneSt =" ";
		
		while(allfalse(stones,stonenum)) {
			
			gameprocessadvanced(Player1,Player2,stones,movedStoneSt) ;
			
			if(!allfalse(stones,stonenum))
				break;
			
			gameprocessadvanced(Player2,Player1,stones,movedStoneSt) ;
			
			if(!allfalse(stones,stonenum))
				break;
			
		}

		
		
	}
	
	private void stoneDisplay(boolean[] thelist){
		int count = 0;
		for (int i = 0; i < stonenum; i++) {
			if (thelist[i])
				 count = count + 1;
		}
		
        System.out.print(count +  " stones left:");
        int index = 1;
        for (int i = 0; i < stonenum; i++){
            if(thelist[i]) 
            {
                System.out.print(" <" + index + ",*>");
            } 
            else 
            {
                System.out.print(" <" + index + ",x>");
                
            }
        index++;
        }
        System.out.println();
        
     
        	
        }
	
	   private void gameprocessadvanced(NimPlayer Player, NimPlayer losingPlayer, boolean[] stones,String movedStoneSt) 
	   {   boolean done = false;
	   	   
		   while(!done) 
		   {
		   System.out.println();
		   stoneDisplay(stones);
		   System.out.println(Player.getLastname()+"'s turn - which to remove?");
		   
		   if (Player instanceof NimHumanPlayer) {
		   try 
		   {
			   movedStoneSt = Nimsys.keyboard.nextLine();
			   String[] movdeStoneStpara = movedStoneSt.split(" ");
			   if (movdeStoneStpara[1].equals("1")) 
			   {
				   if (stones[Integer.parseInt(movdeStoneStpara[0])-1]) {
					   stones[Integer.parseInt(movdeStoneStpara[0])-1] = false;
					   done = true;
				   }
				   else
					   throw new Exception();
			   }
			   else if (movdeStoneStpara[1].equals("2"))
			   {
				   if (stones[Integer.parseInt(movdeStoneStpara[0])-1] && 
						  stones[Integer.parseInt(movdeStoneStpara[0])])
				   {
					   stones[Integer.parseInt(movdeStoneStpara[0])-1] = false;
					   stones[Integer.parseInt(movdeStoneStpara[0])] = false;
					   done = true;
				   }
				   else
					   throw new Exception();
				   
			   }
			   else
				   throw new Exception();
			   
			   
		   }
		   catch(Exception e)
		   {
			   System.out.println();
			   System.out.println("Invalid move.");
		   }
		   
		   }
		   if(Player instanceof NimAIPlayer) {
			   
		   }
		
		   
		   
		   
		  
		   
		   
		   
		   
		   
		   
		}
		   
		   
		   
        for (int i = 0; i < stonenum; i++)
        {
			   if (stones[i])
				   return;
		   }
        System.out.println();
		System.out.println("Game Over");
		System.out.println(Player.getLastname()+" "+Player.getFirstname()+" wins!");
		Player.winGame();
		losingPlayer.loseGame();
        
		}
	   
	   private boolean allfalse(boolean[] stones, int index) 
	   {
		   for (int i = 0; i < index; i++) {
			   if (stones[i])
				   return true;
		   }
		  
		return false;
	   }
	}

	

