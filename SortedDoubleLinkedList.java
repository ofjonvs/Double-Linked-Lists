package abc;
/** Sorted double linked list class
 * 
 * @author Jonas da Silva
 *
 */
import java.util.*;

import abc.BasicDoubleLinkedList.DoubleLinkedListIterator;
import abc.BasicDoubleLinkedList.Node;

public class SortedDoubleLinkedList <T> extends BasicDoubleLinkedList<T> {
	
	Comparator<T> comparat = null;
	
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		comparat = compareableObject;
	}

	
	public void add(T data) {
		Node nde = new Node(data, null, null);
		Node next;
		Node previous;
		if (header == null) {
			header = new Node (data, null, null);
			tail = header;
		} else {
			if (comparat.compare(data, (T) header.dataNode) <= 0) {
				nde.next = header;
				header = nde;
			} else if (comparat.compare(data, (T) tail.dataNode) >= 0) {
				tail.next = nde;
				tail = nde;
			} else {
				next = header.next;
				previous = header;
				while (comparat.compare(data, (T) next.dataNode) > 0) {
					previous = next;
					next = next.next;
				}
				previous.next = nde;
				nde.next = next;
			}
		}
		size++;
	}
	
	
	@Override
	public void addToEnd(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	@Override
	public void addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	public ListIterator<T> iterator(){
		return new DoubleLinkedListIterator();
	}
	
	public BasicDoubleLinkedList.Node remove​(T data, Comparator<T> comparator){
		Node previous = null;
		Node current = header;
		while(current != null) {
			if(comparat.compare((T) current.dataNode, data) == 0) {
				size--;
				if(previous != null) {
					previous.next = current.next;
				}
				else {
					header = current.next;
				}
				if(current == tail) {
					tail = previous;
				}
			}
			previous = current;
			current = current.next;
			
		}
		return current;
	}

	

	
}
