import java.util.ArrayList;
import java.util.Arrays;
class Node{
    String id;
    public int prioLvl;
    int npl;
    ArrayList<String> dependencies;
    Node left, right;
    public Node(String id, int priority, String[] dependencies){
        this.id = id;
        // Negative so that the largest value is at the top
        this.prioLvl = -priority;
        this.dependencies = new ArrayList<String>(Arrays.asList(dependencies));
    }
    public void setPriorityLevel(int i){
        this.prioLvl = -1 * i;
    }
    public int getPriorityLevel(){
        return -this.prioLvl;
    }
    public int HashCode(){
        return id.hashCode();
    }
}
public class LeftistHeap {
    private Node root;

    public LeftistHeap(){
        this.root = null;
    }
    public void insert(Node n){
        this.root = merge(this.root, n);
    }

    public Node extractMin(){
        if(this.root == null){return null;}
        Node min = this.root;
        // Return the original value that was inputted.
        min.prioLvl *= -1;
        this.root = merge(this.root.left, this.root.right);
        return min;
    }
    public void merge (LeftistHeap h1, LeftistHeap h2){
        this.root = merge(h1.root, h2.root);
    }
    public Node merge(Node h1, Node h2){
        if(h1 == null){return h2;}
        if(h2 == null){return h1;}
        // Check if they have the same task id
        if(h1.id.equals(h2.id)){
            return h1;
        }
        // Make sure h1 has the "smaller" priority
        if(h1.prioLvl > h2.prioLvl){
            Node temp = h2;
            h2 = h1;
            h1 = temp;
        }
        if(h1.left == null) {
            h1.left = h2;
        } else {
            h1.right = merge(h1.right, h2);
            if(h1.left.npl < h1.right.npl){
                Node temp = h1.left;
                h1.left = h1.right;
                h1.right = temp;
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }
    public void delete(Node n){
        this.root = deleteHelper(this.root, n);
    }

    public Node deleteHelper(Node r, Node n){
        if(r == null){return null;}
        if(r.id.equals(n.id)){
            return merge(r.left, r.right);
        }
        r.left = deleteHelper(r.left, n);
        r.right = deleteHelper(r.right, n);
        return r;
    }
    public static void main(String[] args){
        String[] dep = new String[1];
        dep[0] = "T1";
        Node n1 = new Node("T1", 10, null);
        Node n2 = new Node("T2", 10, dep);
        Node n3 = new Node("T3", 10, null);
        Node n4 = new Node("T4", 10, null);
        LeftistHeap heap = new LeftistHeap();
        heap.insert(n1);
        heap.insert(n3);
        // heap.insert(n4);
        heap.insert(n2);
        Node n5 = heap.extractMin();
        Node n6 = heap.extractMin();
        Node n7 = heap.extractMin();
    }   
}

// This should suffice for the priority queue.

