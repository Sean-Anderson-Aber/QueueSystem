/**
 * @author      Sean Anderson <sea6@aber.ac.uk>
 * @version     1.0                 
 * @date       16/11/2014  
 * This class is the interface for a Queue it is based of Bernie's slides on Blackboard.
 */
package uk.ac.aber.dcs.sea6.cs21120.assignment1;

public interface QueueInterface {
	public void addToQueue(String p);//This adds an object to the back of the queue.
	public String takeFromFront() throws QueueIsEmptyException; //This takes an object from the front of the queue.
	public String inspectFront() throws QueueIsEmptyException; //This inspects the front element of the queue.
	public int length(); //This checks the length of the queue.
	public boolean isQueueEmpty(); //This checks whether the queue is empty.
	public void clear(); //This removes all items from the queue.
}
