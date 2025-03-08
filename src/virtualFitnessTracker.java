import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class virtualFitnessTracker {

    public static void main(String args[]){
        System.out.println(" Welcome to Your Virtual Fitness Tracker!");
        System.out.println("Let's begin by creating a new profile for this user.");
        Scanner scanner = new Scanner(System.in);
        createUserProfile profile1 = new createUserProfile();
       System.out.print("Please enter the name of the user: ");
        profile1.setUserName(scanner.nextLine());
       System.out.print("Please enter the height of the user(in): ");
       profile1.setUserHeight(scanner.nextInt());
       System.out.print("Please enter the weight of the user(lbs): ");
       profile1.setUserWeight(scanner.nextInt());
       System.out.print("Please enter the age of the user: ");
       profile1.setUserAge(scanner.nextInt());
       scanner.nextLine();
       System.out.print("Would you like to put on muscle or lose weight?: ");
       String stringWorkoutType = scanner.nextLine().toLowerCase();
       while(!(stringWorkoutType.toLowerCase().equals("put on muscle") || stringWorkoutType.toLowerCase().equals("lose weight"))){
           System.out.println("Please enter one of the two valid options!");
           System.out.print("Would you like to put on muscle or lose weight?: ");
        stringWorkoutType = scanner.nextLine().toLowerCase();
       }

       int workoutType = 0;
       if(stringWorkoutType.equals("put on muscle")){
           workoutType = 1;
       }else if(stringWorkoutType.equals("lose weight")){
           workoutType = 2;
       }
       System.out.print(workoutType);
        ArrayList<String> listOfMuscles = new ArrayList<String>();
        ArrayList<String> listOfCardioVariations = new ArrayList<String>();
        int numOfExerciseDays = 0;


       if(workoutType == 1) {
           System.out.println("What Muscle Groups Would You Like To Work?\n Please enter from the following as a comma separated (Chest/Shoulder/Back/Biceps/Triceps/Legs/Abs):");
           String line = scanner.nextLine().toUpperCase();
           String[] muscleGroups = line.split(",");


           System.out.println("What Types of Cardio Would You Like To Implement?\n Please enter from the following as a comma separated list (Incline Treadmill Walk/Running/Elyptical/Biking/Swimming): ");
           line = scanner.nextLine();
           String[] cardioTypes = line.split(",");


           listOfMuscles.addAll(Arrays.asList(muscleGroups));
           listOfCardioVariations.addAll(Arrays.asList(cardioTypes));


           System.out.println("How many days a week would you like to work out?: ");
           numOfExerciseDays = scanner.nextInt();
           while (numOfExerciseDays < 4 || numOfExerciseDays > 6) {
               System.out.println("Please enter a valid amount of days in a week!");
               if (numOfExerciseDays < 4) {
                   System.out.println("You Should At Least Be Working Out 4 Days of the Week");
               }
               if (numOfExerciseDays > 6) {
                   System.out.println("You Should Rest At Least Once A Week");
               }
               System.out.println("How many days a week would you like to work out?: ");
               numOfExerciseDays = scanner.nextInt();
           }
           }

            /*
           for(int i =0; i < listOfMuscles.size(); i++){
               System.out.print(listOfMuscles.get(i));
           }
           for(int i =0; i < listOfCardioVariations.size(); i++){
               System.out.print(listOfCardioVariations.get(i));
           }

             */


           if (workoutType == 2) {
               System.out.println("What Muscle Groups Would You Like To Work?\n Please enter from the following as a comma separated list (Chest/Shoulder/Back/Biceps/Triceps/Legs/Abs):");
               String line = scanner.nextLine().toUpperCase();
               String[] muscleGroups = line.split(",");

               System.out.println("What Types of Cardio Would You Like To Implement?\n Please enter from the following as a comma separated list (Incline Treadmill Walk/Running/Elyptical/Biking/Swimming): ");
               line = scanner.nextLine();
               String[] cardioTypes = line.split(",");

               listOfMuscles.addAll(Arrays.asList(muscleGroups));
               listOfCardioVariations.addAll(Arrays.asList(cardioTypes));


               System.out.println("How many days a week would you like to work out?: ");
               numOfExerciseDays = scanner.nextInt();
               while (numOfExerciseDays < 4 || numOfExerciseDays > 6) {
                   System.out.println("Please enter a valid amount of days in a week!");
                   if (numOfExerciseDays < 4) {
                       System.out.println("You Should At Least Be Working Out 4 Days of the Week");
                   }
                   if (numOfExerciseDays > 6) {
                       System.out.println("You Should Rest At Least Once A Week");
                   }
                   System.out.println("How many days a week would you like to work out?: ");
                   numOfExerciseDays = scanner.nextInt();
               }
           }



           /*
           for(int i =0; i < listOfCardioVariations.size(); i++){
               System.out.print(listOfCardioVariations.get(i));
           }

         */
         for(int i =0; i < listOfMuscles.size(); i++){
               System.out.print( "Printing all muscles selected" + listOfMuscles.get(i));
           }

           storeUserProfile(profile1, stringWorkoutType, workoutType, listOfMuscles, listOfCardioVariations, numOfExerciseDays);
           createMuscleGrowthProgram(profile1, listOfMuscles, listOfCardioVariations, numOfExerciseDays);

       }

    public static void storeUserProfile(createUserProfile userProfile, String workoutType, int typeOfWorkoutProgram, ArrayList<String> muscleGroups, ArrayList<String> cardioTypes, int numberOfWorkoutDays){
        FileOutputStream userFile = null;

        try {
            userFile = new FileOutputStream("src/" + userProfile.getUserName().toLowerCase() +"fitnessprofile.csv");
        }catch(FileNotFoundException e){
                System.out.println("File not found");
            }

         PrintWriter fileWriter = new PrintWriter(userFile);
        fileWriter.println( "User Info: " + userProfile.getUserName() + ", " + userProfile.getUserHeight() + "in, " + userProfile.getUserWeight() + "lbs, " + userProfile.getUserAge() + " yrs old");

        fileWriter.println("Workout Type: " + workoutType.toUpperCase());



            fileWriter.print("Muscles Groups Selected: ");
            for(int i =0; i < muscleGroups.size(); i++){
               fileWriter.print(muscleGroups.get(i).toUpperCase() + ", ");
           }
            fileWriter.print("\nCardio Types Selected: ");
            for(int i =0; i < cardioTypes.size(); i++){
               fileWriter.print(cardioTypes.get(i).toUpperCase() + ", ");
           }
            fileWriter.println("\nNumber of Workout Days: " + numberOfWorkoutDays);






        fileWriter.flush();
        fileWriter.close();
    }
    public static void createMuscleGrowthProgram(createUserProfile userProfile,ArrayList<String> muscleGroups,ArrayList<String> cardioTypes,int numberOfWorkoutDays){
        ArrayList<workoutExercise> listOfPotentialExercises = new ArrayList<>();
        Random rand = new Random();
        FileInputStream fileOfExercises = null;
        for (int i = 0; i < muscleGroups.size(); i++) {
            System.out.println(muscleGroups.get(i));
        }
        try {
            fileOfExercises = new FileInputStream("src/Capstone Workout Plan File - Sheet1 (1).csv");
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
        Scanner fileReader = new Scanner(fileOfExercises);
        while(fileReader.hasNextLine()) {
            String line = fileReader.nextLine().toUpperCase();
            //System.out.println(line);
            String[] lineInfo = line.split(",");

            /*
            for (int i = 0; i < lineInfo.length; i++) {
                System.out.println(lineInfo[i]);
            }
             */
            System.out.println(lineInfo[1]);
            try {
                if ((muscleGroups.contains(lineInfo[1].toUpperCase()))) {
                    workoutExercise exercise = new workoutExercise(lineInfo[0], lineInfo[1], lineInfo[2], Integer.parseInt(lineInfo[3]), Integer.parseInt(lineInfo[4]));
                    listOfPotentialExercises.add(exercise);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error parsing line ");
            }
        }
        System.out.println("Printing list of potential exercises");
        for(int i = 0; i < listOfPotentialExercises.size(); i++){
            System.out.print(listOfPotentialExercises.get(i) + " ");
        }








        FileOutputStream muscleGrowthProgram = null;
        try {
            muscleGrowthProgram = new FileOutputStream("src/" + userProfile.getUserName().toLowerCase() +"'sworkoutprogram.csv");
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        PrintWriter fileWriter = new PrintWriter(muscleGrowthProgram);

            for(int i =1; i < numberOfWorkoutDays+1; i++) {
                fileWriter.println("DAY " + i + ":");
                for (int j = 0; j < 4; j++) {
                    fileWriter.println(listOfPotentialExercises.get(rand.nextInt(listOfPotentialExercises.size())).toString());
                }
            }
        fileWriter.flush();
        fileWriter.close();

    }


}