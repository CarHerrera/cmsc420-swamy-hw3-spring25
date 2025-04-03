// import java.util.LinkedList;
class Node {
    Task data;
    Node next;
    Node prev;

    public Node(Task data) {
        this.data = data;
    }
}
class LinkedList{


    Node head;
    Node tail;

    // Adds a new string to the end of the list.
    // Returns the newly created node for further operations.
    public Node add(Task data) {
        Node newNode = new Node(data);
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
public class HashMap { 
    private LinkedList[] HashArr;
    public HashMap(int size){
        HashArr = new LinkedList[size];
    }  

    // public int hash_func(Node s){
    //     return s.hashCode() % HashArr.length;
    // }
    public int hash_func(String s){
        int hash = s.hashCode();
        if (hash < 0){
            return (hash * -1) % HashArr.length;
        } else {
            return s.hashCode() % HashArr.length;
        }
        
    }
    public void add(String key, Task s){
        int index = hash_func(key);
        if(HashArr[index] == null){
            HashArr[index] = new LinkedList();
        }
        HashArr[index].add(s);
    }

    public Task find(String key, Task s){
        int index = hash_func(key);
        // Object does not exist and was not init
        if(HashArr[index] == null){
            return null;
        }
        Node curr = HashArr[index].head;
        while(curr != null){
            if (curr.data.id.equals(s.id) ){
                return curr.data;
            }
            curr = curr.next;
        }
        return null;
    }

    public void remove(String key, Task s){
        int index = hash_func(key);
        if(HashArr[index] == null){
            return;
        }
        Node curr = HashArr[index].head;
        while(curr != null){
            if (curr.data.id.equals(s.id) ){
                HashArr[index].remove(curr);
                break;
            }
            curr = curr.next;
        }
    }
    public LinkedList getValues(String key){
        int index = hash_func(key);
        if(HashArr[index] == null){
            return null;
        } 
        return HashArr[index];
    }
    // public static void main(String[] args) {
    //     HashMap hash = new HashMap(11901);

    //     Node n1 = new Node("T1", 10, null);
    //     Node n2 = new Node("T2", 10, null);
    //     Node n3 = new Node("T3", 10, null);
    //     Node n4 = new Node("T4", 10, null);


    //     hash.add("T1", n1);
    //     // hash.add("Hi");
    //     // hash.add("Carlos");
    //     // hash.add("Peter");
    // }
}

