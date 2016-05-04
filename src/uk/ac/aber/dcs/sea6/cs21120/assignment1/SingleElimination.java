/**
 * @author      Sean Anderson <sea6@aber.ac.uk>
 * @version     1.0                 
 * @date       20/11/2014  
 * This class uses the queue for Single Elimination which is one of the implementations of the queue required for the assignment.   
 */
package uk.ac.aber.dcs.sea6.cs21120.assignment1;

import java.util.ArrayList;

public class SingleElimination implements IManager {
	protected Queue sQueue ;//Short for Single ELimination queue.
	private String team1;
	private String team2;
	@Override
	public void setPlayers(ArrayList<String> players) {
		sQueue = new Queue(players.size());
		System.out.println(players.size());
				for (int i=0;i<players.size();i++){
				sQueue.addToQueue(players.get(i));
				}
		//System.out.println(singleElimination.length); Initially here for test and debugging purposes, thought it may be useful to leave in.
		}
		

	@Override
	public boolean hasNextMatch() {	
				if (sQueue.length()>1){
				return true;
				}
				else {
				return false;
				}
	}

	@Override
	public Match nextMatch() throws NoNextMatchException {
				if (!hasNextMatch()){
				throw new NoNextMatchException("No matches avaliable");
				}
				team1=sQueue.takeFromFront();
				team2=sQueue.takeFromFront();
				return new Match(team1,team2);
	}

	@Override
	public void setMatchWinner(boolean player1) {
		
				if (player1==true){
					
				sQueue.addToQueue(team1);
				}
					
				else if (player1==false){
					sQueue.addToQueue(team2);
				}
	}
	
			
	@Override
	public String getPosition(int n) {
			if (n==1){
			return("Runner up can not be determined using Single Elimination");
		}
		return sQueue.takeFromFront();
	}




}
