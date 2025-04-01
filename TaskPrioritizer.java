import java.lang.String;
import java.util.LinkedList;
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
    LeftistHeap queue;
    HashMap hasDependecies;
    HashMap allNodes;
    public TaskPrioritizer() {
        // The queue with no dependencies
        queue = new LeftistHeap();
        int size = 10;
        // int size = 11939;
        // Hash map that has all the nodes with dependencies
        hasDependecies = new HashMap(size);
        // Hash map to easily find nodes
        allNodes = new HashMap(size);
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
        Node n = new Node(taskId, urgencyLevel, dependencies);
        if(dependencies.length == 0){
            queue.insert(n);
        } else{
            // Has dependenceis so add into HashMap.
            for(String s: dependencies){
                hasDependecies.add(s, n);
            }
        }
        allNodes.add(taskId, n);
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
        LinkedList<Node> vals = allNodes.getValues(taskId);
        Node found = null;
        for(Node n: vals){
            if(n.id.equals(taskId)){
                found = n;
            }
        }
        // Update its placement in the heap??
        //
        if (found != null)
            queue.delete(found); 

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
        Node max = queue.extractMin();
        if (max == null) return null;
        LinkedList<Node> dep = hasDependecies.getValues(max.id);
        if (dep != null){
            for(Node n: dep){
                if(n.dependencies.remove(max.id)){
                    if(n.dependencies.size() == 0) queue.insert(n);
                    hasDependecies.remove(max.id, n);
                }
            }
        }   

        return max.id;
    }
}