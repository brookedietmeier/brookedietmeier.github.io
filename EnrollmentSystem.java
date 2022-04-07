import java.util.*;
import java.io.*;

public class EnrollmentSystem {
    public static void main(String[] arg) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);

        int choice = -1;
        while (choice != 0) {
            System.out.print("Would you like to rollCall (1), enrollClass (2), or quit (0)? ");
            choice = console.nextInt();
            if (choice == 1) {
                System.out.print("What class would you like to roll call? ");
                String courseName = console.next();
                Scanner input = new Scanner(new File(courseName + ".txt"));
                rollCall(input, courseName);
                System.out.println();
            } else if (choice == 2) {
                System.out.print("What class would you like to enroll? ");
                String courseName = console.next();
                enrollClass(console, courseName + ".txt");
                System.out.println();
            }
        }
        
    }

    public static void enrollClass(Scanner console, String fileName) throws FileNotFoundException {
        System.out.print("How many students would you like to enroll in this class? "); 
        PrintStream outputFile = new PrintStream(new File(fileName));

        int students = console.nextInt(); 
        
        int studentNumber = 1;
        while(studentNumber <= students) {
        System.out.print("Student " + studentNumber + " first name: " );
        String first = console.next(); 
        System.out.print("Student " + studentNumber + " last name: " );
        String last = console.next();
        System.out.print("Student " + studentNumber + " age: " );
        int age = console.nextInt(); 
        System.out.print("Student " + studentNumber + " id: ");
        int id = console.nextInt(); 
        studentNumber++; 
        String lineContent = last + " " + first + " " + age + " " + id;
        outputFile.println(lineContent);
        }
        

    }

    public static void rollCall(Scanner input, String courseName) {
        System.out.println("The following students in " + courseName + 
                " are of voting age:");
        while (input.hasNextLine()) {
            String studentInfo = input.nextLine();
            Scanner student = new Scanner(studentInfo);
            String last = student.next();
            String first = student.next();
            int age = student.nextInt();
            if (age >= 18) {
                System.out.println("    " + first + " " + last);
            }
        }
    }
}