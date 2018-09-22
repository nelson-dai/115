/*
 * Heap.java
 * Author: Hongyu Dai
 * ID: V00815253
 */
import java.util.NoSuchElementException;



@SuppressWarnings({"unchecked"})

public class Heap {

	private Comparable[] heapArray;
	private int num;
	private static final int INITIAL_SIZE=64;
	/**
	initiate the heapArray with the number of 
	items and size. 
	 */
	public Heap() {
		num = 0;
		heapArray = new ER_Patient[INITIAL_SIZE];
	}
	/**
	determine whether it is empty or not
	 */
	public boolean isEmpty() {
		return num == 0;
	}
	/**
	return the number of items in the heap.
	 */
	// note: This must return the number of items in the heap,
	// not the size of the heap array.
	public int size() 
	{
		return num;
	}
	
	public Comparable getRootItem(){
		if (num == 0){
		throw new NoSuchElementException("Heap is empty.");
		}
		return heapArray[0];
		}

	/**
	insert The patient into the right place.
	 */
	public void insert(ER_Patient patient) {
		if(num>=heapArray.length){
			ER_Patient[] doubleArray = new ER_Patient[2*heapArray.length];
			for(int i=0;i<heapArray.length;i++){
				doubleArray[i]=(ER_Patient) heapArray[i];
			}
			heapArray=doubleArray;
		}
		heapArray[num]=patient;
		num++;
		trickleUp(num-1);
		
	}
	/**
	recursion method that trickles up to the root 
	and switch if the condition is met.
	 */
	private void trickleUp(int index){
		if(index==0) 
			return;
		
		if( heapArray[index].compareTo( heapArray[(index-1)/2] ) < 0){
			swap(index,(index-1)/2);
			trickleUp((index-1)/2);
		}
	}
	/**
	swap method than enables to switch the value.
	 */
	private void swap (int index1, int index2){     
		Comparable holder = heapArray[index1];
		heapArray[index1]=heapArray[index2];
		heapArray[index2]=holder; 
	}
	/**
	remove the first patient that has the highest priority
	 */
	public Comparable removeRootItem() {
		if(num==0){
			return null;
		}                                              
		Comparable root=heapArray[0];
		heapArray[0]=heapArray[num-1];
		num--;
		trickleDown(0);
		return root;
	}
	/**
	recursion method that trickles down 
	and switch if the condition is met.
	 */
	private void trickleDown(int index){
		if(index>=num){
			return;
		}
		int leftIndex = 2*index+1;
		int rightIndex = 2*index+2;
		if(leftIndex>=num){
			return;
		}else if(rightIndex>=num){
			if(heapArray[index].compareTo(heapArray[leftIndex])<0){
				return;
			}else{
				swap(index,leftIndex);
				trickleDown(leftIndex);
			}
		}else{
			if(heapArray[index].compareTo(heapArray[rightIndex])<0&&
					heapArray[index].compareTo(heapArray[leftIndex])<0){
					return;
			}else if(heapArray[leftIndex].compareTo(heapArray[rightIndex])<0){
					swap(index,leftIndex);
					trickleDown(leftIndex);
			}else{
					swap(index,rightIndex);
					trickleDown(rightIndex);
			}	
		}
	}
	
	public void print(){
		for(int i = 0; i < num; i++){
			System.out.println(heapArray[i]);
		}
	}
	
	/**
	there are 5 tests
	 */
	public static void main(String[] args) {
		boolean passed = true;
		//test 1: empty test
		Heap heap = new Heap();
		if(heap.isEmpty()){
			passed = true;
		}else{
			passed = false;
		}
		//creating new patients 
		String[] complaints = {"Walk-in","Life-threatening","Chronic","Major fracture","Chronic"};
		ER_Patient[] patients = new ER_Patient[complaints.length];
		
		int[] expectedInsertIndex ={1,4,2,0,3};
		int[] expectedRemoveIndex ={1,2,4,3,0};
		
		for(int i=0; i<complaints.length; i++){
			// spread out the admission times by 1 second
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
				return;
			}
			patients[i] = new ER_Patient(complaints[i]);
			heap.insert(patients[i]);
			
		//test 2:size test
		if(heap.size()!=complaints.length) passed = true;
		
		//test 3: insert test with elements
		for(int k =0; k<patients.length; k++){
			if(heap.heapArray[k] !=patients[expectedInsertIndex[k]]) passed = true;
		}
		//test 4: remove test
		for(int j =0; j<patients.length; j++){
			if(heap.heapArray[0] !=patients[expectedRemoveIndex[j]]) passed = true;
		}
		//test 5: empty test
		if(!heap.isEmpty()) passed = true;
		System.out.println("The test result is: " + (passed? "Passed" : "Failed"));
		}
		System.out.println("The output is:");
		heap.print();
	}
}
