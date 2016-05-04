/**
 * @author      Sean Anderson <sea6@aber.ac.uk>
 * @version     1.0                 
 * @date       20/11/2014  
 * This is a JUnit Test which actually runs tests on the Single Elimination Queue.  
 */
package uk.ac.aber.dcs.sea6.cs21120.assignment1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
public class SingleEliminationTest {
	private IManagerFactory factory;
	private SingleElimination sE;
	
	@Before
	public void setup() throws FileNotFoundException{
		sE = (SingleElimination) IManagerFactory.getManager("uk.ac.aber.dcs.sea6.cs21120.assignment1.SingleElimination");
	}

	@Test
	//This tests that a string is successfully added to the Single Elimination Queue.
	public void testAddingToQueue() {
		sE.sQueue = new Queue(1);
		sE.sQueue.addToQueue("This is a test string to test whether it has properly been added to the Queue");
		assertEquals("This should dispplay the String which has been added to the Queue","This is a test string to test whether it has properly been added to the Queue",(sE.sQueue.inspectFront()));
	}
	@Test
	//This tests that the Queue adds Strings correctly and allocates enough space and that data can be easily added to the Queue.
	public void testLengthIsCorrect(){
		sE.sQueue = new Queue(5);
		sE.sQueue.addToQueue("a");
		sE.sQueue.addToQueue("b");
		sE.sQueue.addToQueue("c");
		sE.sQueue.addToQueue("d");
		sE.sQueue.addToQueue("e");
		assertEquals("This should display the correct length of the Queue", 5,sE.sQueue.length());
	}
		
	@Test
	//This tests that the Queue clears correctly
	public void testClearing(){
		sE.sQueue = new Queue(5);
		sE.sQueue.addToQueue("a");
		sE.sQueue.addToQueue("b");
		sE.sQueue.addToQueue("c");
		sE.sQueue.addToQueue("d");
		sE.sQueue.addToQueue("e");
		assertEquals("This should display the correct length of the Queue", 5,sE.sQueue.length());
		sE.sQueue.clear();
		assertEquals("This should display the length of the Queue to be 0", 0,sE.sQueue.length());
		
	}
	@Test
	//This tests checks that the Queue being empty boolean is working correctly.
	//I added data to the queue initially as I felt that this would be a more realistic implementation of how the Queue works.
	public void testQueueEmptyBoolean(){
		sE.sQueue = new Queue(5);
		sE.sQueue.addToQueue("a");
		sE.sQueue.addToQueue("b");
		sE.sQueue.addToQueue("c");
		sE.sQueue.addToQueue("d");
		sE.sQueue.addToQueue("e");
		sE.sQueue.clear();
		assertEquals("This should display the correct length of the Queue", true,sE.sQueue.isQueueEmpty());
	}
	
	@Test
	//This test checks that external data is loaded in correctly.
	public void checkExternalData() throws FileNotFoundException{
		ArrayList<String> players = CompetitionManager.readPlayers("test.txt");
		sE.setPlayers(players);
		assertEquals("This should display the amount of teams in the text file which is 8.",8,sE.sQueue.length); //This test ensures that the correct amount of files have been added to the queue.
		assertEquals("This should display the first name in the Queue which is Manchester United","Manchester United",sE.sQueue.takeFromFront()); //This test checks the element at the front of the queue thus determining that the elements have been added correctly.
		
}
	
	@Test
	//Checks that it returns the right boolean in relation to the hasNextMatch.
	public void checkMatch() throws FileNotFoundException{
		ArrayList<String> players = CompetitionManager.readPlayers("test.txt");
		sE.setPlayers(players);
		assertEquals("This should return true as there is a next match to be played.",true,sE.hasNextMatch());
	}
	@Test
	//This should reduce the queue size by 1 team wins the match and the other looses.
	public void checkAddToBack() throws FileNotFoundException{
		ArrayList<String> players = CompetitionManager.readPlayers("test.txt");
		sE.setPlayers(players);
		sE.nextMatch();
		sE.setMatchWinner(true);
		assertEquals("The queue size should now be 7",7,sE.sQueue.length());
	}
	@Test
	//This method checks that the final is done correctly (when there are two elements in the queue). It should return Arsenal at the end as Arsenal is the winner.
	public void checkFinal(){
		sE.sQueue = new Queue(2);
		sE.sQueue.addToQueue("Arsenal");
		sE.sQueue.addToQueue("Man City");
		sE.nextMatch();
		sE.setMatchWinner(true);
		assertEquals("This should return Arsenal as Arsenal is the winner and therefore at the top of the Queue","Arsenal",sE.getPosition(0));
	}
	
}
