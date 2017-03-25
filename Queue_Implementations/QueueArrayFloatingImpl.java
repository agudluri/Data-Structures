\import java.util.Arrays;
import java.util.NoSuchElementException;

public class QueueArrayFloatingImpl<E> implements QueueInterface<E> {
	private E[] data;
	private int front, back;
	private int capacity;
	private int num_elements;

	@SuppressWarnings("unchecked")
	public QueueArrayFloatingImpl(int num_elems) {
		capacity = num_elems;
		data = (E[]) new Object[capacity];
		front = back = 0;
	}

	
	/**
	 * Adds an element to the end of the queue.
	 * 
	 * @param e, the element to be added.
	 * 
	 * @return void
	 **/
	public void add(E element) {
		if (size() == capacity-1)
			grow();
		
		data[back] = element;	//adds an element to the back of the queue.
		back = (back + 1) % capacity;
		num_elements++;
	}

	/**
	 * Removes an element from the start of the queue.
	 * 
	 * @param void.
	 * 
	 * @return the element that has been removed.
	 **/
	public E remove() {
		if (size() == 0)
			throw new NoSuchElementException();
		else {
			E element = data[front];	//remove an element from the front of the queue
			front = (front + 1) % capacity;
			num_elements--;
			return element;
		}
	}

	
	/**
	 * Gives you a peek at the first element of the queue.
	 * 
	 * @param void.
	 * 
	 * @return the element at the front of the queue.
	 **/
	public E peek() {
		if (size() == 0)
			throw new NoSuchElementException();
		return data[front];		//return the element at the front the queue.
	}

	
	/**
	 * Gives you the size of the queue.
	 * 
	 * @param void.
	 * 
	 * @return the size/length of the queue.
	 **/
	public int size() {
		return num_elements;
	}

	/**
	 * A method to grow the size of the queue array.
	 * 
	 * @param void.
	 * 
	 * @return a boolean denoting if the size has been increased or not.
	 **/	
	private boolean grow() {
		int newSize = capacity * 2;
		data = Arrays.copyOf(data, newSize); // using arrays.copyof to make a copy the existing array with double the size
		capacity=capacity*2;
		return true;
	}

}
