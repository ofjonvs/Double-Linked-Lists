package abc;
/** Jonas JUnit tests
 * 
 * @author Jonas da Silva
 *
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DoubleLinkedListJonasTest {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	SortedDoubleLinkedList<Car> sortedLinkedCar;
	SortedDoubleLinkedList<String> sortedLinkedString;
	StringComparator comparator;
	DoubleComparator comparatorD;
	CarComparator comparatorCar;

	public Car a=new Car("Ford", "F150", 2005);
	public Car b=new Car("Jeep", "Renegade", 2005);
	public Car c=new Car("Honda", "Civic", 2005);
	public Car d=new Car("Subaru", "Outback", 2005);
	public Car e=new Car("Chevrolet", "Silverado", 2005);
	public Car f=new Car("Chrysler", "PTCruiser", 2005);
	
	private class Car{
		String make;
		String model;
		int year;
		
		public Car(String make, String model, int year){
			this.make = make;
			this.model = model;
			this.year = year;
		}
		
		public String getMake(){
			return make;
		}
		public String getModel(){
			return model;
		}
		public int getYear(){
			return year;
		}
		
		public String toString() {
			return (getMake()+" "+getModel()+" "+getYear());
		}
	}
	
	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class CarComparator implements Comparator<Car>
	{

		@Override
		public int compare(Car arg0, Car arg1) {
			// Just put cars in alphabetic order by make
			return arg0.getMake().compareTo(arg1.getMake());
		}		
	}
	
	
	@Test
	public void testRemoveString() {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Java");
		linkedString.addToEnd("Python");
		comparator = new StringComparator();
		assertEquals("Java", linkedString.getFirst());
		assertEquals("Python", linkedString.getLast());
		linkedString.addToFront("C++");
		assertEquals("C++", linkedString.getFirst());
		linkedString.remove("C++", comparator);
		assertEquals("Java", linkedString.getFirst());
		linkedString.addToEnd("html");
		assertEquals("html", linkedString.getLast());
		linkedString.remove("html", comparator);
		assertEquals("Python", linkedString.getLast());
		linkedString.addToFront("C++");
		assertEquals("C++", linkedString.getFirst());
		assertEquals("Python", linkedString.getLast());
		linkedString.remove("Java", comparator);
		assertEquals("C++", linkedString.getFirst());
		assertEquals("Python", linkedString.getLast());
		
	}
	
	@Test
	public void testDouble() {
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(1.0);
		linkedDouble.addToEnd(2.0);
		comparatorD = new DoubleComparator();
		assertEquals(1.0 + "", linkedDouble.getFirst().toString());
		assertEquals(2.0 + "", linkedDouble.getLast().toString());
		linkedDouble.addToFront(3.0);
		assertEquals(3.0 + "", linkedDouble.getFirst().toString());
		linkedDouble.remove(3.0, comparatorD);
		assertEquals(1.0 + "", linkedDouble.getFirst().toString());
		linkedDouble.addToFront(4.0);
		linkedDouble.remove(1.0, comparatorD);
		assertEquals(4.0+"", linkedDouble.getFirst().toString());
		linkedDouble.remove(4.0, comparatorD);
		assertEquals(2.0+"", linkedDouble.getFirst().toString());
	}
	
	@Test
	public void testSortedString() {
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
		sortedLinkedString.add("c");
		sortedLinkedString.add("b");
		sortedLinkedString.add("d");
		sortedLinkedString.add("a");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("a", iterator.next());
		assertEquals("b", iterator.next());
		assertEquals("c", iterator.next());
		assertEquals("d", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("d", iterator.previous());
		assertEquals("c", iterator.previous());
		assertEquals("b", iterator.previous());
		assertEquals("a", iterator.previous());
	}
	
	@Test
	public void testSortedCar() {
		comparatorCar = new CarComparator();
		sortedLinkedCar = new SortedDoubleLinkedList<Car>(comparatorCar);
		sortedLinkedCar.add(a);
		sortedLinkedCar.add(c);
		assertEquals(a, sortedLinkedCar.getFirst());
		assertEquals(c, sortedLinkedCar.getLast());
		sortedLinkedCar.add(b);
		assertEquals(a, sortedLinkedCar.getFirst());
		assertEquals(b, sortedLinkedCar.getLast());
		sortedLinkedCar.add(f);
		assertEquals(f, sortedLinkedCar.getFirst());
		assertEquals(b, sortedLinkedCar.getLast());
		assertEquals(4,sortedLinkedCar.getSize());
		sortedLinkedCar.remove(a, comparatorCar);
		assertEquals(f, sortedLinkedCar.getFirst());
		assertEquals(b, sortedLinkedCar.getLast());
		assertEquals(3,sortedLinkedCar.getSize());
	}
	
	
	
	
}