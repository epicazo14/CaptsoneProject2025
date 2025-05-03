public class linkedList {
    Node head;
    public linkedList(){
            head = null;
    }
    public linkedList(Node node) {
        if (head == null) {
            head = node;
        }else {
            Node currNode = head;
            while (currNode.next != null){
                currNode = currNode.next;
        }
            currNode.next = node;
        }
    }

    public void append (workoutExercise p){
        if(head == null) {
            Node n = new Node(p);
            head = n;
        }
        else {
            Node curr = head;
            while(curr.next != null){
                curr = curr.next;
            }
            Node newNode = new Node(p);
            curr.next = newNode;
        }

    }

    public static int getLength(Node head) {
        int length = 0;
        Node curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }


    public String printPlayer() {
         Node currNode = head;
         String playerData = "";
         while (currNode != null) {
             playerData = (currNode.data.toString());
             currNode = currNode.next;
         }
         System.out.println("null");
     return playerData;
    }

    public  void printList() {
        Node currNode = head;
        while (currNode != null) {
            System.out.println(currNode.data.toString());
            currNode = currNode.next;
        }

    }

    public String listToString(){
        String listAsString = "";
        Node currNode = head;
        while (currNode != null) {
            listAsString = listAsString + (currNode.data.toString() + "\n");
            currNode = currNode.next;
        }

        return listAsString;
    }





}





