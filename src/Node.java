public class Node {

    public workoutExercise data;
    public Node next;

    public Node(   workoutExercise data){
        this.data = data;
    }

    public void setData(workoutExercise data) {
        this.data = data;
    }

    public void setNext(Node nextNode) {
        this.next = nextNode;
    }
}
