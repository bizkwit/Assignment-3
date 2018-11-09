import java.util.*;
public class UnsortedListPriorityQueue<K,V> {

	private Node<K,V> last;
	private Node<K,V> first;
	private int size;
	
	/**
	 * <h1> default constructor </h1>
	 */
	public UnsortedListPriorityQueue()
	{
        first = null;
		last = null;
        size=0;
    }
	
	/**
	 * <h1> insert an element at the end of the queue </h1>
	 * @param key priority
	 * @param value data
	 */
	public void insert(K key, V value)
	{
		Node<K,V> newNode = new Node<>(key, value);
		
		if (first == null)
		{
			first = newNode;
			last = newNode;
		}
		
		else
		{
			last.setNext(newNode);
			last = newNode;
		}
		size++;	
	}// end of insert()
	
	/**
	 * <h1> returns the node with the highest priority but does not remove it </h1>
	 * @return node with the highest priority
	 * @throws NoSuchElementException
	 */
	public Node<K,V> min() throws NoSuchElementException
	{
		if (isEmpty())
			throw new NoSuchElementException();
		Node<K,V> position = first;
		Node<K,V> toReturn=position;
		position=position.getNext();
		while(position!=null)
		{
			if(position.compare(toReturn.getPriority(), position.getPriority()) != -1
					&&position.compare(toReturn.getPriority(), position.getPriority()) != 0)
				toReturn=position;
			
			position=position.getNext();
		}
		return toReturn;
	}
	
	/**
	 * <h1> removes the node with the highest priority </h1>
	 * @return the removed node
	 */
	public Node<K,V> removeMin() throws NoSuchElementException
	{
		if (isEmpty())
			throw new NoSuchElementException();
		
		Node<K,V> toReturn=min();
		//if need to remove the first node
		if (toReturn == first)
		{
			first = first.getNext();
			//for garbage collection
			toReturn.setNext(null);
			size--;
			return toReturn;
		}
		
		Node<K,V> position = first;
		//to find the node before the one for removal
		while (!position.getNext().equals(toReturn))
			position=position.getNext();
		position.setNext(toReturn.getNext());
		//for garbage collection
		toReturn.setNext(null);
		size--;
		return toReturn;
	}
	
	/**
	 * <h1> checks whether the queue is empty or not </h1>
	 * @return true or false
	 */
	public boolean isEmpty()
	{
		return (size==0);
	}
	
	/**
	 * <h1> gets the size of the queue </h1>
	 * @return size
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * <h1> prints the content of the queue </h1>
	 */
	public String toString()
	{
		
		//for empty queue
		if (first == null)
			return "null queue";
		//if only one element
		if (first.getNext()==null)
			return "{ ("+first.getPriority()+","+first.getData()+") }";
		
		Node<K,V> position = first;
		System.out.print("{ ");
		System.out.print(" ("+position.getPriority()+","+position.getData()+"), ");
		position = position.getNext();
		while (position!=null)
		{
			System.out.print(" ("+position.getPriority()+","+position.getData()+"), ");
			position = position.getNext();
		}
		return"}";
	}
	
	/**
	 * <h1> find the lowest priority </h1>
	 * @return the node with the lowest priority
	 */
	public Node<K,V> getLastPriority()
	{
		Node<K,V> toReturn = first;
		Node<K,V> position = first.getNext();
		while(position!=null)
		{
			if(position.compare(position.getPriority(), toReturn.getPriority())>0)
				toReturn = position;
			position = position.getNext();	
		}
		return toReturn;
	}
	
	/**
	 * <h1> remove the lowest priority </h1>
	 * @return the nodes with the lowest priority 
	 */
	public Node<K,V> removeLastPraiority()
	{
		Node<K,V> toReturn = getLastPriority();
		Node<K,V> position = first;
		
		while(!position.getNext().equals(toReturn))
			position=position.getNext();
		position.setNext(toReturn.getNext());
		//for garbage collection
		toReturn.setNext(null);
		return toReturn;
	}
	
	/////////////////////////////////////////////////////////////////////
	//							Node class							   //
	/////////////////////////////////////////////////////////////////////
	/**
	 * <h1>A node class </h1>
	 * @param <K> key
	 * @param <V> value 
	 */
	final class Node<K,V>{
		private V data;
		private Node<K,V> next;
		private K priority;

		/**
		 * <h1> default constructor </h1>
		 */
		public Node()
		{
			data = null;
			next = null;
			priority = null;
		}
		/**
		 * <h1> custom constructor </h1>
		 * assigns the elements to their locations and initialize
		 * @param element the data
		 * @param priority 
		 */
		public Node(K priority, V data)
		{
			this.data = data;
			this.priority = priority;
			next = null;
		}

		/**
		 * <g1> gets the data </h1>
		 * @return node's data
		 */
		public V getData()
		{
			return data;
		}

		/**
		 * <h1> sets a new data </h1>
		 * @param data new data for the node
		 */
		public void setData(V data)
		{
			this.data = data;
		}

		/**
		 * <h1> get next node </h1>
		 * @return next node
		 */
		public Node<K,V> getNext()
		{
			return next;
		}

		/**
		 * <h1> sets a new node </h1>
		 * @param next new next node
		 */
		public void setNext(Node<K,V> next) 
		{
			this.next = next;
		}

		/**
		 * <h1> gets the priority </h1>
		 * @return priority
		 */
		public K getPriority()
		{
			return priority;
		}

		/**
		 * <h1> sets node's new priority
		 * @param priority new priority
		 */
		public void setPriority(K priority)
		{
			this.priority = priority;
		}
		
		/**
		 * <h1> compares two objects</h1>
		 * @param a first object
		 * @param b second object
		 * @return i<0 when a<b, i=0 when a=b, i>0 when a>b
		 * @throws ClassCastException
		 */
		public int compare(K a, K b) throws ClassCastException
		{
			return ((Comparable<K>)a).compareTo(b);
		}
		
		/**
		 * <h1> prints the node </h1>
		 */
		public String toString()
		{
			return "("+priority+","+data+")";
		}
		
		

	}// and of inner class
}
