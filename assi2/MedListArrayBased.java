/**
 * CSC115 Assignment 2 : Containers
 * MedListArrayBased.java
 * Created for use by CSC115 Spring2016 
 */

/*
 * NOTE TO PROGRAMMER:
 * Do not alter this file.
 * It's purpose is to provide a comparison between a List 
 * that uses an array and a list that uses a linked data structure.
 */

/*
 * The MedListArrayBased is a list that uses an array as
 * the storage for its Medications.
 */
public class MedListArrayBased implements List<Medication> {

	private Medication[] storage;
	private int count;
	private static final int INITIAL_SIZE=3;

	/**
 	 * Creates and initializes an empty List of Medication objects.
 	 */
	public MedListArrayBased() {
		storage = new Medication[INITIAL_SIZE];
		count = 0;
	}

	/**
	 * A private helper method for use within this file only.
	 * This doubles the size of the storage array.
	 */
	private void growAndCopy() {
		Medication[] newStorage = new Medication[storage.length*2];
		// Move all the integers from storage into newStorage
		for (int i=0; i<count; i++) {
			newStorage[i] = storage[i];
		}
		storage = newStorage;
	}

	/**
 	 * A private helper method that moves all the elements after 
	 * the given index position, one position to the left.
 	 * The programmer is responsible for the validity of the index variable.
 	 * @param fromIndex The index position where the sliding begins.
 	 */
	private void slideLeftTo(int fromIndex) {
		for (int i=fromIndex; i<count-1; i++) {
			storage[i] = storage[i+1];;
		}
	}

	/**
	 * A private helper method that moves all the elements after
	 * the given index position, one postion to the right.
	 * The programmer is responsible for the validity of the index variable.
	 * @param fromIndex The index position where the sliding begins.
	 */
	private void slideRightFrom(int fromIndex) {
		for (int i=count; i>fromIndex; i--) {
			storage[i] = storage[i-1];
		}
	}

	/**
	 * A private helper method that is used primarly for debugging
	 * and testing the array object during operations.
	 */
	private void printArray() {
		System.out.println("Actual Array contents:");
		for (int i=0; i<storage.length; i++) {
			if (storage[i] != null) {
				System.out.println(i+" : "+storage[i]);
			} else {
				System.out.println(i+" : null");
			}
		}
		System.out.println("Count = "+count);
	}
			

	/*
	 * List Interface methods...
	 * NOTE THAT THESE do not need header comments. They inherit
	 * the comments from the List interface.
	 */

	public void add(Medication k,int index) {
		// since we are adding, the index can be one place after the end.
		if (index < 0 || index > count) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		if (count==storage.length) {
			growAndCopy();
		}
		slideRightFrom(index);
		storage[index] = k;
		count++;
	}

	public void remove(int index) {
		if (index < 0 || index > count-1) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		slideLeftTo(index);
		storage[count--] = null;
	}

	public Medication get(int index) {
		if (index < 0 || index > count-1) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		return storage[index];
	}

	public boolean isEmpty() {
		return count==0;
	}

	public int size() {
		return count;
	}

	public void removeAll() {
		// create a new array and leave the other for garbage collection.
		storage = new Medication[INITIAL_SIZE];
		count = 0;
	}

	public int find(Medication item) {
		for (int i=0; i<count; i++) {
			if (storage[i].equals(item)) {
				return i;
			}
		}
		return -1;
	}

	public void remove(Medication item) {
		for (int i=0; i<count; i++) {
			if (storage[i].equals(item)) {
				slideLeftTo(i);
				storage[count--] = null;
				// need to look at the new item in this spot
				i--;
			}
		}
	}

	public String toString() {
		if (count == 0) return "{}";
		StringBuilder s = new StringBuilder(count*10); // just an estimate of the size
		s.append("List: {\n");
		for (int i=0; i<count-1; i++) {
			s.append("\t"+storage[i]+",\n");
		}
		if (count>0) {
			s.append("\t"+storage[count-1]+"\n");
		}
		s.append("}");
		return s.toString();
	}

	/**
	 * The main method is a test harness that allows this programmer to
	 * do some tests to make sure the code is good enough for market.
	 * @param args Some command line arguments that are not used.
	 */

	public static void main(String[] args) {
		MedListArrayBased list = new MedListArrayBased();
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("acetylsalicylic acid",325),0);
		list.add(new Medication("acetominophen",100),0);
		list.add(new Medication("cimetidine",150),3);
		list.add(new Medication("meperidine",100),0);
		System.out.println("The list should be {meperidine,acetominophen,asa,meperidine,cimetidine}");
		System.out.println(list);
		list.printArray();
		// check to make sure the private shift methods work:
		System.out.println("After sliding everything right from the second spot:");
		list.slideRightFrom(1);
		list.printArray();
		// The list thinks its longer now.	
		list.count++;
		System.out.println("list version: "+list);
		System.out.println("After sliding them back again:");
		list.slideLeftTo(1);
		list.count--;
		list.printArray();
		System.out.println("list version: "+list);

		
		list.remove(new Medication("meperidine",100));
		System.out.println("After removing meperidine:");
		System.out.println(list);
		System.out.println("The number of elements is "+list.size());
		list.removeAll();
		System.out.println("After removing all the elements:");
		System.out.println(list);
		System.out.println("The number of elements is now "+list.size());
	}
}
