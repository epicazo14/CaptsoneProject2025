import java.util.ArrayList;

public class createUserProfile {
    String name;
    String userName;
    int userHeight;
    int userWeight;
    int userAge;
    String workoutType;
    int numOfWorkoutDays;
    String workoutSplit;
    ArrayList<String> listOfCardioVariations;

    public createUserProfile() {
    name = "";
    userName = "";
    userHeight = 0;
    userWeight = 0;
    userAge = 0;
    workoutType = "";
    numOfWorkoutDays = 0;
    workoutSplit = "";
    listOfCardioVariations = new ArrayList<>();


}

public createUserProfile(String nameOfUser, String userName, int userHeight, int userWeight, int userAge, String workoutType, int numOfWorkoutDays, String workoutSplit) {
    this.name = nameOfUser;
    this.userName = userName;
    this.userHeight = userHeight;
    this.userWeight = userWeight;
    this.userAge = userAge;
    this.workoutType = workoutType;
    this.numOfWorkoutDays = numOfWorkoutDays ;
    this.workoutSplit = workoutSplit;
    listOfCardioVariations = new ArrayList<>();

}
    public String toString(){
        return(name +"," + userName + "," + userHeight + "," + userWeight + "," + userAge + "," + workoutType + "," + numOfWorkoutDays + "," + workoutSplit);
    }

    public void setName(String newName) {
        name = newName;
    }
    public String getName() {
    return name;
    }
    public void setUserName(String newUserName) {
        userName = newUserName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserHeight(int userHeight) {
    this.userHeight = userHeight;
    }
    public int getUserHeight() {
    return userHeight;
    }
    public void setUserWeight(int userWeight) {
    this.userWeight = userWeight;
    }
    public int getUserWeight() {
    return userWeight;
    }
    public void setUserAge(int userAge) {
    this.userAge = userAge;
    }
    public int getUserAge() {
    return userAge;
    }
    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }
    public String getWorkoutType() {
        return workoutType;
    }
    public void setNumOfWorkoutDays(int numOfWorkoutDays) {
        this.numOfWorkoutDays = numOfWorkoutDays;
    }
    public int getNumOfWorkoutDays() {
        return numOfWorkoutDays;
    }
    public void setWorkoutSplit(String workoutSplit) {
        this.workoutSplit = workoutSplit;
    }
    public String getWorkoutSplit() {
        return workoutSplit;
    }
    public void setListOfCardioVariations(ArrayList<String> listOfCardioVariations) {
        this.listOfCardioVariations = listOfCardioVariations;
    }
    public ArrayList<String> getListOfCardioVariations() {
        return listOfCardioVariations;
    }


}