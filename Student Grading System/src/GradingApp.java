import java.util.Scanner;
 
public class GradingApp 
{    
	
      static Scanner input  = new Scanner(System.in);
      static double[] scores;
      static String[] grades;
      static String subjectName;
      static String[] names;
      static int Distinction;
      static int Merit;
      static int Pass;
      static int Unsuccessful;
      static int Invalid;
      static boolean validScores = false;
      /*
      *An array is a temporary allocation of memory that can store MULTIPLE values of a particular data type.
      *Golden Rule: We must define its size
      *(1) Create array by size e.g String[] names = new String[3]
      *In this case, the value in square brackets- 3 is the size of the array
      *(2) Create array by set - String[] days = ("Mon", "Tue", "wed")
      *In this case, the number of values in set is size or length of array
       */
    
      public static void main(String[] args)
      {
            setUp();
            menu();
      }
      
      public static void setUp() 
      		{
            System.out.println("Enter subject name");
            subjectName = input.next();
            System.out.println("How many students?");
            int numStudents = input.nextInt();
            scores = new double[numStudents];
            grades = new String[numStudents];
            names = new String[numStudents];
            System.out.println(subjectName + " created.");
            }

      public static void menu() {
            System.out.println("********Student Grading Application********");
            System.out.println("Press 1 to Enter Values");
            System.out.println("Press 2 to View Class List");
            System.out.println("Press 3 to View Highest Score");
            System.out.println("Press 4 to View lowest Score");
            System.out.println("Press 5 to View Statistics");
            System.out.println("Press 6 to Create New Group");
            System.out.println("Press X to Exit");
            System.out.println("*******************************************");
            String choice = input.next();
          
            switch(choice) {
            case "1":{
                  enterValues();
                  break;
                  }
            case "2":{
                  if (validScores==true) {
                        viewClassList();
                  }
                  else {
                        System.out.println("Enters scores first");
                  }
                  break;  
                  }
            case "3":
            {
                  if (validScores==true) {
                        findMax();
                  }
                  else {
                        System.out.println("Enters scores first");
                  }
                  break;
                  }
            case "4":{
                  if (validScores==true) {
                        findMin();
                  }
                  else {
                        System.out.println("Enters scores first");
                  }
                  break;
                  }
            case "5":{
                  if (validScores==true) {
                  viewStats();
                  }
                  break;
                  }
            case "6":{
                  setUp();
                  break;
                  }
            case "x": case "X":{
                  System.out.println("Shutting down...........");
                  System.exit(0);
                  break;
                  }
            }
           
           
            menu(); //recursive method call
      }
     
     
      private static void viewStats() {
     
            calcGrade();
            double total=0;
           
            for(int i=0; i < scores.length; i++) {
                  if(scores[i] <=100) {
                        total = total + scores[i];
                       
                  }
            }
            int validStudents = scores.length - Invalid;
            double average = total/validStudents;
           
            System.out.println("-----------Class stats-----------");
            System.out.println("Number of Students " + scores.length);
            System.out.println("Class Average: " + average);
            System.out.println("Number Passed: " + Pass);
            System.out.println("Number of Fails: " + Unsuccessful);
            System.out.println("Number of Merits " + Merit);
            System.out.println("Number of Distinctions " + Distinction);
            System.out.println("---------------------------------");
           
           reset();
           
      }
      private static void findMin() {
            double min = scores[0];
            String minStudentName=names[0];
           
            for(int i=0; i> scores.length; i++) {
              if(min>scores[i]) {
                    min = scores[i];
                    minStudentName= names[i];
              }
            }
            System.out.println(minStudentName + " was the lowest score with " + min);
      }
      private static void findMax() {
            double max = scores[0];
            String maxStudentName=names[0];
           
            for(int i=0; i< scores.length; i++) {
            if(max<scores[i]) {
                  max = scores[i];
                  maxStudentName = names[i];
                  }
            }
            System.out.println(maxStudentName + " was the highest with " + max);   
           
            }
      private static void viewClassList() {
     
            calcGrade();
           
            System.out.println("------"+ subjectName + "--------");
            for(int i=0; i < scores.length; i++) {
                  System.out.println((i+1) + "\t" + names[i]);
                  System.out.println((i+1) + "\t" + scores[i]);
                  System.out.println((i+1) + "\t" + grades[i]);
            }
            System.out.println("--------------------------------");
           
      }
      private static void calcGrade() {
            for(int i=0; i <grades.length; i++) {
                  if(scores[i]<50) {
                        grades[i] = "Unsuccessful  ";
                        Unsuccessful++;
                  }
                  else if(scores[i]<65) {
                        grades[i] = "Pass";
                        Pass++;
                  }
                  else if(scores[i]<80) {
                    grades[i] = "Merit";
                    Merit++;
                   
                  }
                  else if(scores[i]<=100) {
                        grades[i] = "Distinction";
                        Distinction++;
                        }
                  else {
                        grades[i] = "invalid";
                        Invalid++;
                  }
            }
            reset();
      }
      private static void reset() 
      {
    	  Distinction=0;
    	  Merit=0;
    	  Pass=0;
    	  Unsuccessful=0;
    	  Invalid=0;
      }
      private static void enterValues() {
            for(int i=0; i<scores.length; i++) {
                  System.out.println("Enter student name");
                  names[i] = input.next();
                  System.out.println("Enter score for " + names[i]);
                  scores[i] = input.nextDouble();
                  System.out.println(names[i] + " scored " + scores[i]);
            }
            validScores=true;
            
      }
}