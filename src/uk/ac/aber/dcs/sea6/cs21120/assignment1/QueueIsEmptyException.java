package uk.ac.aber.dcs.sea6.cs21120.assignment1;

public class QueueIsEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueIsEmptyException(){
		super("You are attempting to access an empty queue");
	}
	
}
