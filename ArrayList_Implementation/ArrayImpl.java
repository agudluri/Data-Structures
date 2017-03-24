import java.util.Arrays;

public class ArrayImpl<E> implements CollectionInterface<E> {

	private E[] elements;
	private int num_elements;
	private int capacity;

	@SuppressWarnings("unchecked")
	public ArrayImpl(int size) {
		elements = (E[]) new Object[size];
		num_elements = 0;
		capacity = size;
	}

	public ArrayImpl() {
		this(5);
	}

	public boolean is_full() {
		if (num_elements == capacity)
			return true;
		return false;
	}

	public boolean is_empty() {
		if (num_elements == 0)
			return true;
		return false;
	}

	public int size() {
		return num_elements;
	}

	public boolean add(E e) {
		add(Where.BACK, e); // Add at the end using the other 'add' method below
		return true;
	}

	/*
	 * Remove element at index i. If the element exists in the collection,
	 * return that element back to the user. If the index is out of bounds,
	 * return null.
	 */
	public E remove(int i) {
		/*
		 * Add code here. Remember to compact the array so there are no spaces
		 * in between, if an element from the middle is removed or an element at
		 * the beginning of the array is removed.
		 */
		if (i < num_elements) {
			E obj = elements[i];
			elements[i] = null;										//Making the space empty
				for (int lol = i; lol < num_elements; lol++) {		//Swapping the elements towards the left
					elements[lol] = elements[lol + 1];				//
					elements[lol + 1] = null;						//Or you could say moving the null pointer towards the right!
				}
			num_elements--;											//reducing the number of elements. Obviously!
			return obj;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	/*
	 * Return true if e is in the collection class, false otherwise.
	 */
	public boolean contains(E e) {
		boolean lol = false;
		for (E count : elements) {			//	
			if (count == e)					//Going through the entire array and checking if the element exists
				lol = true;					//
		}
		return lol;
	}

	/**
	 * ---- Methods defined by this class
	 * ---------------------------------------------------------- Methods that
	 * are added by this class and not in the CollectionInterface
	 */
	public boolean add(Where where, int index, E e) {
		if (is_full()) {
			grow();
		}
		if (where == Where.MIDDLE && index <= num_elements - 1)
			System.out.println("Inserting element at index " + num_elements);
		System.arraycopy(elements, index, elements, index + 1, num_elements - index); //using the arraycopy function to copy elements
		elements[index] = e;															//from the array to the same array
		num_elements++;
		// add(Where.MIDDLE, index, e);
		return true;
	}

	/**
	 * Add element in front or at the end, as dictated by where. Preconditions:
	 * where != MIDDLE
	 * @throws Exception 
	 */
	public boolean add(Where where, E e) {
		if (is_full()) {
			grow();
		}
		if (where == Where.BACK) {
			System.out.println("Inserting element at index " + num_elements);
			elements[num_elements] = e;			//Simply inserting the element at the end of the array
			num_elements++;
		} else if (where == Where.FRONT) {
			System.out.println("Inserting element at index 0");
			System.out.println("Compacting storage");
			System.arraycopy(elements, 0, elements, 1, num_elements); //Using 'arraycopy' again to move the elements to the right
			elements[0] = e;
			num_elements++;
		}
		return true;
	}

	/*
	 * Gets the element at index i (0 <= i <= num_elements-1)
	 */
	public E get(int i) {
		if (i < 0 && i > num_elements)
			return null;
		return (elements[i]);		//returning the element at the index
	}
	
	/**
	 * subList(int from, int to): Returns the view of a portion of the list between the specified index from,
	 * inclusive, and index to, exclusive. That is returns a sublist [from, to).
	 *
	 * The method returns a new ArrayImpl<E> object.
	 */
	 public ArrayImpl<E> subList(int from, int to)
	 {
		 if(from<0)											//
			 throw new IndexOutOfBoundsException();			//		Making sure the indices are not weird.
		 if (to > num_elements)								//
			 throw new IndexOutOfBoundsException();			//
		 ArrayImpl<E> lol =  new ArrayImpl<E>();
		 for(int i=from;i<to;i++){
			 lol.add(elements[i]); 			//Adding elements to the new object of ArrayImpl
		 }
		 return lol;
	 }

	/*
	 * Grows elements array to hold more elements. Copies old (existing)
	 * elements in the new array.
	 * 
	 * Postcondition: (a) elements must contain the contents of the old array
	 * (b) elements must now have twice as much capacity as before
	 */
	@SuppressWarnings("unchecked")
	private boolean grow() {
		int newSize = elements.length * 2;
		elements = Arrays.copyOf(elements, newSize);		//using arrays.copyof to make a copy the existing array with double the size
		System.out.println("Capacity reached.  Increasing storage...");
		System.out.println("New capacity is " + 2*capacity + " elements");
		return true;
	}
}
