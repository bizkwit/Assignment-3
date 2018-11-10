import java.util.*;
//COMMIT ATTEMPT
public class ArrayHeap<K,V>  {
	 private Entry[] heap;
	 private int size;
	 private static final int MAX=2;
	 
	 /**
	  * <h1> default constructor </h1>
	  */
	 public ArrayHeap()
	 {
		 heap = new Entry[10];
		 size=0;
	 }
	 
	 /**
	  * <h1> custom constructor </h1>
	  * @param size custom heap size
	  */
	 public ArrayHeap(int size)
	 {
		 heap = new Entry[size];
		 size = 0;
	 }
	 
	 /**
	  * <h1> checks if the heap is empty </h1>
	  * @return true if empty, false otherwise
	  */
	 public boolean isEmpty()
	 {
		 return size==0;
	 }
	
	 public void modLast() {
		 int i,currentKey;
		 int max=1;
		 Job current=(Job)min().getValue();
		 
		 for(i=0;i<heap.length;i++) {
			 currentKey=(int)heap[i].getKey();
			 if(currentKey>max) {
				 max=currentKey;
				 current=(Job)heap[i].getValue();
			 }
		 }
		 current.setJobPriority(1);
	 }
	 
	 public Object getMinVal() {
		 return removeMin().getValue();
	 }
	 
	 /**
	  * <h1> checks the size of the heap </h1>
	  * @return heap's size
	  */
	 public int size()
	 {
		 return size;
	 }
	 
	 /**
	  * <h1> inserts a new element into the heap and sort it <h1>
	  * @param key of the element
	  * @param value of the element
	  */
	 public void insert (K key, V value)
	 {
		 Entry toInsrt = new Entry(key,value);
		if(size == heap.length)
			resizeUp();
		
		 heap[size] = toInsrt;
		 upHeap(size);
		 size++;
	 }
	 
	/**
	 * <h1> check what is the node with the highest priority but does not delete it</h1> 
	 * @return the node with the highest priority
	 */
	 public Entry min()
	 {
		if(isEmpty())
			throw new NoSuchElementException("empty");
		 return heap[0];
	 }
	 
	 /**
	  * <h1> removes the entry with the highest priority </h1>
	  * @return entry with the highest priority
	  */
	 public Entry removeMin()
	 {
		 if(size==0)
			 throw new NoSuchElementException("heap is empty");
		 Entry<K,V> toReturn = heap[0];
		 heap[0] = heap[size-1];
		 size--;
		 downHeap(0);
		 return toReturn;
	 }
	 /**
	  * <h1> finds the parent </h1>
	  * @param x index of child
	  * @return index of parent
	  */
	 private int parent(int x)
	 {
		 return (x)/MAX;
	 }
	 
	 /**
	  * <h1> finds left node child <h1>
	  * @param x index of parent
	  * @return index of left child
	  */
	 private int leftChild( int x)
	 {
		 return MAX*x;
	 }
	 
	 /**
	  * <h1> finds right node child </h1>
	  * @param x index of parent
	  * @return index of right child
	  */
	 private int rightChild(int x)
	 {
		 return MAX*x+1;
	 }
	 
	 /**
	  * <h1> performs upheap </h1>
	  * @param index the entry that was inserted last
	  */
	 private void upHeap(int index)
	 {
		 Entry temp = heap[index];
		 while (index>0 && temp.compare(temp.getKey(), heap[parent(index)].getKey())<0)
		 {
			 heap[index] = heap [parent(index)];
			 index = parent(index);
		 }
		 heap[index] = temp; 
	 }
	 
	 /**
	  * <h1> performs a downheap </h1>
	  * @param index the entry that was inserted last
	  */
	 private void downHeap(int index)
	 {
		 Entry temp = heap[index];
		 while(kthChild(index, 1)< size)
		 {
			 int child = minChild(index);
			 if(heap[child].compare(heap[child].getKey(), temp.getKey())<0)
				 heap[index] = heap[child];
			 else
				 break;
			 index = child;
		 }
		 heap[index] = temp;	 
	 }
	 
	 /**
	  * <h1> gets the smallest child </h1> 
	  * @param ind parents index
	  * @return the index of the smallest child
	  */
	 final int minChild(int ind) 
	    {
	        int bestChild = kthChild(ind, 1);
	        int k = 2;
	        int pos = kthChild(ind, k);
	        while ((k <= MAX) && (pos < size)) 
	        {
	            if (heap[pos].compare(heap[pos].getKey(), heap[bestChild].getKey())<0) 
	                bestChild = pos;
	            pos = kthChild(ind, k++);
	        }    
	        return bestChild;
	    }
	 
	 /**
	  * <h1> doubles the size of the array when needed </h1>
	  */
	 private void resizeUp() 
	 {
		   int newSize = heap.length * 2;
		   heap = Arrays.copyOf(heap, newSize);
	 }
	 /**
	  * <h1> prints the heap </h1>
	  */
	 public String toString()
	 {
		 System.out.print("{ ");
		 for(Entry k : heap)
		 {
			 System.out.print(k + ", ");
		 }
		 return " }";
	 }
	 
	 private int kthChild(int i,int k)
	 {
		 return MAX*i+k;
	 }

/////////////////////////////////////////////////////////////////////
//							Node class							   //
/////////////////////////////////////////////////////////////////////
	private class Entry<K,V>{
		private K key;
		private V value;
		
		public Entry()
		{
			key=null;
			value = null;
		}
		
		public Entry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
		
		public K getKey()
		{
			return key;
		}
		
		public V getValue()
		{
			return value;
		}
		
		public void setKey(K key)
		{
			this.key = key;
		}
		
		public void setValue(V value)
		{
			this.value = value;
		}
		
		public int compare(K a, K b) throws ClassCastException
		{
			return ((Comparable<K>)a).compareTo(b);
		}
		
		public String toString()
		{
			return "("+key+","+value+")";
		}

	}

}
