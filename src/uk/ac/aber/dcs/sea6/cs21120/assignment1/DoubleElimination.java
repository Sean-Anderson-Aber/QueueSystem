/**
 * @author      Sean Anderson <sea6@aber.ac.uk>
 * @version     1.0                 
 * @date       22/11/2014  
 * This class uses the queue for Double Elimination which is one of the implementations of the queue required for the assignment.   
 */
package uk.ac.aber.dcs.sea6.cs21120.assignment1;

import java.util.ArrayList;

public class DoubleElimination implements IManager {
	protected Queue winnersQueue;
	protected Queue losersQueue;
	private String team1;
	private String team2;
	private String winner;
	private String runnerUp;
	private String placeInQueue;
	private Boolean winners;
	private Boolean finalMatch=false;
	@Override
	public void setPlayers(ArrayList<String> players) {
		winnersQueue = new Queue(players.size());
		losersQueue = new Queue(players.size());
		System.out.println(players.size());
		for (int i=0;i<players.size();i++){
			winnersQueue.addToQueue(players.get(i));
		}
	}

	@Override
	public boolean hasNextMatch() {
		if( (winnersQueue.length>=1)&&(losersQueue.length>=0)){
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
		if(hasNextMatch()==true){
		
		if(winnersQueue.length>losersQueue.length){
			team1=winnersQueue.takeFromFront();
			team2=winnersQueue.takeFromFront();
			winners=true;
		}
		
		else if((winnersQueue.length==1)&&(losersQueue.length==1)){
			//This is the final match
			team1=winnersQueue.takeFromFront();
			team2=losersQueue.takeFromFront();
			finalMatch=true;
		}
		
		else if (winnersQueue.length<=losersQueue.length) {
			team1=losersQueue.takeFromFront();
			team2=losersQueue.takeFromFront();
			winners=false;
			
		}
	}
		return new Match(team1,team2);
	}

	@Override
	public void setMatchWinner(boolean player1) {
		
		if (finalMatch==true){
			if(player1==true){
				winner=team1;
				runnerUp=team2;
			}
			if (player1==false){
					winner=team2;
					runnerUp=team1;
				}
		}
		
		else if ((player1==true)&&((finalMatch==true))){
			winner=team2;
			runnerUp=team1;
		}
		
		if ((player1==true)&&(winners==true)){
			winnersQueue.addToQueue(team1);
			losersQueue.addToQueue(team2);
			}
				
		else if ((player1==true)&&(winners==false)){
				losersQueue.addToQueue(team1);
			}
		else if ((player1==false)&&(winners=true)){
			winnersQueue.addToQueue(team2);
			losersQueue.addToQueue(team1);
			}
		else if ((player1==false)&&(winners==true)){
			losersQueue.addToQueue(team2);
		}
		
		else if ((player1==true)&&(losersQueue.isQueueEmpty()==true)){
			winner=team1;
			runnerUp=team2;
		}
		
}

	@Override
	public String getPosition(int n) {		
		if(n==0){
			placeInQueue= winner;
		}
		if (n==1) {
			placeInQueue= runnerUp;
		}
		return placeInQueue;
		
	}

}
