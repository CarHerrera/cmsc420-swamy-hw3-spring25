import java.lang.String;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
class Node{
    String id;
    int prioLvl;
    String[] dependencies;
    public Node(String id, int priority, String[] dependencies){
        this.id = id;
        this.prioLvl = priority;
        this.dependencies = dependencies;
    }
}
class Heap{
    ArrayList<Node> heapList;
    public Heap(){
        heapList = new ArrayList<Node>();
    }

    public void addNode(Node n){
        int count = heapList.size();
        if (count == 0){
            heapList.add(n);
        } else {
            heapList.add(n);
            for(int i = (count/2)-1; i >= 0; i--){
                heapify(i);
            }
        }
    }
    public void heapify(int i ){
        int size = heapList.size();
        int last = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if(l < size && heapList.get(l).prioLvl > heapList.get(last).prioLvl){
            last = l;
        }
        if(r < size && heapList.get(r).prioLvl > heapList.get(last).prioLvl){
            last = r;
        }

        if (last != i){
            Node temp = heapList.get(last);
            heapList.set(last, heapList.get(i));
            heapList.set(i, temp);
            heapify(last);
        }
    }
}
 class Queue{
    public Queue(){

    }
}
/**
 * TaskPrioritizer class that returns the most urgent
 * available task
 *
 * @author Carlos Herrera
 */
public class TaskPrioritizer {
    /**
     * Constructor to initialize the TaskPrioritizer
     */
    public TaskPrioritizer() {

    }

    /**
     * A method to add a new task
     *
     * @param taskId       The string taskId of the task we want to add
     * @param urgencyLevel The integer urgencyLevel of the task we want to add
     * @param dependencies The array of taskIds of tasks the added task depends on
     */
    public void add(String taskId, int urgencyLevel, String[] dependencies) {
        // TODO
    }

    /**
     * A method to change the urgency of a task
     *
     * @param taskId       The string taskId of the task we want to change the
     *                     urgency of
     * @param urgencyLevel The new integer urgencyLevel of the task
     */
    public void update(String taskId, int newUrgencyLevel) {
        // TODO
    }

    /**
     * A method to resolve the greatest urgency task which has had all of its
     * dependencies satisfied
     *
     * @return The taskId of the resolved task
     * @return null if there are no unresolved tasks left
     */
    public String resolve() {
        // TODO
        return null;
    }
}