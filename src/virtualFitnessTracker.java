import java.util.*;
import java.io.*;

public class virtualFitnessTracker {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Your Virtual Fitness Tracker! \n Enter Username or Type 'n/a' to Create a New Profile: ");
        String userNameInput = scanner.nextLine();
        boolean existingaccnt = false;
        int choice = 0;
        if (userNameInput.equals("n/a")) {
            createUserProfile();
            choice = 1;
        } else {
            choice = 2;
            FileInputStream existingUsers = null;
            try {
                existingUsers = new FileInputStream("src/existingUserFiles.csv");
            } catch (FileNotFoundException e) {
                System.out.println("File not found\n Try entering again or type n/a to create a new profile");
            }
            String[] currentUserData = new String[8];
            Scanner fileReader2 = new Scanner(existingUsers);
            while (fileReader2.hasNextLine()) {
                String line = fileReader2.nextLine();
                String[] lineInfo = line.split(",");
                if (lineInfo[1].equalsIgnoreCase(userNameInput)) {
                    currentUserData = lineInfo;
                    existingaccnt = true;
                    break;
                }


            }

            if (existingaccnt == true) {
                createUserProfile currentUserProfile = new createUserProfile(currentUserData[0], currentUserData[1], Integer.parseInt(currentUserData[2]), Integer.parseInt(currentUserData[3]), Integer.parseInt(currentUserData[4]), currentUserData[5], Integer.parseInt(currentUserData[6]), currentUserData[7]);
                printUserProfile(currentUserProfile);
                mainDirectory(currentUserProfile);
            }
            if(existingaccnt == false) {
               System.out.println("User profile does not exist");
                createUserProfile();
            }


        }
    }
    public static void mainDirectory (createUserProfile profileInUse){
        Scanner scanner = new Scanner(System.in);
        int userSelection = 0;
        while(userSelection != 4) {
            System.out.println("DIRECTORY:\nType the corresponding number to the function you wish to perform:");
            System.out.println("(1) To Edit Your Profile\n(2) To Edit Your Current Workout Split\n(3) To Create Workout Program File\n(4) To Exit Your Fitness Tracker");
            userSelection = scanner.nextInt();
            if(userSelection == 1) {
                editUserProfile(profileInUse);
            }else if(userSelection == 2) {
                selectWorkoutSplit(profileInUse);
            }else if(userSelection == 3) {
                createMuscleGrowthProgram(profileInUse);
            } else if(userSelection == 4) {
                System.out.println("Exiting Your Fitness Tracker, See You Soon!");


                FileInputStream userProfileFile = null;
                try {
                    userProfileFile = new FileInputStream("src/existingUserFiles.csv");
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }
                Scanner fileReader = new Scanner(userProfileFile);
                ArrayList<String> listOfUsers = null;
                listOfUsers = new ArrayList<>();
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] lineInfo = line.split(",");
                    if (lineInfo[1].equals(profileInUse.getUserName())) {
                        listOfUsers.add(profileInUse.toString());
                    } else {
                        listOfUsers.add(line);
                    }
                }
                fileReader.close();

                FileOutputStream existingProfileFile = null;
                try {
                    existingProfileFile = new FileOutputStream("src/existingUserFiles.csv");
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }
                PrintWriter fileWriter = new PrintWriter(existingProfileFile);

                for (int i = 0; i < listOfUsers.size(); i++) {
                    fileWriter.println(listOfUsers.get(i));
                }

                fileWriter.flush();
                fileWriter.close();
                System.exit(0);
            }
        }
    }


       public static void createUserProfile(){
        System.out.println("Let's begin by creating a new profile for this user.");
        Scanner scanner = new Scanner(System.in);
        createUserProfile profile1 = new createUserProfile();
        while(profile1.userName.equals("")) {
           System.out.println("Please create a username for your profile(*you will use this to login*): ");
           String z = scanner.nextLine();
           if (z.contains(" ")) {
               System.out.println("Please enter a username without spaces: ");
           }else {
               profile1.setUserName(z);
           }
       }
        System.out.print("Please enter the name of the user: ");
        profile1.setName(scanner.nextLine());
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
        stringWorkoutType = scanner.nextLine().toUpperCase();
       }
       profile1.setWorkoutType(stringWorkoutType);

       int numOfExerciseDays = 0;
           System.out.println("How many days a week would you like to work out?: ");
           numOfExerciseDays = scanner.nextInt();
           while (numOfExerciseDays < 3 || numOfExerciseDays > 6) {
               System.out.println("Please enter a valid amount of days in a week!");
               if (numOfExerciseDays < 3) {
                   System.out.println("You Should At Least Be Working Out 3 Days of the Week");
               }
               if (numOfExerciseDays > 6) {
                   System.out.println("You Should Rest At Least Once A Week");
               }
               System.out.println("How many days a week would you like to work out?: ");
               numOfExerciseDays = scanner.nextInt();
           }
           profile1.setNumOfWorkoutDays(numOfExerciseDays);
           selectWorkoutSplit(profile1);

           FileOutputStream existingProfileFile = null;
           try {
               existingProfileFile = new FileOutputStream("src/existingUserFiles.csv",true);
           }catch(FileNotFoundException e){
               System.out.println("File not found");
           }
               PrintWriter fileWriter = new PrintWriter(existingProfileFile);
               String profileToStore = profile1.toString();
               fileWriter.print(profileToStore +"\n");
               fileWriter.flush();
               fileWriter.close();

           createMuscleGrowthProgram(profile1);
           System.out.println("Your profile has been created. Now log-in with your username");
           System.exit(4);
    }

    public static void selectWorkoutSplit(createUserProfile profile){
        Scanner scanner = new Scanner(System.in);
        int numOfWorkoutDays = profile.getNumOfWorkoutDays();
        if(!profile.getWorkoutSplit().equals("")){
            System.out.println("You have selected to workout " + numOfWorkoutDays + " days a week and your current workout split is "+ profile.getWorkoutSplit() +"\n We will use this to select a workout split.");
        }else {
            System.out.println("You have selected to workout " + numOfWorkoutDays + " days a week.\n We will use this to select a workout split.");
        }
        int userSelection = 0;
        if(numOfWorkoutDays == 3){
            System.out.println("These are the options for a " + numOfWorkoutDays + " day split:");
            System.out.println("(1) Full Body:\n(2) Push-Pull-Legs(PPL):");
            System.out.println("To Select a Split Type its Corresponding Number or (3) to Learn More Information About Each Split");
             userSelection = scanner.nextInt();
            if(userSelection == 1){
                profile.setWorkoutSplit("Full Body");
            }
            else if(userSelection == 2){
                profile.setWorkoutSplit("Push-Pull-Legs");
            } else if (userSelection == 3) {
                System.out.println("Full Body Split:\nThe full body split will target every muscle group each day, in this case there will be 2-3 compound lifts each day with 2-3 accessory lifts. These lifts should be performed with heavier weight and might require longer time in the gym.");
                System.out.println("Push-Pull-Legs:\nThe PPL split will split each day into a push(Chest,Shoulder,Triceps), pull(Back,Biceps), and leg day. In this case each day will have around 2 compound lifts and 2-3 accessory lifts. These lifts should be performed with heavier weight and might require longer time in the gym.");
                System.out.println("(1) Full Body:\n(2) Push-Pull-Legs(PPL):");
                userSelection = scanner.nextInt();
                if (userSelection == 1) {
                    profile.setWorkoutSplit("Full Body");
                } else if (userSelection == 2) {
                    profile.setWorkoutSplit("Push-Pull-Legs");
                }
            }

        }
        if(numOfWorkoutDays == 4){
            System.out.println("These are the options for a " + numOfWorkoutDays + " day split:");
            System.out.println("(1) Upper-Lower:\n(2) Bro Split:");
            System.out.println("To Select a Split Type its Corresponding Number or (3) to Learn More Information About Each Split");
            userSelection = scanner.nextInt();
            if(userSelection == 1){
                profile.setWorkoutSplit("Upper-Lower");
            } else if(userSelection == 2){
                profile.setWorkoutSplit("Bro Split");
            } else if(userSelection == 3) {
                System.out.println("Upper-Lower:\nThe Upper-Lower split will designate half of your days to work your upper body and the other half to work your lower body. In this case, your upper days will be split into a chest,shoulder,triceps day and a back,biceps day. Your lower days will be split into a Quad focused day and a Glute,Hamstring focused day");
                System.out.println("Bro Split:\nThe Bro Split will designate a specific body part for each day. In this case, your days will be split into a Chest day, Back day, Arm day(Biceps,Triceps,Shoulders), and Leg day. Each day will be fairly extensive as each of your body parts will only be trained once a week.");
                System.out.println("(1) Upper-Lower:\n(2) Bro Split:");
                userSelection = scanner.nextInt();
                if (userSelection == 1) {
                    profile.setWorkoutSplit("Upper-Lower");
                } else if (userSelection == 2) {
                    profile.setWorkoutSplit("Bro Split");
                }
            }
        }
        if(numOfWorkoutDays == 5){
            System.out.println("These are the options for a " + numOfWorkoutDays + " day split:");
            System.out.println("(1) Upper-Lower:\n(2) Bro Split:");
            System.out.println("To Select a Split Type its Corresponding Number or (3) to Learn More Information About Each Split");
            userSelection = scanner.nextInt();
            if(userSelection == 1){
                profile.setWorkoutSplit("Upper-Lower");
            } else if(userSelection == 2){
                profile.setWorkoutSplit("Bro Split");
            } else if(userSelection == 3) {
                System.out.println("Upper-Lower:\nThe Upper-Lower split will designate half of your days to work your upper body and the other half to work your lower body(3 upper days, 2 lower days). In this case, your upper days will be split into a chest,shoulder,triceps day, a back,biceps day, and a combination day (mix of both). Your lower days will be split into a Quad focused day and a Glute,Hamstring focused day");
                System.out.println("Bro Split:\nThe Bro Split will designate a specific body part for each day. In this case, your days will be split into a Chest day, Back day, Shoulder day, Arm day(Biceps,Triceps), and Leg day. Each day will be fairly extensive as each of your body parts will only be trained once a week.");

                System.out.println("(1) Upper-Lower:\n(2) Bro Split:");
                userSelection = scanner.nextInt();
                if (userSelection == 1) {
                    profile.setWorkoutSplit("Upper-Lower");
                } else if (userSelection == 2) {
                    profile.setWorkoutSplit("Bro Split");
                }
            }
        }
        if(numOfWorkoutDays == 6){
            System.out.println("These are the options for a " + numOfWorkoutDays + " day split:");
            System.out.println("(1) Push-Pull-Legs");
            System.out.println("To Select a Split Type its Corresponding Number or (3) to Learn More Information About Each Split");
            userSelection = scanner.nextInt();
            if(userSelection == 1){
                profile.setWorkoutSplit("Push-Pull-Legs");
            }
            else if(userSelection == 3){
                System.out.println("Push-Pull-Legs:\nThe PPL split will split each day into a push(Chest,Shoulder,Triceps), pull(Back,Biceps), and leg day. In this case, there will be 2 push days, 2 pull days, and 2 leg days with each day consisting of a compound lift and 2-3 accessory lifts.");


                System.out.println("(1) Push-Pull-Legs:");
                userSelection = scanner.nextInt();
                if(userSelection == 1){
                profile.setWorkoutSplit("Push-Pull-Legs");
                }
            }
        }

    }

    public static void printUserProfile(createUserProfile userProfile){
        System.out.println( "User Info: " + userProfile.getName().toUpperCase() + ", " + userProfile.getUserHeight() + "in, " + userProfile.getUserWeight() + "lbs, " + userProfile.getUserAge() + " yrs old;");
        System.out.println("Workout Type: " + userProfile.getWorkoutType().toUpperCase() + ";");
        System.out.println("Number of Workout Days: " + userProfile.getNumOfWorkoutDays() + ";");
        System.out.println("Workout Split: " + userProfile.getWorkoutSplit() + ";");
    }

    public static void editUserProfile(createUserProfile userProfile){
        Scanner scanner = new Scanner(System.in);
        int userSelection = 0;
        while(userSelection != 8) {
            System.out.println("You Have Selected to Edit Your Profile:\n Type the Corresponding Number to the Information you Wish to Change or Return: ");
            System.out.println("(1) To change your username:\n(2) To edit your name:\n(3) To change your height:\n(4) To change your weight:\n(5) To change your age:\n(6) To change your workout type:\n(7) To change the number of days you wish to workout in a week:\n(8) Return to previous page:");
            userSelection = scanner.nextInt();
            if (userSelection == 1) {
                System.out.print("You Have Selected to change you username:\n *You will use this username to log in*\n");
                System.out.println("Enter the new username you wish to use: ");
                scanner.nextLine();
                String newUsername = scanner.nextLine();
                userProfile.setUserName(newUsername);
            } else if (userSelection == 2) {
                System.out.print("You have selected to change your name:\n Enter the new name you wish to save: ");
                scanner.nextLine();
                String newName = scanner.nextLine();
                userProfile.setName(newName);
            } else if (userSelection == 3) {
                System.out.print("You have selected to change your height:\n Enter the new height you wish to save: ");
                userProfile.setUserHeight(scanner.nextInt());
            } else if (userSelection == 4) {
                System.out.print("You have selected to change your weight:\n Enter the new weight you wish to save: ");
                userProfile.setUserWeight(scanner.nextInt());
            } else if (userSelection == 5) {
                System.out.print("You have selected to change your age:\n Enter the new age: ");
                userProfile.setUserAge(scanner.nextInt());
            } else if (userSelection == 6) {
                System.out.println("You have selected to change your workout type:\n Your current workout type is: " + userProfile.getWorkoutType() + "\nSelect a workout type(Put on muscle or Lose Weight): ");
                scanner.nextLine();
                String workoutType = scanner.nextLine();
                userProfile.setWorkoutType(workoutType);
            } else if (userSelection == 7) {
                System.out.println("You have selected to change the number of days you workout in the week:\n You currently have selected to work out " + userProfile.getNumOfWorkoutDays() +" days in the week\nEnter the new number of days you wish to workout in the week: ");
                userProfile.setNumOfWorkoutDays(scanner.nextInt());
                selectWorkoutSplit(userProfile);
            } else if (userSelection == 8) {
                mainDirectory(userProfile);
            }
        }
    }


    public static void createMuscleGrowthProgram(createUserProfile userProfile){
        ArrayList<workoutExercise> listOfPotentialExercises = new ArrayList<>();
        FileInputStream fileOfExercises = null;
        try {
            fileOfExercises = new FileInputStream("src/Capstone Workout Plan File - Sheet1 (1).csv");
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
        Scanner fileReader = new Scanner(fileOfExercises);
        fileReader.nextLine();
        while(fileReader.hasNextLine()) {
            String line = fileReader.nextLine().toUpperCase();
            String[] lineInfo = line.split(",");
            workoutExercise exercise = new workoutExercise(lineInfo[0], lineInfo[1], lineInfo[2], Integer.parseInt(lineInfo[3]), Integer.parseInt(lineInfo[4]));
            listOfPotentialExercises.add(exercise);
        }
        //Sorting Algorithm by Set Amnt
        for(int i = 0; i < listOfPotentialExercises.size()-1; i++){
            if(listOfPotentialExercises.get(i).getSetAmnt() > listOfPotentialExercises.get(i+1).getSetAmnt()){
                workoutExercise currentExercise = listOfPotentialExercises.get(i);
                listOfPotentialExercises.set(i, listOfPotentialExercises.get(i+1));
                listOfPotentialExercises.set(i+1, currentExercise);
                i = 0;
            }
        }

       linkedList day1 = new linkedList();
       linkedList day2 = new linkedList();
       linkedList day3 = new linkedList();
       linkedList day4 = new linkedList();
       linkedList day5 = new linkedList();
       linkedList day6 = new linkedList();

        if(userProfile.getWorkoutSplit().equals("Push-Pull-Legs")) {
            for (int i = 1; i < userProfile.numOfWorkoutDays + 1; i++) {
                int backCompoundExercise = 0;
                int backAccessoryExercise = 0;
                int bicepAccessoryExercise = 0;
                int chestCompoundExercise = 0;
                int shoulderCompoundExercise = 0;
                int chestAccessoryExercise = 0;
                int shoulderAccessoryExercise = 0;
                int tricepAccessoryExercise = 0;
                int legCompoundExercise = 0;
                int legAccessoryExercise = 0;
                int accessoryAbExercises = 0;
                if (i == 1 || i == 4) {
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if (listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && chestCompoundExercise == 0) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            chestCompoundExercise++;
                            if (i == 1) {
                                day1.append(currentExercise);
                            } else {
                                day4.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("SHOULDER") && shoulderCompoundExercise == 0) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            shoulderCompoundExercise++;
                            if (i == 1) {
                                day1.append(currentExercise);
                            } else {
                                day4.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("TRICEP") && tricepAccessoryExercise == 0) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            listOfPotentialExercises.remove(currentExercise);
                            tricepAccessoryExercise++;
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && chestAccessoryExercise == 0) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            chestAccessoryExercise++;
                            if (i == 1) {
                                day1.append(currentExercise);
                            } else {
                                day4.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("SHOULDER") && shoulderAccessoryExercise == 0) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            shoulderCompoundExercise++;
                            if (i == 1) {
                                day1.append(currentExercise);
                            } else {
                                day4.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("TRICEP") && tricepAccessoryExercise == 0) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            tricepAccessoryExercise++;
                            if (i == 1) {
                                day1.append(currentExercise);
                            } else {
                                day4.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        }
                    }
                }
                if (i == 2 || i == 5) {
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if (listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && backCompoundExercise < 2) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            backCompoundExercise++;
                            if (i == 2) {
                                day2.append(currentExercise);
                            } else {
                                day5.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && backAccessoryExercise == 0) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            backAccessoryExercise++;
                            if (i == 2) {
                                day2.append(currentExercise);
                            } else {
                                day5.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BICEP") && bicepAccessoryExercise == 0) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            bicepAccessoryExercise++;
                            if (i == 2) {
                                day2.append(currentExercise);
                            } else {
                                day5.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        }
                    }
                }
                if (i == 3 || i == 6) {
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if (listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && legCompoundExercise < 2) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            legCompoundExercise++;
                            if (i == 3) {
                                day3.append(currentExercise);
                            } else {
                                day6.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && legAccessoryExercise < 2) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            legAccessoryExercise++;
                            if (i == 3) {
                                day3.append(currentExercise);
                            } else {
                                day6.append(currentExercise);
                            }
                            listOfPotentialExercises.remove(currentExercise);
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("ABS") && accessoryAbExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            accessoryAbExercises++;
                            if (i == 3) {
                                day3.append(currentExercise);
                            }else{
                                day6.append(currentExercise);
                            }
                        }
                    }
                }

            }
        }
        if(userProfile.getWorkoutSplit().equals("Bro Split")) {
            for (int i = 0; i < userProfile.getNumOfWorkoutDays()+1; i++) {
                int compoundChestExercises = 0;
                int accessoryChestExercises = 0;
                int compoundBackExercises = 0;
                int accessoryBackExercises = 0;
                int compoundShoulderExercises = 0;
                int accessoryShoulderExercises = 0;
                int accessoryBicepExercises = 0;
                int accessoryTricepExercises = 0;
                int compoundLegExercises = 0;
                int accessoryLegExercises = 0;
                int accessoryAbExercises = 0;
                if (i == 1) {
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if (listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && compoundChestExercises < 2) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            compoundChestExercises++;
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && accessoryChestExercises < 2) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            accessoryChestExercises++;
                        }
                    }
                } else if (i == 2) {
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if (listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && compoundBackExercises < 2) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            compoundBackExercises++;
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && accessoryBackExercises < 2) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            accessoryBackExercises++;
                        }
                    }
                }else if (i == 3) {
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                    if (listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("SHOULDER") && compoundShoulderExercises < 2) {
                        workoutExercise currentExercise = listOfPotentialExercises.get(j);
                        day3.append(currentExercise);
                        compoundShoulderExercises++;
                    } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("SHOULDER") && accessoryShoulderExercises < 2) {
                        workoutExercise currentExercise = listOfPotentialExercises.get(j);
                        day3.append(currentExercise);
                        accessoryShoulderExercises++;
                    }
                }} else if (i == 4) {
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BICEP") && accessoryBicepExercises < 2) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day4.append(currentExercise);
                            accessoryBicepExercises++;
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("TRICEP") && accessoryTricepExercises < 2) {
                        workoutExercise currentExercise = listOfPotentialExercises.get(j);
                        day4.append(currentExercise);
                        accessoryTricepExercises++;
                        }

                    }
                }else if (i == 5) {
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && compoundLegExercises < 2){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day5.append(currentExercise);
                            compoundLegExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && accessoryLegExercises < 2){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day5.append(currentExercise);
                            accessoryLegExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("ABS") && accessoryAbExercises < 2){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day5.append(currentExercise);
                            accessoryAbExercises++;
                        }
                    }
                }
            }

        }
        if(userProfile.getWorkoutSplit().equals("Upper-Lower")){
            for (int i = 0; i < userProfile.getNumOfWorkoutDays()+1; i++) {
                int compoundChestExercises = 0;
                int accessoryChestExercises = 0;
                int compoundBackExercises = 0;
                int accessoryBackExercises = 0;
                int compoundShoulderExercises = 0;
                int accessoryShoulderExercises = 0;
                int accessoryBicepExercises = 0;
                int accessoryTricepExercises = 0;
                int compoundLegExercises = 0;
                int accessoryLegExercises = 0;
                int accessoryAbExercises = 0;
                if(i== 1){
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && compoundChestExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundChestExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && accessoryChestExercises < 2){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryChestExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("SHOULDER") && compoundShoulderExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundShoulderExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("SHOULDER") && accessoryShoulderExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryShoulderExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("TRICEP") && accessoryTricepExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryTricepExercises++;
                        }
                    }
                }else if(i == 2){
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && compoundLegExercises < 2){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundLegExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && accessoryLegExercises < 2){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryLegExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("ABS") && accessoryAbExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            accessoryAbExercises++;
                        }
                    }
                }else if(i == 3){
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if (listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && compoundBackExercises < 1) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundBackExercises++;
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && accessoryBackExercises < 1) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryBackExercises++;
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BICEP") && accessoryBicepExercises < 2) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryBicepExercises++;
                        }
                    }
                }else if(i == 4){
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && compoundLegExercises < 2){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day4.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundLegExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && accessoryLegExercises < 2){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day4.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryLegExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("ABS") && accessoryAbExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day4.append(currentExercise);
                            accessoryAbExercises++;
                        }
                    }
                }else if(i == 5){
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && compoundChestExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day5.append(currentExercise);
                            compoundChestExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && compoundBackExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day5.append(currentExercise);
                            compoundBackExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("TRICEP") && accessoryTricepExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day5.append(currentExercise);
                            accessoryTricepExercises++;
                        } else if (listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BICEP") && accessoryBicepExercises < 1) {
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day5.append(currentExercise);
                            accessoryBicepExercises++;
                        }
                    }
                }
            }
        }
        if(userProfile.getWorkoutSplit().equals("Full Body")){
            for(int i = 0; i< userProfile.getNumOfWorkoutDays()+1; i++){
                int compoundChestExercises = 0;
                int compoundBackExercises = 0;
                int compoundShoulderExercises = 0;
                int accessoryBicepExercises = 0;
                int accessoryTricepExercises = 0;
                int compoundLegExercises = 0;
                int accessoryAbExercises = 0;
                if(i == 1){
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && compoundChestExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundChestExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && compoundBackExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundBackExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("SHOULDER") && compoundShoulderExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundShoulderExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && compoundLegExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundLegExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("TRICEP") && accessoryTricepExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryTricepExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BICEP") && accessoryBicepExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryBicepExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("ABS") && accessoryAbExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day1.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryAbExercises++;
                        }
                    }
                }else if (i ==2){
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && compoundChestExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundChestExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && compoundBackExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundBackExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("SHOULDER") && compoundShoulderExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundShoulderExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && compoundLegExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundLegExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("TRICEP") && accessoryTricepExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryTricepExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BICEP") && accessoryBicepExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryBicepExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("ABS") && accessoryAbExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day2.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryAbExercises++;
                        }
                    }
                }else if(i==3){
                    for (int j = 0; j < listOfPotentialExercises.size(); j++) {
                        if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("CHEST") && compoundChestExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundChestExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BACK") && compoundBackExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundBackExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("SHOULDER") && compoundShoulderExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundShoulderExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 4 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("LEGS") && compoundLegExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            compoundLegExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("TRICEP") && accessoryTricepExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryTricepExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("BICEP") && accessoryBicepExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryBicepExercises++;
                        }else if(listOfPotentialExercises.get(j).getSetAmnt() == 3 && listOfPotentialExercises.get(j).getMuscleGroup().toUpperCase().equals("ABS") && accessoryAbExercises < 1){
                            workoutExercise currentExercise = listOfPotentialExercises.get(j);
                            day3.append(currentExercise);
                            listOfPotentialExercises.remove(j);
                            accessoryAbExercises++;
                        }
                    }
                }
            }
        }

        createWorkoutProgramFile(userProfile,day1,day2,day3,day4,day5,day6);


    }
    public static void createWorkoutProgramFile(createUserProfile currentUserProfile,linkedList day1,linkedList day2, linkedList day3, linkedList day4,linkedList day5,linkedList day6) {
        FileOutputStream userWorkoutProgram = null;
        try {
            userWorkoutProgram = new FileOutputStream("src/" + currentUserProfile.getUserName().toLowerCase() + "'s.workoutprogram.csv");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        PrintWriter fileWriter = new PrintWriter(userWorkoutProgram);
        if (currentUserProfile.getWorkoutSplit().equals("Push-Pull-Legs")) {
            for (int i = 1; i <= currentUserProfile.getNumOfWorkoutDays(); i++) {
                fileWriter.print("DAY " + i + ": ");
                if (i == 1 || i == 4) {
                    fileWriter.println(" PUSH");
                    if (i == 1) {
                        fileWriter.print(day1.listToString());
                    } else {
                        fileWriter.print(day4.listToString());
                    }
                }
                if (i == 2 || i == 5) {
                    fileWriter.println(" PULL");
                    if (i == 2) {
                        fileWriter.print(day2.listToString());
                    } else {
                        fileWriter.print(day5.listToString());
                    }
                }
                if (i == 3 || i == 6) {
                    fileWriter.println("LEGS");
                    if (i == 3) {
                        fileWriter.print(day3.listToString());
                    } else {
                        fileWriter.print(day6.listToString());
                    }
                }
            }

        }
        if(currentUserProfile.getWorkoutSplit().equals("Bro Split")){
            for (int i = 1; i <= currentUserProfile.getNumOfWorkoutDays(); i++) {
                fileWriter.print("DAY " + i + ": ");
                if(i == 1) {
                    fileWriter.println(" CHEST");
                    fileWriter.println(day1.listToString());
                }
                if(i == 2) {
                    fileWriter.println(" BACK");
                    fileWriter.println(day2.listToString());
                }
                if(i == 3) {
                    fileWriter.println(" SHOULDER");
                    fileWriter.println(day3.listToString());
                }
                if(i == 4) {
                    fileWriter.println(" ARMS");
                    fileWriter.println(day4.listToString());
                }
                if(i == 5) {
                    fileWriter.println(" LEGS");
                    fileWriter.println(day5.listToString());
                }
            }
        }
        if(currentUserProfile.getWorkoutSplit().equals("Upper-Lower")){
            for (int i = 1; i <= currentUserProfile.getNumOfWorkoutDays(); i++) {
                fileWriter.print("DAY " + i + ": ");
                if(i == 1) {
                    fileWriter.println(" UPPER (CHEST FOCUSED)");
                    fileWriter.println(day1.listToString());
                }
                if(i == 2) {
                    fileWriter.println(" LOWER");
                    fileWriter.println(day2.listToString());
                }
                if(i == 3) {
                    fileWriter.println(" UPPER (BACK FOCUSED)");
                    fileWriter.println(day3.listToString());
                }
                if(i == 4) {
                    fileWriter.println(" LOWER");
                    fileWriter.println(day4.listToString());
                }
                if(i == 5) {
                    fileWriter.println(" UPPER (COMBINATION)");
                    fileWriter.println(day5.listToString());
                }
            }
        }
        if(currentUserProfile.getWorkoutSplit().equals("Full Body")){
            for (int i = 1; i <= currentUserProfile.getNumOfWorkoutDays(); i++) {
                fileWriter.println("DAY " + i + ": ");
                if(i == 1) {
                    fileWriter.println(day1.listToString());
                }
                if(i == 2) {
                    fileWriter.println(day2.listToString());
                }
                if(i == 3) {
                    fileWriter.println(day3.listToString());
                }
            }
        }
        int numOfSets = 0;
        numOfSets = calculateNumOfSets(day1.head);
        numOfSets += calculateNumOfSets(day2.head);
        numOfSets += calculateNumOfSets(day3.head);
        numOfSets += calculateNumOfSets(day4.head);
        numOfSets += calculateNumOfSets(day5.head);
        numOfSets += calculateNumOfSets(day6.head);
        fileWriter.println("Total Workload: " + numOfSets + " Total Sets");

            Scanner scanner = new Scanner(System.in);
            System.out.println("What Types of Cardio Would You Like To Implement?\n Please enter from the following as a comma separated list (Treadmill/Elliptical/Biking/Swimming): ");
            String line = scanner.nextLine();
            String[] cardioTypes = line.split(",");
            currentUserProfile.listOfCardioVariations.addAll(Arrays.asList(cardioTypes));


        fileWriter.println("** Cardio **\n To actively " + currentUserProfile.getWorkoutType() + " pick one of the following after each lift: ");
        if(currentUserProfile.getWorkoutType().equalsIgnoreCase("put on muscle")){
            for(int i = 0; i < currentUserProfile.getListOfCardioVariations().size(); i++) {
                if(currentUserProfile.listOfCardioVariations.get(i).equalsIgnoreCase("Treadmill")){
                    fileWriter.println("Incline Treadmill Walk: 30min/3.5 Speed/10 Elevation");
                }else if(currentUserProfile.listOfCardioVariations.get(i).equalsIgnoreCase("Elliptical")){
                    fileWriter.println("Elliptical: 27min(2min Warmup then/2min moderate pace into 30seconds max speed)");
                }else if(currentUserProfile.listOfCardioVariations.get(i).equalsIgnoreCase("Biking")){
                    fileWriter.println("Biking: 25min(5min Warmup then/15min of [30second sprint into 60second moderate pace]/5min Cool Down)");
                }else if(currentUserProfile.listOfCardioVariations.get(i).equalsIgnoreCase("Swimming")){
                    fileWriter.println("Swimming: 400 meter Warmup then/8x25 meter sprint/all out pace/60second rest");
                }
            }

        }else if(currentUserProfile.getWorkoutType().equalsIgnoreCase("lose weight")) {
            for (int i = 0; i < currentUserProfile.getListOfCardioVariations().size(); i++) {
                if (currentUserProfile.listOfCardioVariations.get(i).equalsIgnoreCase("Treadmill")) {
                    fileWriter.println("Incline Treadmill Walk: 5min Warmup/15 min(30second Sprint/60second Walk)/5min Cooldown)");
                } else if (currentUserProfile.listOfCardioVariations.get(i).equalsIgnoreCase("Elliptical")) {
                    fileWriter.println("Elliptical: 30min/Moderate Resistance/90rpm");
                } else if (currentUserProfile.listOfCardioVariations.get(i).equalsIgnoreCase("Biking")) {
                    fileWriter.println("Biking: 30min/Moderate Resistance/90rpm");
                } else if (currentUserProfile.listOfCardioVariations.get(i).equalsIgnoreCase("Swimming")) {
                    fileWriter.println("Swimming: 10min Moderate Pace Warmup/15min (60 seconds All Out Pace/30second Rest)/10min Slow Cool Down)");
                }
            }

        }

        fileWriter.flush();
        fileWriter.close();
    }
    public static int calculateNumOfSets(Node n){
        if(n == null){
            return 0;
        }
        if(n.next == null){
            return n.data.getSetAmnt();
        }
        return calculateNumOfSets(n.next);
    }


}
