import java.util.ArrayList;
public class Task {
    static long counter = 0;
    public String id;
    public int prioLvl;
    public int index;
    public ArrayList<String> dependencies;
    public long order;
    public boolean resolved;
    public Task(String id, int priority, String[] dependencies){
        this.id = id;
        // Negative so that the largest value is at the top
        this.prioLvl = priority;
        this.dependencies = new ArrayList<>();
        for(String s: dependencies){
            this.dependencies.add(s);
        }
        this.order = counter++;
        resolved = false;
    }
    public int HashCode(){
        // Ensure positive hashcode
        return id.hashCode() < 0 ? -1 * id.hashCode() : id.hashCode();
    }
}
