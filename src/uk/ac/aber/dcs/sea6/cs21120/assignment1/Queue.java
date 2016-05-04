/**
 * @author      Sean Anderson <sea6@aber.ac.uk>
 * @version     1.0                 
 * @date       17/11/2014  
 * This class actually creates the Queue; it does so by implementing the Queue interface.        
 */


package uk.ac.aber.dcs.sea6.cs21120.assignment1;

public class Queue implements QueueInterface {
	String[] queue;
	int top, bottom, length;

	public Queue(int initialSize) {
		queue = new String[initialSize];
		top = bottom = length = 0;

	}

	@Override
	public void addToQueue(String p) {
		if (length == queue.length) {
			System.out.println("This queue is not big enough");
		}
		queue[bottom++] = p;
		length++;
		if (bottom == queue.length)
			bottom = 0;
		// This was initially here for testing and debugging purposes. System.out.println("adding " + p + " to queue in " + bottom);
	}

	@Override
	public String takeFromFront() throws QueueIsEmptyException {
		if (isQueueEmpty()) {
			throw new QueueIsEmptyException();
		}
		String s = queue[top];
		queue[top] = null;
		top++;
		if (top == queue.length) {
			top = 0;
		}
		length--;
		return s;
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public boolean isQueueEmpty() {
		if (length == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void clear() {
		length = 0;
		queue = new String[queue.length];
		top = bottom = 0;
	}

	public String[] doubleArray(String[] a){
		String [] doubleArray = new String[a.length];
		for(int i=0;i<a.length;i++){
			doubleArray[i]=a[i];
		}
		return doubleArray;
	}

	@Override
	public String inspectFront() throws QueueIsEmptyException {
		if(isQueueEmpty()) throw new QueueIsEmptyException();
		return queue[top];
	}
		
	}
	