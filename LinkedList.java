class Node {
    Task data;
    Node next;
    Node prev;

    public Node(Task data) {
        this.data = data;
    }
}
public class LinkedList{


    Node head;
    Node tail;
    int count;
    // Adds a new string to the end of the list.
    // Returns the newly created node for further operations.
    public Node add(Task data) {
        Node newNode = new Node(data);
        count++;
        if (head == null) {  // The list is empty.
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        return newNode;
    }


    public void remove(Node node) {
        if (node == null) {
            return;
        }
        
        if (node == head) {
            head = node.next;
            if (head != null) {
                head.prev = null;
            } else { // If the list becomes empty.
                tail = null;
            }
        }
        else if (node == tail) {
            tail = node.prev;
            tail.next = null;
        }
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        count--;
    }


    public Task get(int index) {
        Node current = head;
        int currentIndex = 0;
        while (currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        return current.data;
    }


    public Node getHead() {
        return this.head;
    }
    public Node getNext(Node n){
        return n.next;
    }
}