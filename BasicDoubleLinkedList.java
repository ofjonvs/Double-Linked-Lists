package abc;

/** Basic double linked list class
 * 
 * @author Jonas da Silva
 *
 */

import java.util.*;

import abc.BasicDoubleLinkedList.Node;
//import abc.BasicDoubleLinkedList.iter;

	public class BasicDoubleLinkedList<T> implements Iterable<T> {
		
		public Node header;
		public Node tail;
		public int size;

		@Override
		public Iterator<T> iterator() {
			return new DoubleLinkedListIterator();
		}
		
		public class Node {
			public T dataNode;
			public Node next;
			public Node previous;

			public Node(T dataNode, Node next, Node previous) {
				this.dataNode = dataNode;
				this.next = next;
				this.previous = previous;
			}
		}


		
		public class DoubleLinkedListIterator implements ListIterator<T>{
			
			private Node current;
			private Node last;
			
			public DoubleLinkedListIterator() {
				current = header;
				last = null;
			}

			@Override
			public boolean hasNext() {
				if (current == null)
					return false;
				return true;
			}

			@Override
			public T next() throws NoSuchElementException {
				if(hasNext()) {
					T data = current.dataNode;
					last = current;
					current = current.next;
					if(hasNext())
						current.previous = last;
					return data;	
					}
				else
					throw new NoSuchElementException();	
			}
			
			
			@Override
			public boolean hasPrevious() {
				if (last == null)
					return false;
				return true;
			}

			@Override
			public T previous()throws NoSuchElementException {
				if(hasPrevious()) {
					current = last;
					last = current.previous;
					T data = current.dataNode;
					return data;
				}
				else
					throw new NoSuchElementException();
			}

			@Override
			public int nextIndex() throws UnsupportedOperationException {
				throw new UnsupportedOperationException();
			}

			@Override
			public int previousIndex() throws UnsupportedOperationException {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() throws UnsupportedOperationException {
				throw new UnsupportedOperationException();
			}

			@Override
			public void set(T e)  throws UnsupportedOperationException{
				throw new UnsupportedOperationException();
			}

			@Override
			public void add(T e) throws UnsupportedOperationException{
				throw new UnsupportedOperationException();
			}
			
		}
		
		public BasicDoubleLinkedList() {
			header = null;
			tail = null;
			size = 0;
		}
		
		public int getSize() {
			return size;
		}
		
		public void addToEnd(T data) {
			Node nde = new Node(data, null, tail);
			if(tail != null)
				tail.next = nde;
			tail = nde;
			if(header == null)
				header = nde;
			size++;
		}
		
		public void addToFront (T data) {
			Node nde = new Node(data, header, null);
			if(header != null)
				header.previous = nde;
			header = nde;
			if(tail == null)
				tail = nde;
			size++;		
		}
				
		
		public T getFirst() {
			if(header.dataNode == null)
				return null;
			return header.dataNode;
		}
		
		public T getLast() {
			if(tail.dataNode == null)
				return null;
			return tail.dataNode;
		}
		
		public BasicDoubleLinkedList.Node remove(T targetData, Comparator<T> comparator){
			Node current = header;
			Node previous = null;
			while(current != null) {
				if(comparator.compare(current.dataNode, targetData) == 0) {
					if(current == header) {
						current = header.next;
						header = header.next;
					}
					else if (current == tail) {
						current = null;
						tail = previous;
						previous.next = null;
					}
					else {
						previous.next = current.next;
						current = current.next;
					}
					size--;
				}
				else {
					previous = current;
					current = current.next;
				}
			}
			return current;
		}
		
		public T retrieveFirstElement() {
			Node nde = header;
			if(size == 0)
				return null;
			size --;
			header = header.next;
			header.previous = null;
			return nde.dataNode;
		}
		
		public T retrieveLastElement() {
			Node current = header;
			Node previous = null;
			if(size == 0)
				return null;
			size --;
			while(current != null) {
				if(current.equals(tail)) {
					tail = previous;
					break;
				}
				previous = current;
				current = current.next;
			}
			return current.dataNode;
		}
		
		public ArrayList <T> toArrayList(){
			ArrayList<T> array = new ArrayList<T>();
			ListIterator<T> iterator = new DoubleLinkedListIterator();
			while(iterator.hasNext()) {
				array.add(iterator.next());
			}
			return array;
		}
		
		
		
	}



	
	

