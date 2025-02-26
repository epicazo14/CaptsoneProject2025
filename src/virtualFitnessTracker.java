import java.util.Scanner;
import java.io.*;
public class virtualFitnessTracker {

    public static void main(String args[]){
        System.out.println(" Welcome to Your Virtual Fitness Tracker!");
        System.out.println("Let's begin by creating a new profile for this user.");
        Scanner scanner = new Scanner(System.in);
        createUserProfile profile1 = new createUserProfile();
       System.out.println("Please enter the name of the user: ");
        profile1.setUserName(scanner.nextLine());
       System.out.println("Please enter the height of the user(in): ");
       profile1.setUserHeight(scanner.nextInt());
       System.out.println("Please enter the weight of the user(lbs): ");
       profile1.setUserWeight(scanner.nextInt());
       System.out.println("Please enter the age of the user: ");
       profile1.setUserAge(scanner.nextInt());
       storeUserProfile(profile1);

    }
    public static void storeUserProfile(createUserProfile userProfile){
        FileOutputStream userFile = null;

        try {
            userFile = new FileOutputStream("src/" + userProfile.getUserName().toLowerCase() +"fitnessprofile.csv");
        }catch(FileNotFoundException e){
                System.out.println("File not found");
            }
         PrintWriter fileWriter = new PrintWriter(userFile);
        fileWriter.println(userProfile.getUserName() + ", " + userProfile.getUserHeight() + ", " + userProfile.getUserWeight() + ", " + userProfile.getUserAge());
        fileWriter.flush();
        fileWriter.close();
    }
}