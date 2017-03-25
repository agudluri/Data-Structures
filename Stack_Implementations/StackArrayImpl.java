import java.util.Arrays;

public class StackArrayImpl<E> implements StackInterface<E> {
	
	private int num_elements;
	private int max_elements;
	private E[] elements;

	@SuppressWarnings("unchecked")
	public StackArrayImpl(int n) {
		max_elements = n;
		num_elements = 0;
		elements = (E[]) new Object[max_elements];
	}

	/**
	 * Pushes an element onto the stack.
	 * 
	 * @param e, the element to be pushed.
	 * 
	 * @return void
	 **/
	public void push(E e) {
		if (num_elements==max_elements) {
			grow();
		}
		elements[num_elements] = e;		//Inserting the element at the end of the stack
		num_elements++;
	}

	
	/*
	 * Pops the top most element of the stack. 
	 * 
	 * @return The element that has been popped
	 */
	public E pop() {
		E temp = elements[num_elements-1];
		//elements[num_elements]= null;
		num_elements--;
		return temp;
	}

	
	/*
	 * Gives you a peek at the topmost element of the stack but does not remove it.
	 * 
	 * @return The element you wanna peek at.
	 */
	public E peek() {
		return elements[num_elements-1];
	}
	
	
	/*
	 * Gives you the size of the stack
	 * 
	 * @return an integer with the size of the current stack
	 */
	public int size() {
		return num_elements;
	}

	
	/*
	 * A method to double the size of the array and copy the existing elements into the new array.
	 * 
	 * @return a boolean value indicating if the size has been grown or not.
	 */
	private boolean grow() {
		int newSize = max_elements * 2;
		elements = Arrays.copyOf(elements, newSize); //using arrays.copyof to make a copy the existing array with double the size
		return true;
	}
	
}
