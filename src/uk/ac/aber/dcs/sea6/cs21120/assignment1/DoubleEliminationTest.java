/**
 * @author      Sean Anderson <sea6@aber.ac.uk>
 * @version     1.0                 
 * @date       22/11/2014  
 * This is a JUnit Test which actually runs tests on the Double Elimination Queue.  
 */
package uk.ac.aber.dcs.sea6.cs21120.assignment1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DoubleEliminationTest {
	private IManagerFactory factory;
	private DoubleElimination dE;
	@Before
	public void setup() throws FileNotFoundException{
		dE = (DoubleElimination) IManagerFactory.getManager("uk.ac.aber.dcs.sea6.cs21120.assignment1.DoubleElimination");
	}
	@Test
	public void checkExternalData() throws FileNotFoundException{
		ArrayList<String> players = CompetitionManager.readPlayers("test.txt");
		dE.setPlayers(players);
		assertEquals("This should display the amount of teams in the text file which is 8.",8,dE.winnersQueue.length);//This test ensures that the correct amount of files have been added to the queue.
		assertEquals("The loosers queue should have as many free spaces as the total of the winners queue",8,dE.winnersQueue.length);
		assertEquals("This should display the first name in the Queue which is Manchester United","Manchester United",dE.winnersQueue.takeFromFront()); //This test checks the element at the front of the queue thus determining that the elements have been added correctly.
		
}
	@Test
	//This tests the basics that both queue's can hold a string and return the top of the queue properly.
	public void bothQueesAddData() {
		dE.winnersQueue=new Queue(1);
		dE.winnersQueue.addToQueue("Arsenal");
		dE.losersQueue=new Queue(1);
		dE.losersQueue.addToQueue("Chelsea");
		assertEquals("This should return Arsenal as it's at the top of the Winners Queue","Arsenal",dE.winnersQueue.takeFromFront());
		assertEquals("This should return Chelsea as it's at the top of the loosers Queue","Chelsea",dE.losersQueue.takeFromFront());
	}
	
	@Test
	//When the winners queue is longer the match should be taken from the winners queue and it should then add the looser to the losers queue and the winner to the back of the loosers queue.
	public void winnersQueueLonger() throws FileNotFoundException{
		ArrayList<String> players = CompetitionManager.readPlayers("test.txt");
		dE.setPlayers(players);
		dE.nextMatch();
		dE.setMatchWinner(true);
		assertEquals("The length of the winners queue should now be 7",7,dE.winnersQueue.length);
		assertEquals("The length of the loosers queue should now be 1",1,dE.losersQueue.length);
		//Player 2 should be on top of the loosers queue, in this case player 2 is Liverpool consequently will now check to see whether Liverpool is at the top of the loosers Queue.
		assertEquals("This should be liverpool as it's at the top of the Loosers Queue","Liverpool",dE.losersQueue.takeFromFront());
	}
	
	@Test
	//Checks that when the losers Queue is longer that it takes the next match from the loosers queue.
	public void losersQueueLonger() {
		dE.winnersQueue = new Queue(1);
		dE.winnersQueue.addToQueue("Arsenal");
		dE.losersQueue = new Queue(2);
		dE.losersQueue.addToQueue("Man City");
		dE.losersQueue.addToQueue("Chelsea");
		dE.nextMatch(); //This makes Man City play Chelsea due to the Queue being bigger than the winners queue.
		assertEquals("The loosers queue should be zero as no winner has yet been decided.",0,dE.losersQueue.length); //This therefore proves that the next match has been taken from the losers queue.
		dE.setMatchWinner(true); //This therefore means that Man City beat Chelsea and consequently Man City should go back into the losers queue and the losers queue and the queue length should be 1.
		assertEquals("This should be 1 due to just Man City being in the queue",1,dE.losersQueue.length);
		assertEquals("This is just a test to ensure that it is Man City at the top of the queue.","Man City",dE.losersQueue.inspectFront());
		assertEquals("This checks that Arsenal is still at the top of the winners queue.","Arsenal",dE.winnersQueue.inspectFront());
	}
	

	
	@Test
	//Checks that the final is done correctly when the team in the Losers Queue wins.
	public void fianlLosersQueue(){
		dE.winnersQueue=new Queue(1); //This is due to the final occuring when both the winners and losers queue is of length 1      
		dE.losersQueue=new Queue(1);
		dE.winnersQueue.addToQueue("Arsenal");
		dE.losersQueue.addToQueue("Chelsea");
		dE.nextMatch();
		dE.setMatchWinner(false);//This means that Arsenal win the match
		assertEquals("Arsenal won so they should be here","Chelsea",dE.getPosition(0));
		assertEquals("Chelsea were the runenrs up so they should be here","Arsenal",dE.getPosition(1));
	}
	
	

}
