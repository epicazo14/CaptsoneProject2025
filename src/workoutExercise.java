import java.util.ArrayList;

public class workoutExercise {
    String name;
    String muscleGroup;
    String liftType;
    int repAmnt;
    int setAmnt;

    public workoutExercise(String exerciseName, String muscleGroup, String typeOfExercise, int amntOfReps, int amntOfSets) {
        this.name = exerciseName;
        this.muscleGroup = muscleGroup;
        this.liftType = typeOfExercise;
        this.repAmnt = amntOfReps;
        this.setAmnt = amntOfSets;
    }
    public int getSetAmnt() {
        return setAmnt;
    }
    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String toString(){
       return (name + "\n" + setAmnt + " Sets of " + repAmnt + " Reps\n");

    }






}