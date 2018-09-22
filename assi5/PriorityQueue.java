/*
 * PriorityQueue.java
 * Author: Hongyu Dai
 * ID: V00815253
 */
import java.util.NoSuchElementException;



public class PriorityQueue {
	
	private Heap heap;

	/**
	 * Creates an empty priority queue.
	 */
	public PriorityQueue() {
		heap = new Heap();
	}
	
	public Comparable peek(){
		if(heap.isEmpty() == true){
		 	throw new NoSuchElementException("The queue is empty.");
		 	}
		return heap.getRootItem();
		}
	//create insert method
	public void enqueue(ER_Patient patient) {
		heap.insert(patient);
	}
	//create remove method
	
	public Comparable dequeue() {
		return heap.removeRootItem();
	}
	//determine whether it is empty
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	private void print(){
		heap.print();
		}
	
	
	
	
	
	//TEST
	public static void main(String[] args) {
		PriorityQueue test = new PriorityQueue();
		boolean passed = test.isEmpty();
		if(passed==true)
		  System.out.println("isEmpty test passed");
		else
		  System.out.println("isEmpty test failed");	
		
		
		
		
		//insert test
		System.out.println("Creating new patients.");
		System.out.println("Adding patients.");
		String[] complaints = {"Walk-in", "Life-threatening","Chronic","Major fracture", "Chronic"};
		ER_Patient[] patients = new ER_Patient[complaints.length];
		int[] expectedRemoveIndex = {1,2,4,3,0};
		for(int i=0; i<complaints.length; i++){
			// spread out the admission times by 1 second
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
				passed = false;
			}
			patients[i] = new ER_Patient(complaints[i]);
			test.enqueue(patients[i]);
		}
		test.print();
		System.out.println("The above test result is: " + (passed? "Passed" : "Failed"));
		System.out.println("");				

		//remove test
		System.out.println("Removing patients.");
		for(int i=0; i<patients.length; i++){
			if(test.dequeue() != patients[expectedRemoveIndex[i]]) passed = false;
		}
		System.out.println("The above test result is: " + (passed? "Passed" : "Failed"));
		System.out.println("");
		
		//empty test
		System.out.println("Determining whether PriorityQueue is empty.");
		if(!test.isEmpty()) passed = false;
		System.out.println("The above test result is: " + (passed? "Passed" : "Failed"));				
	}
			
}
	
