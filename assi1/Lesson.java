/*
* Author: HONGYU DAI
* ID: V00815253
* Date: January 22, 2016
* Filename: Lesson.java
* Details: CSC115 Assignment 1
*/ 

/*
 * The class Lesson creats lessons for skiers
 * different levels of skiers. 
 */

public class Lesson {
	private static final String[] levelNames = 
		{"Beginner", "Novice", "Snowplower", "Intermediate", "Advanced"};
	private int lessonLevel; // level of the Lesson, not necessarily all the skiers
	private String lessonName; // One of the names in levelNames array above.
	private SkierList students; // The list of skiers registered for this lesson.

/*
 * The constructor that initialize all the variables.
 * In this case, the variables are the lessonLevel
 * lessonName and the students arry.
 */ 
	public Lesson(int level) {
		  if(level>4 || level <0){
            this.lessonLevel = 0;
        }else{
            this.lessonLevel = level;
        }
        this.lessonName = this.levelNames[this.lessonLevel];
        this.students = new SkierList();
    }
	
/*
 * Creates a lesson for the given level, if the level 
 * is not in the range set it to 0. Also set population of the lesson.
 */ 
	public Lesson(int level, SkierList students) {
		if(level>4 || level<0){       //level is in the range
			this.lessonLevel=0;
		}else{                        //not in the range
		this.lessonLevel=level;		
	    }
		this.students=students;       //population
		this.lessonName=this.levelNames[this.lessonLevel];//lesson name
	}  

/*
 * Use the level of the students to sets the lesson name 
 * if the level is not in the range then set to 0
 */ 
	public void setLessonLevel(int level) {  //chicking level
		 if(level>4 || level <0){
            this.lessonLevel = 0;            //not in the range set to 0 
        }else{
            this.lessonLevel = level;        //set level
        }
         this.lessonName = this.levelNames[this.lessonLevel]; //set lesson name by the level
	}

/*
 *return the lesson name from the setLessonLevel method
 */
	public String getName() {
		return this.lessonName;
	}

/*
 *return the number of skiers in the lesson
 */	
	public int numStudents() {
		return this.students.size();
	}

/*
 *Update the level of a skier and add the 
 *skier to the lesson
 */		
	public void addSkier(Skier skier) {
		skier.setLevel(this.lessonLevel);//update the level for the skier
		this.students.add(skier);        //add the skier to the lesson
	}

/*
 *Remove the skier if we can find 
 *the skier in the lesson
 */	
	public void removeSkier(Skier skier) {
		this.students.remove(this.students.findSkier(skier));
	}

/*
 *checking if the skier is registered in the lesson
 */
	public boolean isRegistered(Skier skier) {
		 if((this.students.findSkier(skier)) != -1){// if the method findSkier 
            return true;                            //does not return -1 then the skier is registered
        }else{
            return false;
        }
	}

/*
 *Prints the list of skiers 
 */
	public String toString() {
		 String lesson = "";
        lesson += this.lessonName+" group:\n";
        for(int n =0;n<this.students.size();n++){
            lesson += this.students.get(n).toString() +"\n";
        }
        return lesson;

	}

	/**
	 * Used as a test harness for the class.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		System.out.println("Testing the Lesson class.");
		Lesson lesson = null;
		String[] group = {"Daffy Duck", "Bugs Bunny", "Betty Boop",
			"Roger Rabbit", "Han Solo", "Chewbacca"};
		try {
			lesson = new Lesson(2);
		} catch (Exception e) {
			System.out.println("Failed to construct a Lesson object.");
			e.printStackTrace();
			return;
		}
		if (!lesson.getName().equals("Snowplower")) {
			System.out.println("Failed at test one.");
			return;
		}
		if (lesson.numStudents() != 0) {
			System.out.println("Failed at test two.");
			return;
		}
		lesson.setLessonLevel(3);
		if (!lesson.getName().equals("Intermediate")) {
			System.out.println("Failed at test three.");
			return;
		}
		for (int i=0; i<group.length; i++) {
			lesson.addSkier(new Skier(group[i]));
		}
		if (lesson.numStudents() != 6) {
			System.out.println("Failed at test four.");
			return;
		}
		System.out.print("Checking the toString: Should see a list of ");
		System.out.println("6 skiers, all with level 3");
		System.out.println(lesson);
		
		System.out.println("Checking constructor that takes a list.");
		int[] levels = {0,3,2};
		int levelIndex = 0;
		SkierList list = new SkierList();
		for (int i=0; i<group.length; i++) {
			list.add(new Skier(group[i],levels[levelIndex]));
			levelIndex = (levelIndex+1)%levels.length;
		}
		try {
			lesson = new Lesson(4,list);
		} catch (Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		if (lesson.numStudents() != 6) {
			System.out.println("Failed at test five.");
			return;
		}
		for (int i=0; i<list.size(); i++) {
			if (!lesson.isRegistered(list.get(i))) {
				System.out.println("Failed at test six.");
				return;
			}
		}
		Skier removed = list.get(3);
		lesson.removeSkier(removed);
		if (lesson.isRegistered(removed)) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (lesson.numStudents() != 5) {
			System.out.println("Failed at test eight.");
			return;
		}
		System.out.print("The following printout should consist of 5 ");
		System.out.println("skiers with varying levels:");
		System.out.println(lesson);
		System.out.println("Testing completed.");
	}
}