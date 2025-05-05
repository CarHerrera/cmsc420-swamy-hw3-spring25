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
    private final StableHeap q;
    private final CuckooHash<String,Task> allNodes;
    private final CuckooHash<String, LinkedList> hasDependencies;
    private final int SIZE = 501083;
    public TaskPrioritizer() {
        q = new StableHeap();
        allNodes = new CuckooHash<>(SIZE);
        hasDependencies = new CuckooHash<>(SIZE);
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
        boolean in = allNodes.containsKey(taskId);
        if(!in){
            Task t = new Task(taskId, urgencyLevel, dependencies);
            // Has no dependencies and does not exist. So add to heap
            if(dependencies == null || dependencies.length == 0){
                q.insert(t);
            } else {
                for(String s: dependencies){
                    // Check if the dependency has been added overall
                    Task tmp = allNodes.get(s);
                   if(tmp != null) {
                        if(tmp.resolved){
                            t.dependencies.remove(tmp.id);
                        } else {
                            LinkedList l = hasDependencies.get(s);
                            // Dependency for that node has not been created
                            if(l == null){
                            l = new LinkedList();
                            l.add(t);
                            hasDependencies.put(s, l); 
                            } else {
                            l.add(t);
                            }
                        }
                   }
                }
                if(t.dependencies.isEmpty()){
                    q.insert(t);
                }
            }
            allNodes.put(taskId, t);
        }
    }

    /**
     * A method to change the urgency of a task
     *
     * @param taskId       The string taskId of the task we want to change the
     *                     urgency of
     * @param urgencyLevel The new integer urgencyLevel of the task
     */
    public void update(String taskId, int newUrgencyLevel) {
        // // TODO
        boolean in = allNodes.containsKey(taskId);
        if(in){
            Task t = allNodes.get(taskId);
            if(t.resolved){
                t.prioLvl = newUrgencyLevel;
            } else {
                q.updatePriority(t, newUrgencyLevel);
            }
        }
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

        Task max = q.extractMax();
        if (max == null){return null;}
        LinkedList dep = hasDependencies.get(max.id);
        if (dep != null && dep.count > 0){
            Node curr = dep.head;
            while(curr != null){
                Node next = curr.next;
                if(curr.data.dependencies.remove(max.id)){
                    if(curr.data.dependencies.isEmpty()) {
                        q.insert(curr.data);
                    }
                    dep.remove(curr);
                }
                curr = next;
            } 
        }
        max.resolved = true;
        return max.id;
    
    }
}