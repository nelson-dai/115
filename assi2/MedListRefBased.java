/**
* Author: HONGYU DAI
* ID: V00815253
* Date: Feburary 13, 2016
* Filename: MedListRefBased.java
* Details: CSC115 Assignment 2
*/ 

/*
 * The MedListRefBased is a list that uses an double-linked list as
 * the storage for its Medications.
 */
	public class MedListRefBased implements List<Medication> {
	private MedicationNode head;
	private MedicationNode tail;
	private int count;

/**
 	 * Creates and initializes an empty List of Medication objects.
 	 */
	public MedListRefBased() {
		this.head = null;
		this.tail = null;
		this.count = 0;
	}


/*
 * List Interface methods...
 * NOTE THAT THESE do not need header comments. They inherit
 * the comments from the List interface.
 */
	public void add(Medication k,int index) {
		if (index < 0 || index > count) {
		throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
	}
		MedicationNode N = new MedicationNode(k);
		MedicationNode curr = head;
		if (isEmpty()){    //add into an empty list
			 head = N;	
		}else {
			for(int n=0 ;n<=index-2;n++){
				curr = curr.next;
		}
			if(index==count){	//add into the last of the list
				curr.next=N;
				N.prev=curr;
				N.next=null;
				}else{	        //add into the middle of the list
					N.next = curr.next;
					N.prev = curr;
					curr.next.prev = N;
					curr.next = N;
		}

	}
		count++;
}

	public void remove(int index) {
		MedicationNode curr = head;
		if (index < 0 || index > count-1) {
		throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
	}
		if(index==0 && count==1){
			head = null;

			}else{
				if(index==0){
					head = head.next;
					head.prev = null;
					}else{
						for(int n=0; n<=index-1; n++){
						curr = curr.next;
			}
					if(index == count-1){
					curr.prev.next = null;
						}else{
							
							curr.prev.next = curr.next;
							curr.next.prev = curr.prev;
		}
	}
}	

		count--;
}

	public Medication get(int index) {
		if (index < 0 || index > count-1) {
		throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
	}
			MedicationNode curr = head;
			for(int n=0; n<=index-1; n++){
			curr = curr.next;
	}
			return curr.item;
}

	public boolean isEmpty() {
			return count==0;
}

	public int size() {
		return count;
}

	public void removeAll() {
		this.head = null;
		this.tail = null;
		this.count = 0;
}

	public int find(Medication item) {
		MedicationNode curr = head;
		for(int n=0; n<=count-1; n++){
			if(curr.item.equals(item)){
				return n;
		}
			curr= curr.next; 
	}
		return -1;
}

	
	public void remove(Medication value) {
		while(find(value)!=-1){
			remove(find(value));
		}
}
	 public String toString(){
        if(count == 0) return "{}";
        MedicationNode curr = this.head;
        String temp = "{";
        for(int i =0;i<count - 1;i++){
            temp += curr.item.toString() + ", ";
            curr = curr.next;
        }
        temp+=curr.item.toString()+"}";
        return temp;
    }
    

/**
 * The main method is a test harness that allows this programmer to
 * do some tests to make sure the code is good enough for market.
 * @param args Some command line arguments that are not used.
 */

	/* public static void main(String [] args){
        MedListRefBased TestList = new MedListRefBased();
        TestList.add(new Medication("a",100),0);
        TestList.add(new Medication("b",325),0);
        TestList.add(new Medication("c",100),0);
        TestList.add(new Medication("d",150),3);
        TestList.add(new Medication("a",100),0);
        System.out.println("The list should be {a,c,asa,a,d}");
        System.out.println(TestList);
    }*/

}