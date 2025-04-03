import java.util.ArrayList;

public class Task {
    static int counter = 0;
    String id;
    public int prioLvl;
    public int order;
    int npl;
    ArrayList<String> dependencies;
    Task left, right;
    boolean resolved;
    public Task(String id, int priority, String[] dependencies){
        this.id = id;
        // Negative so that the largest value is at the top
        this.prioLvl = -priority;
        this.dependencies = new ArrayList<>();
        this.order = counter++;
        for(String s : dependencies){
            this.dependencies.add(s);
        }
        this.resolved = false;
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
