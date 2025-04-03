import java.lang.String;


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
        // int size = 10;
        int size = 11939;
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
        Task n = new Task(taskId, urgencyLevel, dependencies);
        
        if(dependencies.length == 0){
            queue.insert(n);
        } else{
            // Has dependenceis so add into HashMap.
            for(String s: dependencies){
                LinkedList task = allNodes.getValues(s);
                // Check to see if the task has already been resolved
                Node curr = task.head;
                while(curr != null){
                    Node next = curr.next;
                    if(curr.data.id.equals(s)){
                        // Found the node in the bucket Now to check if resolved
                        if(!curr.data.resolved){
                            hasDependecies.add(s, n);
                        } else {
                            // resolved, no need to look further in this bucket
                            n.dependencies.remove(s);
                            if (n.dependencies.size()==0) queue.insert(n);
                            break;
                        }
                    }
                curr = next;
            }
                
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
        // LinkedList vals = allNodes.getValues(taskId);
        // Task found = null;
        // for(Task n: vals){
        //     if(n.id.equals(taskId)){
        //         found = n;
        //     }
        // }
        // // Update its placement in the heap??
        // //
        // if (found != null)
        //     queue.delete(found); 

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
        Task max = queue.extractMin();
        if (max == null) return null;
        LinkedList dep = hasDependecies.getValues(max.id);
        if (dep != null){
            Node curr = dep.head;
            while(curr != null){
                Node next = curr.next;
                if(curr.data.dependencies.remove(max.id)){
                    if(curr.data.dependencies.size() == 0) queue.insert(curr.data);
                    dep.remove(curr);
                }
                curr = next;
            }
        }   
        max.resolved = true;
        return max.id;
    }
}