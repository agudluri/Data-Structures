public interface QueueInterface<E> {
	public void add(E element);
	   public E remove();
	   public E peek();
	   public int size();

}
