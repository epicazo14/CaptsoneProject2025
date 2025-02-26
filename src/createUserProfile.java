

public class createUserProfile {
    String userName;
    int userHeight;
    int userWeight;
    int userAge;
    int userGender;

    public createUserProfile() {
    userName = "";
    userHeight = 0;
    userWeight = 0;
    userAge = 0;
    userGender = 0;
}

public createUserProfile(String userName, int userHeight, int userWeight, int userAge, int userGender) {
    this.userName = userName;
    this.userHeight = userHeight;
    this.userWeight = userWeight;
    this.userAge = userAge;
    this.userGender = userGender;
}

    public void setUserName(String name) {
        userName = name;
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


}